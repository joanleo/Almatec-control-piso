// Estado global de la aplicación
const state = {
    loadedPvs: new Set(),
    isLoading: false,
    activeRequest: null,
    productionOrdersData: new Map() // Cambiamos a Map para mejor manejo de datos
};

// Constantes y configuración
const CONFIG = {
    PAGE_SIZE: 10,
    DEFAULT_SORT: 'fecha',
    DEFAULT_DIRECTION: 'desc',
    DATE_FORMAT: {
        year: "numeric",
        month: "2-digit",
        day: "2-digit"
    }
};

// Inicialización
document.addEventListener('DOMContentLoaded', function() {
    initializeTooltips();
    initializeDateConstraints();
    initializeSortingListeners();
    buscarPedidos(); // Búsqueda inicial
});

// Inicializadores
function initializeTooltips() {
    const tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'));
    tooltipTriggerList.map(tooltipTriggerEl => new bootstrap.Tooltip(tooltipTriggerEl));
}

function initializeDateConstraints() {
    const today = new Date().toISOString().split('T')[0];
    document.getElementById('start_date').max = today;
    document.getElementById('end_date').max = today;
}

function initializeSortingListeners() {
    document.querySelectorAll('th.sortable').forEach(header => {
        header.onclick = () => handleSort(header);
    });
}

// Manejadores de eventos
function handleSort(header) {
    const sortField = header.dataset.sortField;
    const sortDirection = header.dataset.sortDirection === 'asc' ? 'desc' : 'asc';
    header.dataset.sortDirection = sortDirection;

    updateSortIcons(header, sortDirection);
    buscarPedidos(0, CONFIG.PAGE_SIZE, sortField, sortDirection);
}

function updateSortIcons(activeHeader, direction) {
    document.querySelectorAll('th.sortable i').forEach(icon => icon.className = 'fa fa-sort');
    activeHeader.querySelector('i').className = direction === 'asc' ? 'fa fa-sort-up' : 'fa fa-sort-down';
}

// Funciones de manejo de fechas
function validateDates() {
    const startDate = document.getElementById('start_date');
    const endDate = document.getElementById('end_date');
    
    if (startDate.value && endDate.value) {
        if (new Date(endDate.value) < new Date(startDate.value)) {
            alert('La fecha final no puede ser menor que la fecha inicial');
            endDate.value = startDate.value;
        }
    }
    
    endDate.min = startDate.value;
    
    if (startDate.value && endDate.value) {
        buscarPedidos();
    }
}

function setDateRange(range) {
    const startDate = document.getElementById('start_date');
    const endDate = document.getElementById('end_date');
    const today = new Date();
    
    const ranges = {
        today: {
            start: today,
            end: today
        },
        week: {
            start: new Date(today.setDate(today.getDate() - 7)),
            end: new Date()
        },
        month: {
            start: new Date(today.setMonth(today.getMonth() - 1)),
            end: new Date()
        }
    };

    const selectedRange = ranges[range];
    startDate.value = selectedRange.start.toISOString().split('T')[0];
    endDate.value = selectedRange.end.toISOString().split('T')[0];
    
    buscarPedidos();
}

// Funciones de formateo
const formatDate = (date) => date ? new Date(date).toISOString().slice(0, 10) : null;

const formatNumber = (number) => {
    return new Intl.NumberFormat('es-CO', {
        minimumFractionDigits: 2,
        maximumFractionDigits: 2
    }).format(number);
};

// Funciones de limpieza
function clearFilters() {
    document.querySelectorAll('.card-body input').forEach(input => {
        input.value = '';
    });
    buscarPedidos();
}

