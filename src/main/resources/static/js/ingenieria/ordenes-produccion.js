// Constants
const CONSTANTS = {
	API: {
		DETALLE_OP: '/ingenieria/op',
		DOWNLOAD_PDF: 'http://10.75.98.3:8090/centros-trabajo/descargar-pdf'
	},
	SELECTORS: {
		MODAL: {
			DETALLE_PROYECTO: '#detalleProyecto',
			TITLE: '#detalleProyectoLabel',
			ZONA: '#zona',
			ESQUEMA_PINTURA: '#esquema_pintura',
			FECHA_INSTALACION: '#fecha_instalacion',
			COLOR_BASTIDORES: '#color_bastidores',
			COLOR_VIGAS: '#color_vigas',
			COLOR_PROTECTORES: '#color_protectores'
		},
		TABLES: {
			DETALLE: '#detalle',
			DETALLE_BODY: '#tabla_detalle_proyecto'
		},
		FILTER: {
			FORM: '.filtro',
			KEYWORD: '#keyword'
		}
	},
	STATUS_CLASSES: {
		CUMPLIDO: 'finished__badge',
		APROBADO: 'approved__badge',
		ANULADO: 'canceled__badge',
		DEFAULT: 'in_preparation__badge'
	}
};

// State management
let state = {
	dataTable: null,
	dataTableInitialized: false,
	currentOp: null,
	dataTableOptions: null
};

// Utility functions
const utils = {
	getStatusClass(estado) {
		return CONSTANTS.STATUS_CLASSES[estado.toUpperCase()] || CONSTANTS.STATUS_CLASSES.DEFAULT;
	},

	async fetchWithError(url, options = {}) {
		try {
			const response = await fetch(url, options);
			if (!response.ok) {
				throw new Error(`HTTP error! status: ${response.status}`);
			}
			return await response.json();
		} catch (error) {
			console.error('Error en la petición:', error);
			throw error;
		}
	},

	clearFormFields() {
		const selectors = CONSTANTS.SELECTORS.MODAL;
		Object.values(selectors).forEach(selector => {
			const element = document.querySelector(selector);
			if (element && element.tagName === 'INPUT') {
				element.value = '';
			}
		});
	}
};

// Modal handler
class ProyectoModalHandler {
	constructor(dataTableHandler) {
        this.modal = document.querySelector(CONSTANTS.SELECTORS.MODAL.DETALLE_PROYECTO);
        this.dataTableHandler = dataTableHandler; // Guardamos la referencia al DataTableHandler
        this.setupModalEvents();
    }

	setupModalEvents() {
		this.modal.addEventListener('hidden.bs.modal', () => {
			utils.clearFormFields();
			if (state.dataTable) {
				state.dataTable.clear().draw();
			}
		});
	}

	async fillModalData(rowData) {
		const {
			zona,
			proyecto,
			op,
			fechaInstalacion,
			esquemaPintura,
			numOp
		} = this.extractRowData(rowData);

		this.updateModalTitle(op, proyecto);
		this.fillModalFields({
			zona,
			esquemaPintura,
			fechaInstalacion,
			numOp
		});

		state.currentOp = numOp;
		await this.dataTableHandler.initialize();
	}

	extractRowData(rowData) {
		const data = rowData.innerText.split('\t');
		return {
			op: data[1],
			proyecto: data[2],
			zona: data[3],
			fechaInstalacion: data[6],
			esquemaPintura: data[8],
			numOp: rowData.id.split('-')[1]
		};
	}

	updateModalTitle(op, proyecto) {
		const titleElement = document.querySelector(CONSTANTS.SELECTORS.MODAL.TITLE);
		titleElement.innerText = `${op} - ${proyecto}`;
	}

	fillModalFields({ zona, esquemaPintura, fechaInstalacion, numOp }) {
		document.querySelector(CONSTANTS.SELECTORS.MODAL.ZONA).value = zona;
		document.querySelector(CONSTANTS.SELECTORS.MODAL.ESQUEMA_PINTURA).value = esquemaPintura;
		document.querySelector(CONSTANTS.SELECTORS.MODAL.FECHA_INSTALACION).value = fechaInstalacion;

		// Llenar colores
		['BASTIDORES', 'VIGAS', 'PROTECTORES'].forEach(tipo => {
			const selector = CONSTANTS.SELECTORS.MODAL[`COLOR_${tipo}`];
			const value = document.querySelector(`#${tipo.toLowerCase()}_${numOp}`).value;
			document.querySelector(selector).value = value;
		});
	}
}

