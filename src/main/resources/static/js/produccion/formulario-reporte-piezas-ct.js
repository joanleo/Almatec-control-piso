
document.getElementById('reporteForm').addEventListener('submit', function() {
	document.getElementById('spinner').removeAttribute('hidden')
});

document.addEventListener('DOMContentLoaded', function(){
	const ct = parseInt( document.getElementById('centroTrabajo').value)
	const ctsMuestranLote = [3,4,5,6,7,8,9,10,11,17]
	const divLotes = document.getElementById('divLotes');
	const showLotes = ctsMuestranLote.includes(ct);

	if(showLotes){
        divLotes.removeAttribute('hidden');
    } else {
        console.log("No se muestra el select de lotes");
    }
	
	let inputCant = document.getElementById('cant-reportar')
	let inputKg = document.getElementById('kg-reportar')
	const loteSelect = document.getElementById('loteSelect');
    const kgDisponibleInput = document.getElementById('kg-disponible');
	const kgWarning = document.getElementById('kg-warning');
	const btnGuardar = document.getElementById('btnGuardar');
	const loteDescripcion = document.getElementById('lote-descripcion');
	
	inputCant.value = 0;
    inputKg.value = 0;
	kgDisponibleInput.value = 0;
	
	const pesoItem = parseFloat(inputKg.getAttribute('data-peso-item') || 0);
	
	function validateKg() {
		if (!showLotes) return;
		
        const kgReportados = parseFloat(inputKg.value) || 0;
        const kgDisponibles = parseFloat(kgDisponibleInput.value) || 0;
        
        if (kgReportados > kgDisponibles) {
            kgWarning.style.display = 'block';
            btnGuardar.disabled = true;
        } else {
            kgWarning.style.display = 'none';
            btnGuardar.disabled = false;
        }
    }
	
	inputCant.addEventListener('input', function(event){
		const max = parseInt(this.max)
		if(this.value < 0) this.value = 0
		if(this.value > max ) this.value = max
		
		inputKg.value = (pesoItem * this.value).toFixed(2)
		
		validateKg()
	})
	
	if (showLotes) {
        loteSelect.addEventListener('change', function() {
            const selectedOption = this.options[this.selectedIndex];
            const disponible = Number(selectedOption.getAttribute('data-disponible')).toFixed(2);
            kgDisponibleInput.value = disponible || 0;
			
			const descripcion = selectedOption.dataset.descripcion || '';
			loteDescripcion.value = descripcion;

            validateKg();
        });
		
		if (loteSelect.value) {
	        const selectedOption = loteSelect.options[loteSelect.selectedIndex];
	        kgDisponible.value = selectedOption.dataset.disponible || 0;
	        loteDescripcion.value = selectedOption.dataset.descripcion || '';
	        calcularKilogramos();
	        validarKilogramos();
	    }
    }
	

	updateIdConfigProceso()
})

function updateIdConfigProceso() {
    const idConfigProcesoField = document.getElementById('idConfigProceso');
    const configProceso = JSON.parse(localStorage.getItem('configProceso')) || null;
    
    if (configProceso) {
        idConfigProcesoField.value = configProceso.id;
    }
}