// Funciones principales de datos
async function buscarPedidos(page = 0, size = CONFIG.PAGE_SIZE, sortField = CONFIG.DEFAULT_SORT, sortDirection = CONFIG.DEFAULT_DIRECTION) {
    if (state.isLoading) return; // Prevenir múltiples solicitudes
    state.isLoading = true;
    
    const loadingOverlay = document.getElementById('loadingOverlay');
    loadingOverlay.classList.remove('d-none');

    try {
        const filtro = getFiltros();
        const pedidos = await getPedidos(filtro, page, size, sortField, sortDirection);
        if (!pedidos || !pedidos.content) {
            throw new Error('Datos de órdenes inválidos');
        }
        const listPvs = createListObjectsPv(pedidos.content);
        fillTablePedidos(listPvs);
        setupPagination(pedidos.totalPages, page, size, sortField, sortDirection);
        
        // Limpiar el estado de las órdenes cargadas cuando se hace una nueva búsqueda
        state.loadedPvs.clear();
    } catch (error) {
        console.error('Error al buscar pedidos:', error);
        showError('Error al cargar los pedidos');
        document.getElementById("orders").innerHTML = `
            <tr class="table-warning">
                <td colspan="10" class="text-center">Error al cargar los datos</td>
            </tr>
        `;
    } finally {
        state.isLoading = false;
        loadingOverlay.classList.add('d-none');
    }
}

function getFiltros() {
    return {
        un: document.getElementById('un').value,
        estado: document.getElementById('estado').value,
        asesor: document.getElementById('asesor').value,
        descripcion: document.getElementById('descripcion').value,
        cliente: document.getElementById('cliente').value,
        fechaInicio: formatDate(document.getElementById('start_date').value),
        fechaFin: formatDate(document.getElementById('end_date').value)
    };
}

const createListObjectsPv = (pedidos) => {
    return pedidos.map(item => {
        // Validar fecha
        let formattedDate;
        try {
            formattedDate = item.fecha ? 
                new Date(item.fecha + "T00:00:00").toLocaleDateString("es-CO", CONFIG.DATE_FORMAT) :
                'Fecha no disponible';
        } catch (e) {
            formattedDate = 'Fecha inválida';
        }

        return {
            rowId: item.rowid,
            pv: `${item.tipo || ''}-${item.noPv || ''}`,
            co: item.co || '',
            zona: item.zona || '',
            descripcion: item.descripcion || '',
            cliente: item.razonSocial || '',
            kgTotal: item.cantPedida || 0,
            kgCumplidos: item.totalKgCumplidos || 0,
            estado: item.estado || 'Sin estado',
            asesor: item.vendedor || '',
            fecha: formattedDate
        };
    });
};