// DataTable handler
class DataTableHandler {
	constructor() {
		this.table = document.querySelector(CONSTANTS.SELECTORS.TABLES.DETALLE);
	}

	async initialize() {
		if (state.dataTableInitialized) {
			state.dataTable.destroy();
		}

		await this.setupDataTableOptions();
		try {
			state.dataTable = $(CONSTANTS.SELECTORS.TABLES.DETALLE).DataTable({
				...state.dataTableOptions,
				
                ajax: {
                    url: `${CONSTANTS.API.DETALLE_OP}/${state.currentOp}/detalle`,
                    type: "POST",
                    dataType: "json",
                    contentType: "application/json",
                    data: function(d) {
                        return JSON.stringify(d);
                    }
                },
				drawCallback: function(settings) {
                    const api = this.api();
                    // Esperamos un breve momento para asegurar que el DOM se ha actualizado
					if (!api.settings()[0].bDestroying) {
					        api.columns.adjust();
					    }
                }
			});

			this.setupResizeHandlers();
            this.setupPDFButtonEvents();
            state.dataTableInitialized = true;
		} catch (error) {
			console.error('Error inicializando DataTable:', error);
			throw error;
		}
	}
	
	setupResizeHandlers() {
        // Ajustar cuando la ventana cambia de tamaño
        window.addEventListener('resize', () => {
            if (state.dataTable) {
                state.dataTable.columns.adjust();
            }
        });

        // Ajustar cuando el modal se muestra
        const modal = document.querySelector(CONSTANTS.SELECTORS.MODAL.DETALLE_PROYECTO);
        if (modal) {
            modal.addEventListener('shown.bs.modal', () => {
                if (state.dataTable) {
                    setTimeout(() => {
                        state.dataTable.columns.adjust();
                    }, 100);
                }
            });
        }

        // Ajustar cuando cambia la pestaña (si hay tabs)
        $('a[data-toggle="tab"]').on('shown.bs.tab', () => {
            if (state.dataTable) {
                state.dataTable.columns.adjust();
            }
        });
    }

