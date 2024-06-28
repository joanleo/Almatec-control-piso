document.getElementById('reporteForm').addEventListener('submit', function() {
				document.getElementById('spinner').removeAttribute('hidden')
			});
document.addEventListener('DOMContentLoaded', function(){
	const ct = parseInt( document.getElementById('centroTrabajo').value)
	const ctsMuestranLote = [3,4,5,6,7,8,9,17]
	if(ctsMuestranLote.includes(ct)){
		document.getElementById('divLotes').removeAttribute('hidden')
	}else{
		console.log("No se muestra el select de lotes")
	}
	
	let inputCant = document.getElementById('cant-reportar')
	inputCant.addEventListener('input', function(event){
		const max = parseInt(this.max)
		if(this.value < 0) this.value = 0
		if(this.value > max ) this.value = max
	})
})
