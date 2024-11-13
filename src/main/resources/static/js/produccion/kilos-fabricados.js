async function actualizarKilosTotales() {
    try {
        const datos = await obtenerDatosKilosFabricados();
        if (datos && datos.length > 0) {
            const kilosTotales = calcularKilosTotales(datos);
            const elementoKilosTotales = document.getElementById('kilosTotales');
            if (elementoKilosTotales) {
                elementoKilosTotales.textContent = kilosTotales.toFixed(2);
            } else {
                console.error('Elemento con ID "kilosTotales" no encontrado');
            }
        } else {
            console.warn('No se obtuvieron datos para calcular los kilos totales');
        }
    } catch (error) {
        console.error('Error al actualizar kilos totales:', error);
    }
}

function calcularKilosTotales(datos) {
    return datos.reduce((total, item) => total + (item.peso * item.cantFabricada), 0);
}

async function obtenerDatosKilosFabricados() {
    try {
        const piezasCT = await obtenerPiezasCtProceso();
        return piezasCT;
    } catch (error) {
        console.error('Error al obtener datos de kilos fabricados:', error);
        return [];
    }
}

setInterval(actualizarKilosTotales, 2 * 60 * 1000);

async function obtenerPiezasCtProceso(){
	if(configProceso != null){
		try{
			const response = await fetch(`/centros-trabajo/${centroTSelected.id}/${configProceso.id}/piezas-operarios-ct-proceso`)
			if(!response.ok){
				console.error("Error en la solicitud:", response.statusText);
				throw new Error("Error en la asignacion de pieza")
			}
			const data = await response.json()
			return data
			
		}catch (error){
			console.log(error)
		}			
	}
}