	async setupDataTableOptions() {
		const fileName = this.getFileName();
		state.dataTableOptions = {
			responsive: true,
			scrollX: true,
			scrollCollapse: true,
            autoWidth: false,
			fixedHeader: true,
			processing: true,
            serverSide: true,
			columns: [
		        { 
		            data: 0,
		            width: '10%',
		            className: 'text-nowrap',
					render: function(data, type, row) {
				        if (type === 'display') {
				            return data == 0 || data === "0" ? '-' : 'GY' + data;
				        }
				        return data;
				    }
		        },  // ITEM
		        { 
		            data: 1,
		            width: '8%',
		            className: 'text-nowrap'
		        },  // MARCA
		        { 
		            data: 2,
		            width: '25%',
		            className: 'text-nowrap'
		        },  // DESCRIPCION
		        { 
		            data: 3,
		            width: '8%',
		            className: 'text-nowrap text-end',
					render: function(data, type, row) {
	                    if (type === 'display') {
	                        return data;
	                    }
	                    return data;
	                }
		        },  // CANT
		        { 
		            data: 4,
		            width: '10%',
		            className: 'text-nowrap text-end',
					render: function(data, type, row) {
		                if (type === 'display') {
		                    return formatNumber(data);
		                }
		                return data;
		            }
		        },  // PESO
		        { 
		            data: 5,
		            width: '12%',
		            className: 'text-nowrap text-end',
					render: function(data, type, row) {
	                    if (type === 'display') {
	                        return data;
	                    }
	                    return data;
	                }
		        },  // CANT PENDIENTE
		        { 
		            data: 6,
		            width: '12%',
		            className: 'text-nowrap text-end',
					render: function(data, type, row) {
		                if (type === 'display') {
		                    return formatNumber(data);
		                }
		                return data;
		            }
		        },  // PESO PENDIENTE
		        { 
		            data: 7,
		            width: '10%',
		            className: 'text-nowrap'
		        },  // COLOR
		        { 
		            data: 8,
		            width: '5%',
		            className: 'text-nowrap text-center',
		            render: function(data, type, row) {
		                if (!data || data === "COM") {
		                    return '';
		                }
		                return `<button class="btn btn-primary btn-sm pdf-button">
		                         <i class="fas fa-file-pdf"></i>
		                       </button>`;
		            }
		        }  // PLANO
		    ],
			columnDefs: [
				{
					targets: -1,
					data: null,
					render: function(data, type, row) {
						const plano = row[8];
						if (!plano || plano === "COM") {
							return '';
						}
						const buttonClass = plano ? 'btn-primary' : 'btn-secondary';
						const disabled = plano ? '' : 'disabled';
						return `<button class="btn ${buttonClass} btn-sm pdf-button" ${disabled}>
	                             <i class="fas fa-file-pdf"></i> 
	                           </button>`;
					}
				},
				{
		            targets: '_all',
		            className: 'text-nowrap'
		        }
			],
			dom: '<"top"B><"top"fi>rt<"bottom"lp><"clear">',
			buttons: [
				{
					extend: 'excelHtml5',
					title: fileName,
					titleAttr: 'Exportar a Excel',
					className: 'btn btn-primary',
					action: function(e, dt, button, config) {
				        const self = this;
				        // Hacemos la llamada al endpoint que devuelve todos los datos
				        $.ajax({
				            url: `${CONSTANTS.API.DETALLE_OP}/${state.currentOp}/detalle`,
				            type: 'GET',
				            success: function(data) {
				                // Convertimos los datos al formato que espera DataTables
				                const exportData = data.map(item => [
				                    item.itemId,
				                    item.marca,
				                    item.descripcion,
				                    item.cant,
				                    item.peso,
				                    item.cantPentiente,
				                    item.pesoPendiente,
				                    item.color,
				                    item.plano
				                ]);

				                // Temporalmente reemplazamos los datos en el DataTable
				                const originalData = dt.data();
				                dt.clear();
				                dt.rows.add(exportData);
				                dt.draw();

				                // Ejecutamos la exportación con el contexto correcto
				                $.fn.dataTable.ext.buttons.excelHtml5.action.call(self, e, dt, button, config);

				                // Restauramos los datos originales
				                dt.clear();
				                dt.rows.add(originalData);
				                dt.draw();
				            }
				        });
				    },
					exportOptions: {
						modifier: {
	                        page: 'all'    // Esto es clave - indica que exporte todas las páginas
	                    },
			            columns: ':not(:last-child)',
			            format: {
							body: function(data, row, column, node) {
	                            // Removemos el formato de número si existe
	                            if (typeof data === 'string') {
	                                // Primero, removemos los puntos de miles
	                                data = data.replace(/\./g, '');
	                                // Luego reemplazamos la coma decimal por punto
	                                data = data.replace(',', '.');
	                                
	                                // Si el resultado es un número válido, lo retornamos como número
	                                if (!isNaN(data)) {
	                                    return parseFloat(data);
	                                }
	                            }
	                            return data;
	                        }
			            }
			        },
					customize: function(xlsx) {
	                    let sheet = xlsx.xl.worksheets['sheet1.xml'];
	                    $('row c[r^="E"], row c[r^="G"]', sheet).each(function() {
	                        // Configuramos el formato de número para las columnas de peso (E y G)
	                        $(this).attr('s', '2'); // Estilo numérico con 2 decimales
	                    });
	                }
				},
				{
					extend: 'pdfHtml5',
					title: fileName,
					titleAttr: 'Exportar a PDF',
					className: 'btn btn-primary',
					action: function(e, dt, button, config) {
				        const self = this;
				        // Hacemos la llamada al endpoint que devuelve todos los datos
				        $.ajax({
				            url: `${CONSTANTS.API.DETALLE_OP}/${state.currentOp}/detalle`,
				            type: 'GET',
				            success: function(data) {
				                // Convertimos los datos al formato que espera DataTables
				                const exportData = data.map(item => [
				                    item.itemId,
				                    item.marca,
				                    item.descripcion,
				                    item.cant,
				                    item.peso,
				                    item.cantPentiente,
				                    item.pesoPendiente,
				                    item.color,
				                    item.plano
				                ]);

				                // Temporalmente reemplazamos los datos en el DataTable
				                const originalData = dt.data();
				                dt.clear();
				                dt.rows.add(exportData);
				                dt.draw();

				                // Ejecutamos la exportación con el contexto correcto
				                $.fn.dataTable.ext.buttons.pdfHtml5.action.call(self, e, dt, button, config);

				                // Restauramos los datos originales
				                dt.clear();
				                dt.rows.add(originalData);
				                dt.draw();
				            }
				        });
				    },
					orientation: 'landscape', // Orientación del PDF (portrait o landscape)
					pageSize: 'LEGAL',
					exportOptions: {
			            columns: ':not(:last-child)' ,
						modifier: {
	                        page: 'all'    // Esto es clave - indica que exporte todas las páginas
	                    },
			        }
				}

			],
			lengthMenu: [5, 10, 15, 20, 50, 100],
			pageLength: 50,
			destroy: true,
			language: {
				"processing": "Procesando...",
				"lengthMenu": "Mostrar _MENU_ registros",
				"zeroRecords": "No se encontraron resultados",
				"emptyTable": "Ningún dato disponible en esta tabla",
				"infoEmpty": "Mostrando registros del 0 al 0 de un total de 0 registros",
				"infoFiltered": "(filtrado de un total de _MAX_ registros)",
				"search": "Buscar:",
				"loadingRecords": "Cargando...",
				"paginate": {
					"first": "Primero",
					"last": "Último",
					"next": "Siguiente",
					"previous": "Anterior"
				},
				"aria": {
					"sortAscending": ": Activar para ordenar la columna de manera ascendente",
					"sortDescending": ": Activar para ordenar la columna de manera descendente"
				},
				"buttons": {
					"copy": "Copiar",
					"colvis": "Visibilidad",
					"collection": "Colección",
					"colvisRestore": "Restaurar visibilidad",
					"copyKeys": "Presione ctrl o u2318 + C para copiar los datos de la tabla al portapapeles del sistema. <br \/> <br \/> Para cancelar, haga clic en este mensaje o presione escape.",
					"copySuccess": {
						"1": "Copiada 1 fila al portapapeles",
						"_": "Copiadas %ds fila al portapapeles"
					},
					"copyTitle": "Copiar al portapapeles",
					"csv": "CSV",
					"excel": "Excel",
					"pageLength": {
						"-1": "Mostrar todas las filas",
						"_": "Mostrar %d filas"
					},
					"pdf": "PDF",
					"print": "Imprimir",
					"renameState": "Cambiar nombre",
					"updateState": "Actualizar",
					"createState": "Crear Estado",
					"removeAllStates": "Remover Estados",
					"removeState": "Remover",
					"savedStates": "Estados Guardados",
					"stateRestore": "Estado %d"
				},
				"autoFill": {
					"cancel": "Cancelar",
					"fill": "Rellene todas las celdas con <i>%d<\/i>",
					"fillHorizontal": "Rellenar celdas horizontalmente",
					"fillVertical": "Rellenar celdas verticalmente"
				},
				"decimal": ",",
				"searchBuilder": {
					"add": "Añadir condición",
					"button": {
						"0": "Constructor de búsqueda",
						"_": "Constructor de búsqueda (%d)"
					},
					"clearAll": "Borrar todo",
					"condition": "Condición",
					"conditions": {
						"date": {
							"before": "Antes",
							"between": "Entre",
							"empty": "Vacío",
							"equals": "Igual a",
							"notBetween": "No entre",
							"not": "Diferente de",
							"after": "Después",
							"notEmpty": "No Vacío"
						},
						"number": {
							"between": "Entre",
							"equals": "Igual a",
							"gt": "Mayor a",
							"gte": "Mayor o igual a",
							"lt": "Menor que",
							"lte": "Menor o igual que",
							"notBetween": "No entre",
							"notEmpty": "No vacío",
							"not": "Diferente de",
							"empty": "Vacío"
						},
						"string": {
							"contains": "Contiene",
							"empty": "Vacío",
							"endsWith": "Termina en",
							"equals": "Igual a",
							"startsWith": "Empieza con",
							"not": "Diferente de",
							"notContains": "No Contiene",
							"notStartsWith": "No empieza con",
							"notEndsWith": "No termina con",
							"notEmpty": "No Vacío"
						},
						"array": {
							"not": "Diferente de",
							"equals": "Igual",
							"empty": "Vacío",
							"contains": "Contiene",
							"notEmpty": "No Vacío",
							"without": "Sin"
						}
					},
					"data": "Data",
					"deleteTitle": "Eliminar regla de filtrado",
					"leftTitle": "Criterios anulados",
					"logicAnd": "Y",
					"logicOr": "O",
					"rightTitle": "Criterios de sangría",
					"title": {
						"0": "Constructor de búsqueda",
						"_": "Constructor de búsqueda (%d)"
					},
					"value": "Valor"
				},
				"searchPanes": {
					"clearMessage": "Borrar todo",
					"collapse": {
						"0": "Paneles de búsqueda",
						"_": "Paneles de búsqueda (%d)"
					},
					"count": "{total}",
					"countFiltered": "{shown} ({total})",
					"emptyPanes": "Sin paneles de búsqueda",
					"loadMessage": "Cargando paneles de búsqueda",
					"title": "Filtros Activos - %d",
					"showMessage": "Mostrar Todo",
					"collapseMessage": "Colapsar Todo"
				},
				"select": {
					"cells": {
						"1": "1 celda seleccionada",
						"_": "%d celdas seleccionadas"
					},
					"columns": {
						"1": "1 columna seleccionada",
						"_": "%d columnas seleccionadas"
					},
					"rows": {
						"1": "1 fila seleccionada",
						"_": "%d filas seleccionadas"
					}
				},
				"thousands": ".",
				"datetime": {
					"previous": "Anterior",
					"hours": "Horas",
					"minutes": "Minutos",
					"seconds": "Segundos",
					"unknown": "-",
					"amPm": [
						"AM",
						"PM"
					],
					"months": {
						"0": "Enero",
						"1": "Febrero",
						"10": "Noviembre",
						"11": "Diciembre",
						"2": "Marzo",
						"3": "Abril",
						"4": "Mayo",
						"5": "Junio",
						"6": "Julio",
						"7": "Agosto",
						"8": "Septiembre",
						"9": "Octubre"
					},
					"weekdays": {
						"0": "Dom",
						"1": "Lun",
						"2": "Mar",
						"4": "Jue",
						"5": "Vie",
						"3": "Mié",
						"6": "Sáb"
					},
					"next": "Próximo"
				},
				"editor": {
					"close": "Cerrar",
					"create": {
						"button": "Nuevo",
						"title": "Crear Nuevo Registro",
						"submit": "Crear"
					},
					"edit": {
						"button": "Editar",
						"title": "Editar Registro",
						"submit": "Actualizar"
					},
					"remove": {
						"button": "Eliminar",
						"title": "Eliminar Registro",
						"submit": "Eliminar",
						"confirm": {
							"_": "¿Está seguro de que desea eliminar %d filas?",
							"1": "¿Está seguro de que desea eliminar 1 fila?"
						}
					},
					"error": {
						"system": "Ha ocurrido un error en el sistema (<a target=\"\\\" rel=\"\\ nofollow\" href=\"\\\">Más información&lt;\\\/a&gt;).<\/a>"
					},
					"multi": {
						"title": "Múltiples Valores",
						"restore": "Deshacer Cambios",
						"noMulti": "Este registro puede ser editado individualmente, pero no como parte de un grupo.",
						"info": "Los elementos seleccionados contienen diferentes valores para este registro. Para editar y establecer todos los elementos de este registro con el mismo valor, haga clic o pulse aquí, de lo contrario conservarán sus valores individuales."
					}
				},
				"info": "Mostrando _START_ a _END_ de _TOTAL_ registros",
				"stateRestore": {
					"creationModal": {
						"button": "Crear",
						"name": "Nombre:",
						"order": "Clasificación",
						"paging": "Paginación",
						"select": "Seleccionar",
						"columns": {
							"search": "Búsqueda de Columna",
							"visible": "Visibilidad de Columna"
						},
						"title": "Crear Nuevo Estado",
						"toggleLabel": "Incluir:",
						"scroller": "Posición de desplazamiento",
						"search": "Búsqueda",
						"searchBuilder": "Búsqueda avanzada"
					},
					"removeJoiner": "y",
					"removeSubmit": "Eliminar",
					"renameButton": "Cambiar Nombre",
					"duplicateError": "Ya existe un Estado con este nombre.",
					"emptyStates": "No hay Estados guardados",
					"removeTitle": "Remover Estado",
					"renameTitle": "Cambiar Nombre Estado",
					"emptyError": "El nombre no puede estar vacío.",
					"removeConfirm": "¿Seguro que quiere eliminar %s?",
					"removeError": "Error al eliminar el Estado",
					"renameLabel": "Nuevo nombre para %s:"
				},
				"infoThousands": "."
			},
			initComplete: function(settings, json) {
	            // Ajustar columnas después de que la tabla se inicialice
	            this.api().columns.adjust();
	        },
			drawCallback: function(settings) {
                const api = this.api();
                // Esperamos un breve momento para asegurar que el DOM se ha actualizado
				if (!api.settings()[0].bDestroying) {
				        api.columns.adjust();
				    }
            }
		};
		$('#detalleOrdenProduccion').on('shown.bs.modal', function () {
		        if (detalleOPTable) {
		            detalleOPTable.columns.adjust();
		        }
		    });
	}