const fillTablePedidos = (listPvs) => {
    const tbodyOrders = document.getElementById("pedidos");
    
    if (!listPvs.length) {
        tbodyOrders.innerHTML = `
            <tr class="table-warning">
                <td colspan="10" class="text-center">No se encontraron registros</td>
            </tr>
        `;
        return;
    }
            
    tbodyOrders.innerHTML = listPvs.map(pv => {
        const porcentaje = pv.kgTotal > 0 ? 
            ((pv.kgCumplidos / pv.kgTotal) * 100).toFixed(2) : 
            '0.00';
            
        return `
            <tr class="order-row">
                <td class="text-center">
                    <button class="btn btn-link btn-sm expand-btn p-0" onclick="toggleProductionOrders('${pv.rowId}', event)">
                        <i class="fa fa-chevron-down"></i>
                    </button>
                </td>
                <td class="text-nowrap">${pv.pv}</td>
                <td class="text-nowrap">${pv.asesor}</td>
                <td class="text-nowrap">${pv.co}</td>
                <td class="text-nowrap">${pv.descripcion}</td>
                <td class="text-nowrap">${pv.cliente}</td>
                <td><div class="${getStatusClass(pv.estado)}">${pv.estado}</div></td>
                <td>${formatNumber(pv.kgTotal)}</td>
                <td>${formatNumber(pv.kgTotal - pv.kgCumplidos)}</td>
                <td>
                    <div class="progress-container">
                        <div class="progress" style="height: 20px;">
                            <div class="progress-bar" role="progressbar" 
                                 style="width: ${porcentaje}%"
                                 aria-valuenow="${porcentaje}"
                                 aria-valuemin="0" aria-valuemax="100">
                            </div>
                        </div>
                        <span class="progress-value">${porcentaje}%</span>
                    </div>
                </td>
                <td>${pv.fecha}</td>
            </tr>
            
			<tr class="production-orders-row d-none" id="production-orders-${pv.rowId}">
	            <td colspan="11">
	                <div class="production-orders-container p-3">
	                    <div class="d-flex justify-content-between align-items-center mb-3">
	                        <h6 class="mb-0">Órdenes de Producción</h6>
	                        <div class="spinner-border spinner-border-sm d-none" role="status" id="spinner-${pv.rowId}">
	                            <span class="visually-hidden">Cargando...</span>
	                        </div>
	                    </div>
	                    <div class="table-responsive justify-content-center" style="max-width:70%">
	                        <table class="table table-sm production-orders-table" id="production-orders-table-${pv.rowId}">
	                            <thead>
	                                <tr>
	                                    <th class="sortable" data-sort-field="op">OP <i class="fa fa-sort"></i></th>
	                                    <th class="sortable" data-sort-field="estado">Estado <i class="fa fa-sort"></i></th>
	                                    <th class="sortable" data-sort-field="fechaProduccion">Fecha Prod <i class="fa fa-sort"></i></th>
	                                    <th class="sortable" data-sort-field="fechaEntrega">Fecha Entrega <i class="fa fa-sort"></i></th>
	                                    <th class="sortable" data-sort-field="kgPlaneados">Cant Planeada [Kg] <i class="fa fa-sort"></i></th>
	                                    <th class="sortable" data-sort-field="kgCumplidos">Cant Cumplida [Kg] <i class="fa fa-sort"></i></th>
	                                    <th class="sortable" data-sort-field="avance">Avance [%] <i class="fa fa-sort"></i></th>
										<th class="text-center">Acciones</th>
	                                </tr>
	                            </thead>
	                            <tbody id="production-orders-body-${pv.rowId}">
	                            </tbody>
	                        </table>
	                    </div>
	                </div>
	            </td>
        `;
    }).join('');
};

function initializeProductionOrdersSorting(rowId) {
    const table = document.getElementById(`production-orders-table-${rowId}`);
    if (!table) return;

    table.querySelectorAll('th.sortable').forEach(header => {
        header.onclick = () => handleProductionOrderSort(header, rowId);
    });
}

// 3. Función para manejar el ordenamiento
function handleProductionOrderSort(header, rowId) {
    const sortField = header.dataset.sortField;
    const sortDirection = header.dataset.sortDirection === 'asc' ? 'desc' : 'asc';
    header.dataset.sortDirection = sortDirection;

	// Actualizar iconos
    const table = header.closest('table');
    table.querySelectorAll('th.sortable i').forEach(icon => icon.className = 'fa fa-sort');
    header.querySelector('i').className = sortDirection === 'asc' ? 'fa fa-sort-up' : 'fa fa-sort-down';

    // Ordenar y renderizar
    if (state.productionOrdersData[rowId]) {
        const sortedOrders = sortProductionOrders(state.productionOrdersData[rowId], sortField, sortDirection);
        renderProductionOrders(sortedOrders, rowId);
    }
}

function sortProductionOrders(orders, field, direction) {
    return [...orders].sort((a, b) => {
        let valueA, valueB;

        switch(field) {
            case 'op':
                valueA = `${a.opHijoTipo}-${a.opHijoNum}`;
                valueB = `${b.opHijoTipo}-${b.opHijoNum}`;
                break;
            case 'estado':
                valueA = a.opHijoEstado;
                valueB = b.opHijoEstado;
                break;
            case 'fechaProduccion':
            case 'fechaEntrega':
                valueA = a[field] ? new Date(a[field]) : new Date(0);
                valueB = b[field] ? new Date(b[field]) : new Date(0);
                break;
            case 'kgPlaneados':
                valueA = a.opHijoKgFabricar || 0;
                valueB = b.opHijoKgFabricar || 0;
                break;
            case 'kgCumplidos':
                valueA = a.opHijoKgCumplidos || 0;
                valueB = b.opHijoKgCumplidos || 0;
                break;
            case 'avance':
                valueA = ((a.opHijoKgCumplidos || 0) / (a.opHijoKgFabricar || 1)) * 100;
                valueB = ((b.opHijoKgCumplidos || 0) / (b.opHijoKgFabricar || 1)) * 100;
                break;
            default:
                valueA = a[field];
                valueB = b[field];
        }

        // Comparación
        if (valueA < valueB) return direction === 'asc' ? -1 : 1;
        if (valueA > valueB) return direction === 'asc' ? 1 : -1;
        return 0;
    });
}

