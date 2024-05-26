function mostrarCodigo() {
    const select = document.getElementById('loteSelect')
    let codigo = document.getElementById('codErpMatriaPrima')
    const loteSeleccionado = select.options[select.selectedIndex].value
	const lotesConCodigos = JSON.parse(lotesConCodigosString)
	//Array.from(lotesConCodigos)
    const loteConCodigo = lotesConCodigos.find(function(lote) {
        return lote.loteErp === loteSeleccionado;
    })

    codigo.value = loteConCodigo ? loteConCodigo.codErp : ''
}