	setupPDFButtonEvents() {
		$(CONSTANTS.SELECTORS.TABLES.DETALLE).off('click', '.pdf-button')
			.on('click', '.pdf-button:not([disabled])', function() {
				const data = state.dataTable.row($(this).closest('tr')).data();
				const plano = data[8];
				if (plano && plano !== "COM") {
					window.open(`${CONSTANTS.API.DOWNLOAD_PDF}/${plano}.pdf`, '_blank');
				}
			});
	}

	getFileName() {
		const modalTitle = document.querySelector(CONSTANTS.SELECTORS.MODAL.TITLE).textContent;
		return modalTitle;
	}
}

// Filter handler
class FilterHandler {
	constructor() {
		this.setupFilterEvents();
	}

	setupFilterEvents() {
		const filterForm = document.querySelector(CONSTANTS.SELECTORS.FILTER.FORM);
		filterForm.addEventListener('submit', this.handleFilterSubmit.bind(this));
	}

	handleFilterSubmit(event) {
		event.preventDefault();
		const keyword = document.querySelector(CONSTANTS.SELECTORS.FILTER.KEYWORD).value;
		window.location.href = `/ingenieria/status/proyectos?keyword=${encodeURIComponent(keyword)}`;
	}

	clearFilter() {
		document.querySelector(CONSTANTS.SELECTORS.FILTER.KEYWORD).value = '';
		window.location.href = '/ingenieria/status/proyectos';
	}
}

