function mostrarAlert(mensaje, tipo) {
    const alertElement = document.createElement('div');
    alertElement.className = `alert alert-${tipo} alert-dismissible fade show`;
    alertElement.role = 'alert';
    alertElement.style.zIndex = '10000';
    alertElement.style.position = 'fixed';
    alertElement.style.top = '7rem';
    alertElement.style.left = '50%';
    alertElement.style.transform = 'translateX(-50%)';
    alertElement.style.width = '90rem'; 
    alertElement.style.maxWidth = '90%';
    alertElement.innerHTML = `
        <strong>${mensaje}</strong>
        <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>`;

    document.body.appendChild(alertElement);

    //setTimeout(() => alertElement.remove(), 5000);
}