function renderProductionOrders(orders, rowId) {
    const tbody = document.getElementById(`production-orders-body-${rowId}`);
    
    tbody.innerHTML = orders.map(order => {
        const kgCumplidos = order.opHijoKgCumplidos || 0;
        const kgSolicitados = order.opHijoKgFabricar || 0;
        const porcentaje = kgSolicitados > 0 ? 
            ((kgCumplidos / kgSolicitados) * 100).toFixed(2) : 
            '0.00';
            
        return `
            <tr>
                <td>${order.opHijoTipo}-${order.opHijoNum}</td>
                <td><div class="${getStatusClass(order.opHijoEstado)}">${order.opHijoEstado}</div></td>
                <td>${formatDate(order.fechaProduccion) || 'N/A'}</td>
                <td>${formatDate(order.fechaEntrega) || 'N/A'}</td>
                <td>${formatNumber(kgSolicitados)}</td>
                <td>${formatNumber(kgCumplidos)}</td>
                <td>
                    <div class="progress-container">
                        <div class="progress" style="height: 20px;">
                            <div class="progress-bar" role="progressbar" 
                                 style="width: ${porcentaje}%"
                                 aria-valuenow="${porcentaje}"
                                 aria-valuemin="0" aria-valuemax="100">
                            </div>
                        </div>
                        <span class="progress-value">${porcentaje}%</span>
                    </div>
                </td>
				<td class="text-center">
                    <button type="button" class="btn btn-primary btn-sm" 
                            data-bs-toggle="modal" 
                            data-bs-target="#detalleOrdenProduccion"
                            onclick="verDetalleOP('${order.opHijoNum}')">
                        <i class="fas fa-eye"></i>
                    </button>
                </td>
            </tr>
        `;
    }).join('');
}

async function toggleProductionOrders(rowId, event) {
    const productionOrdersRow = document.getElementById(`production-orders-${rowId}`);
    const expandBtn = event.currentTarget;
    const icon = expandBtn.querySelector('i');
    const spinner = document.getElementById(`spinner-${rowId}`);
    
    if (productionOrdersRow.classList.contains('d-none')) {
        icon.classList.remove('fa-chevron-down');
        icon.classList.add('fa-chevron-up');
        productionOrdersRow.classList.remove('d-none');
        
        if (!state.loadedPvs.has(rowId)) {
            if (state.activeRequest) {
                state.activeRequest.abort();
            }
            await loadProductionOrders(rowId, spinner);
            state.loadedPvs.add(rowId);
            initializeProductionOrdersSorting(rowId);
        }
    } else {
        icon.classList.remove('fa-chevron-up');
        icon.classList.add('fa-chevron-down');
        productionOrdersRow.classList.add('d-none');
    }
}

async function loadProductionOrders(rowId, spinner) {
    const tbody = document.getElementById(`production-orders-body-${rowId}`);
    spinner.classList.remove('d-none');
    
    try {
        const numericRowId = parseInt(rowId, 10);
        if (isNaN(numericRowId)) {
            throw new Error('ID de pedido inválido');
        }

        const controller = new AbortController();
        state.activeRequest = controller;

        const response = await fetch(`/comercial/pedidos/${numericRowId}/ordenes-produccion`, {
            signal: controller.signal
        });
        
        if (!response.ok) {
            throw new Error(`Error ${response.status}`);
        }
        
        const productionOrders = await response.json();
		
		//console.log(productionOrders)
        
        if (!productionOrders || productionOrders.length === 0) {
            tbody.innerHTML = `
                <tr>
                    <td colspan="7" class="text-center text-muted">
                        No se encontraron órdenes de producción
                    </td>
                </tr>
            `;
            return;
        }
        
        // Almacenar los datos
        state.productionOrdersData[rowId] = productionOrders;
        
        // Renderizar con ordenamiento inicial
        renderProductionOrders(productionOrders, rowId);
        
        // Inicializar los listeners de ordenamiento
        initializeProductionOrdersSorting(rowId);
        
    } catch (error) {
        console.error('Error:', error);
        tbody.innerHTML = `
            <tr>
                <td colspan="7" class="text-center text-danger">
                    Error al cargar las órdenes de producción
                </td>
            </tr>
        `;
    } finally {
        spinner.classList.add('d-none');
    }
}

