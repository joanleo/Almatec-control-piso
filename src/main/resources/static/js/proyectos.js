let tabla_detalle_proyecto = document.querySelector("#tabla_detalle_proyecto")
let numOp;
let dataTableOptions;
async function fethItemsOp(numOp){
	try{
		const response = await fetch('/ingenieria/op/' + numOp + '/detalle')
		if(!response.ok){
			console.log(response.status)
			throw new Error("Error al obtener el detalle de la Op")
		}
		const data = await response.json()
		return data
	}catch(error){
		console.error("Error al obtener el detalle de la Op: ", error)
	}
}

let dataTable;
let dataTableIsInicialized = false;


async function llenarDetalleProyecto(event){
	console.log("click para llenar tabla")
	const rowOp = event.target.parentNode.parentNode
	const data = rowOp.innerText.split("\t")
	const modalTitle = document.getElementById("detalleProyectoLabel")
	modalTitle.innerText = "STATUS " + "  PROYECTO  " + data[2] + " " + data[1]
	
	numOp = rowOp.id.split("-")[1] // data[3].split("-")[0]
	console.log("Llenar detalle numOp",numOp)
	await initOptions()
	await initDataTable()
	
	//const itemsOp = await fethItemsOp(numOp)
	
	document.querySelector("#zona").value = data[3].split("-")[0]
	document.querySelector("#sistema").value = data[3].split("-")[1]
	document.querySelector("#esquema_pintura").value = data[8] 
	document.querySelector("#fecha_instalacion").value = data[6]

	document.querySelector("#color_bastidores").value = document.querySelector("#bastidores_"+numOp).value
	document.querySelector("#color_vigas").value = document.querySelector("#vigas_"+numOp).value
	document.querySelector("#color_protectores").value = document.querySelector("#protectores_"+numOp).value

}



const initDataTable = async () => {
	console.log("InitDataTable ", numOp)
	if(dataTableIsInicialized){
		console.log("Destruir tabla")
		dataTable.destroy()
	}
	console.log("Se crea la tabla con el itemOp ", numOp)
	console.log(dataTableOptions)
	dataTable = $('#exampleArray').DataTable(dataTableOptions)

	dataTableIsInicialized = true
}
async function initOptions(){
	dataTableOptions = {
	processing: true,
    serverSide: true,
	ajax: {
            "url": `/ingenieria/op/${numOp}/detalle`,
            "type": "POST",
            "dataType": "json",
            "contentType": "application/json",
            "data": function (d) {
                return JSON.stringify(d)
            }
        },
	dom: '<"top"B><"top"fi>rt<"bottom"lp><"clear">',
	buttons: [
		{
            extend:    'excelHtml5',
            text:      '<i class="fa-solid fa-file-csv" style="background-color: #F8F9FA;" ></i>',
            titleAttr: 'Exportar a Excel',
            className: 'btn btn-primary',
        },

	],
    lengthMenu: [5,10,15,20,50,100],	
	pageLength: 5,
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
}
	}

}

	/*$('#exampleArray').DataTable({
        "processing": true,
        "serverSide": true,
        "ajax": {
            "url": '/ingenieria/op/' + encodeURIComponent(numOp) + '/detalle',
            "type": "POST",
            "dataType": "json",
            "contentType": "application/json",
            "data": function (d) {
                return JSON.stringify(d)
            }
        }
    });
	}
async function llenarDetalleProyecto(event){
	const rowOp = event.target.parentNode.parentNode
	const data = rowOp.innerText.split("\t")
	console.log(data)
	const modalTitle = document.getElementById("detalleProyectoLabel")
	modalTitle.innerText = "STATUS " + "  PROYECTO  " + data[2] + " " + data[1]
	
	const numOp = rowOp.id.split("-")[1] // data[3].split("-")[0]
	
	const itemsOp = await fethItemsOp(numOp)
	
	document.querySelector("#zona").value = data[3].split("-")[0]
	document.querySelector("#sistema").value = data[3].split("-")[1]
	document.querySelector("#esquema_pintura").value = data[8] 
	document.querySelector("#fecha_instalacion").value = data[6]

	document.querySelector("#color_bastidores").value = document.querySelector("#bastidores_"+numOp).value
	document.querySelector("#color_vigas").value = document.querySelector("#vigas_"+numOp).value
	document.querySelector("#color_protectores").value = document.querySelector("#protectores_"+numOp).value
	
	while (tabla_detalle_proyecto.hasChildNodes()) {
		tabla_detalle_proyecto.removeChild(tabla_detalle_proyecto.firstChild)
	}
	let row
	let contId = 0
	for(const item of itemsOp){
		row = tabla_detalle_proyecto.insertRow(contId)
		row.setAttribute("id", "item_" + contId)
		let cod_item = row.insertCell(0)
		let marca = row.insertCell(1)
		let descripcion = row.insertCell(2)
		let cant = row.insertCell(3)
		let peso = row.insertCell(4)
		let cant_pend = row.insertCell(5)
		let peso_pend = row.insertCell(6)
		let pintura_bt = row.insertCell(7)
		//let pintura_viga = row.insertCell(8)
		//let pintura_prot = row.insertCell(9)
		
		cod_item.innerText = item["codigoErp"]
		marca.innerText = item["marca"]
		descripcion.innerText = item["descripcion"]
		cant.innerText = item["cant"]
		peso.innerText = item["peso"]
		cant_pend.innerText = item["codigoErp"]
		peso_pend.innerText = item["codigoErp"]
		pintura_bt.innerText = item["codigoErp"]
		//pintura_viga.innerText = item["codigoErp"]
		//pintura_prot.innerText = item["codigoErp"]
		
		contId++
	}
	
}*/