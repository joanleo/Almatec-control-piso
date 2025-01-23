document.addEventListener('DOMContentLoaded', function() {
	const lotesData = JSON.parse(lotesConCodigosString);
	    const loteSelect = document.getElementById('loteSelect');
	    const descripcionInput = document.getElementById('descripcionLote');
	    const codErpInput = document.getElementById('codErpMatriaPrima');

	    loteSelect.addEventListener('change', function() {
	        const selectedLot = lotesData.find(lote => lote.loteErp === this.value);
			console.log(selectedLot)
	        if (selectedLot) {
	            descripcionInput.value = selectedLot.descripcion;
	            codErpInput.value = selectedLot.codErp;
	        } else {
	            descripcionInput.value = '';
	            codErpInput.value = '';
	        }
	    });
})