const styles = `
.production-orders-container {
    background-color: #f8f9fa;
    border-radius: 4px;
    margin: 0 20px;
}

.fa-chevron-up, .fa-chevron-down {
    transition: all 0.3s ease;
}

.production-orders-row {
    background-color: #fff;
}

.production-orders-row td {
    padding: 0 !important;
}
`;

// Agregar los estilos dinámicamente
const styleSheet = document.createElement("style");
styleSheet.innerText = styles;
document.head.appendChild(styleSheet);

const getStatusClass = (estado) => {
    switch(estado) {
        case "Cumplido": return "finished__badge";
        case "Aprobado": return "approved__badge";
        case "Anulado": return "canceled__badge";
        default: return "in_preparation__badge";
    }
}

async function getPedidos(filtro, page, size, sortField, sortDirection) {
    const response = await fetch(`/comercial/pedidos/filtrar?page=${page}&size=${size}&sort=${sortField}&order=${sortDirection}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(filtro)
    });

    if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
    }

    return await response.json();
}

const setupPagination = (totalPages, currentPage, size, sortField, sortDirection) => {
    const pagination = document.getElementById('pagination');
    pagination.innerHTML = '';

    const maxVisiblePages = 5;
    let startPage = Math.max(0, currentPage - Math.floor(maxVisiblePages / 2));
    let endPage = Math.min(totalPages - 1, startPage + maxVisiblePages - 1);

    // Ajustar startPage si estamos cerca del final
    startPage = Math.max(0, Math.min(startPage, totalPages - maxVisiblePages));

    // Función para crear un elemento de página
    const createPageItem = (pageNum, text, isActive = false, isDisabled = false) => {
        const pageItem = document.createElement('li');
        pageItem.className = `page-item${isActive ? ' active' : ''}${isDisabled ? ' disabled' : ''}`;

        const pageLink = document.createElement('a');
        pageLink.className = 'page-link';
        pageLink.href = '#';
        pageLink.textContent = text;
        if (!isDisabled) {
            pageLink.onclick = (event) => {
                event.preventDefault();
                buscarPedidos(pageNum, size, sortField, sortDirection);
            };
        }

        pageItem.appendChild(pageLink);
        return pageItem;
    };

    // Botón "Primera página"
    pagination.appendChild(createPageItem(0, '«', false, currentPage === 0));

    // Botón "Anterior"
    pagination.appendChild(createPageItem(Math.max(0, currentPage - 1), '‹', false, currentPage === 0));

    // Páginas numeradas
    for (let i = startPage; i <= endPage; i++) {
        pagination.appendChild(createPageItem(i, i + 1, i === currentPage));
    }

    // Botón "Siguiente"
    pagination.appendChild(createPageItem(Math.min(totalPages - 1, currentPage + 1), '›', false, currentPage === totalPages - 1));

    // Botón "Última página"
    pagination.appendChild(createPageItem(totalPages - 1, '»', false, currentPage === totalPages - 1));
};

document.querySelectorAll('th.sortable').forEach(header => {
    header.onclick = () => {
        const sortField = header.dataset.sortField;
        const sortDirection = header.dataset.sortDirection === 'asc' ? 'desc' : 'asc';
        header.dataset.sortDirection = sortDirection;

        // Update icons
        document.querySelectorAll('th.sortable i').forEach(icon => icon.className = 'fa fa-sort');
        header.querySelector('i').className = sortDirection === 'asc' ? 'fa fa-sort-up' : 'fa fa-sort-down';

        buscarPedidos(0, 10, sortField, sortDirection);
    };
});

let detalleOPTable = null;

async function verDetalleOP(numOp) {
    if (detalleOPTable) {
        detalleOPTable.destroy();
    }
	
	$('#tablaDetalleOP').on('click', '.pdf-button', function() {
        const plano = $(this).data('plano');
        if (plano && plano !== "COM") {
            window.open(`http://10.75.98.3:8090/centros-trabajo/descargar-pdf/${plano}.pdf`, '_blank');
        }
    });
	
	const fileName = 'OP-' + numOp;
	
    detalleOPTable = $('#tablaDetalleOP').DataTable({
		ajax: {
	        url: `/ingenieria/op/${numOp}/detalle`,
	        type: "POST",
	        dataType: "json",
	        contentType: "application/json",
	        data: function(d) {
	            return JSON.stringify(d);
	        }
	    },
		responsive: true,
		scrollX: true,
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
            },
            { 
                data: 1,
                width: '8%',
                className: 'text-nowrap'
            },
            { 
                data: 2,
                width: '25%',
                className: 'text-nowrap'
            },
            { 
                data: 3,
                width: '8%',
                className: 'text-nowrap text-end',

            },
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
            },
            { 
                data: 5,
                width: '12%',
                className: 'text-nowrap text-end',
            },
            { 
                data: 6,
                width: '12%',
                className: 'text-nowrap',
				render: function(data, type, row) {
	                if (type === 'display') {
	                    return formatNumber(data);
	                }
	                return data;
	            }
            }
			, 
	        { 
	            data: 7,
	            width: '10%',
	            className: 'text-nowrap'
	        }, 
	        { 
	            data: 8,
	            width: '5%',
	            className: 'text-nowrap text-center',
	            render: function(data, type, row) {
	                if (!data || data === "COM") {
	                    return '';
	                }
	                return `<button class="btn btn-primary btn-sm pdf-button" data-plano="${data}">
	                         <i class="fas fa-file-pdf"></i>
	                       </button>`;
	            }
	        } 
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
					return `<button class="btn ${buttonClass} btn-sm pdf-button" ${disabled} onclick="descargarPlano('${data}')">
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
			            url: `/ingenieria/op/${numOp}/detalle`,
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
				            // Solo aplicamos el formato a las columnas numéricas específicas (4, 6 para peso)
				            // Y columnas 3, 5 para cantidades
				            if (column === 4 || column === 6 || column === 3 || column === 5) {
				                // Verificamos si el dato parece un número formateado
				                if (typeof data === 'string' && /^[\d.,]+$/.test(data.trim())) {
				                    // Primero, removemos los puntos de miles
				                    let cleanData = data.replace(/\./g, '');
				                    // Luego reemplazamos la coma decimal por punto
				                    cleanData = cleanData.replace(',', '.');
				                    
				                    // Si el resultado es un número válido, lo retornamos como número
				                    if (!isNaN(cleanData)) {
				                        return parseFloat(cleanData);
				                    }
				                }
				            }
				            // Para el resto de las columnas, devolvemos el dato sin modificar
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
			            url: `/ingenieria/op/${numOp}/detalle`,
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
		            columns: ':not(:last-child)' 
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
            // Ajustar columnas después de cada redibujado
            this.api().columns.adjust();
        }
    });
	
	$('#detalleOrdenProduccion').on('shown.bs.modal', function () {
        if (detalleOPTable) {
            detalleOPTable.columns.adjust();
        }
    });
	
	// Actualizar el título del modal
	document.getElementById('detalleOrdenProduccionLabel').textContent = 
	    `Detalle de Orden de Producción - ${numOp}`;
}


// Limpiar el DataTable cuando se cierra el modal
$('#detalleOrdenProduccion').on('hidden.bs.modal', function () {
    if (detalleOPTable) {
		$('#tablaDetalleOP').off('click', '.pdf-button'); 
		$('#detalleOrdenProduccion').off('shown.bs.modal');
        detalleOPTable.destroy();
        detalleOPTable = null;
    }
});