const createOpButtons = document.querySelectorAll('.create-op-button');
console.log(createOpButtons)
createOpButtons.forEach(button => {
    button.addEventListener('click', function() {
        const noPedido = button.closest('tr').querySelector('[name="noPedido"]').value;
        const ref = button.closest('tr').querySelector('[name="referencia"]').value;
		spinner.removeAttribute('hidden')
        document.getElementById('opForm').action = `/comercial/crear-op/${noPedido}/${ref}`;
        document.getElementById('opForm').submit();
    });
});