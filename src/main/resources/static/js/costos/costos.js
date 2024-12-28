
const form = document.getElementById("productionOrderForm")

form.addEventListener('submit', async function(e) {
    e.preventDefault();

    const orderId = orderSelect.value;
    if (!orderId) {
        mostrarAlert('Por favor seleccione una orden de producción', 'warning');
        return;
    }

    try {
        showLoader();
        
        const response = await fetch(`/costos/ordenes-produccion/${orderId}/ajustar-cantidades-ejecutar`, {
            method: 'POST',
            headers: {
                'Accept': 'application/json'
            }
        });

        const data = await response.json();
        console.log(data)
        if (!response.ok) {
            throw new Error(data || 'Error al actualizar las cantidades');
        }

        mostrarAlert('Cantidades actualizadas exitosamente', 'success');
        orderSelect.value = ''; // Resetear selección
    } catch (error) {
        mostrarAlert(error.mensaje, 'danger');
    } finally {
        hideLoader(false);
    }
});