const initializeApp = () => {
    try {
        // Creamos primero el DataTableHandler
        const dataTableHandler = new DataTableHandler();
        
        // Pasamos el dataTableHandler al ProyectoModalHandler
        const modalHandler = new ProyectoModalHandler(dataTableHandler);
        const filterHandler = new FilterHandler();

        // Setup status badges
        document.querySelectorAll('[data-estado]').forEach(element => {
            const estado = element.getAttribute('data-estado');
            element.classList.add(utils.getStatusClass(estado));
        });

        // Export handlers to window for use in HTML
        window.llenarDetalleProyecto = async (event) => {
            try {
                const rowOp = event.target.parentNode.parentNode;
                await modalHandler.fillModalData(rowOp);
            } catch (error) {
                console.error('Error en llenarDetalleProyecto:', error);
            }
        };

        window.clearFilter = () => filterHandler.clearFilter();

    } catch (error) {
        console.error('Error inicializando la aplicación:', error);
    }
};

const formatNumber = (number) => {
    return new Intl.NumberFormat('es-CO', {
        minimumFractionDigits: 2,
        maximumFractionDigits: 2
    }).format(number);
};

// Ensure DOM is loaded before initializing
if (document.readyState === 'loading') {
    document.addEventListener('DOMContentLoaded', initializeApp);
} else {
    initializeApp();
}