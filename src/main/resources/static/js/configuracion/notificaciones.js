let currentEmails = [];
let modal;
let deleteModal;

document.addEventListener('DOMContentLoaded', function() {
    modal = new bootstrap.Modal(document.getElementById('configModal'));
	deleteModal = new bootstrap.Modal(document.getElementById('deleteModal'));
});

// Validación de formularios
(function () {
    'use strict'
    const forms = document.querySelectorAll('.needs-validation')
    
    Array.from(forms).forEach(form => {
        form.addEventListener('submit', event => {
            if (!form.checkValidity()) {
                event.preventDefault()
                event.stopPropagation()
            }
            form.classList.add('was-validated')
        }, false)
    })
})()

// Mejora en la función addEmail
function addEmail() {
    const emailInput = document.getElementById('emailInput');
    const email = emailInput.value.trim();
    
    if (email && isValidEmail(email) && !currentEmails.includes(email)) {
        currentEmails.push(email);
        updateEmailList();
        emailInput.value = '';
        emailInput.focus();
    } else if (!isValidEmail(email)) {
        // Mostrar feedback visual
        emailInput.classList.add('is-invalid');
        setTimeout(() => emailInput.classList.remove('is-invalid'), 2000);
    }
}

function removeEmail(configId, email) {
    if(configId) {
        // Si tenemos un configId, es una configuración existente
        fetch(`/notificaciones/${configId}/email/${encodeURIComponent(email)}`, {
            method: 'DELETE'
        }).then(() => window.location.reload());
    } else {
        // Si no hay configId, estamos editando el formulario actual
        const index = currentEmails.indexOf(email);
        if (index > -1) {
            currentEmails.splice(index, 1);
            updateEmailList();
        }
    }
}

function updateEmailList() {
    const emailList = document.getElementById('emailList');
    const emailsHidden = document.getElementById('emailsHidden');
    
    emailList.innerHTML = currentEmails.map((email, index) => `
        <span class="badge me-1 mb-1">
            ${email}
            <button type="button" class="btn-close btn-close ms-2"
                    onclick="removeEmail(null, '${email}')">
            </button>
        </span>
    `).join('');
    
    emailsHidden.value = currentEmails.join(',');
}

function editConfig(id) {
    // Limpiar el estado anterior
    currentEmails = [];
    updateEmailList();
    
    // Cargar los datos de la configuración
    fetch(`/notificaciones/${id}`)
        .then(response => response.json())
        .then(config => {
            document.getElementById('configId').value = config.id;
            document.getElementById('tipo').value = config.tipo;
            currentEmails = [...config.emails];
            updateEmailList();
            modal.show();
        })
        .catch(error => {
            console.error('Error:', error);
            // Aquí podrías mostrar un mensaje de error al usuario
        });
}

function submitForm() {
    const form = document.getElementById('configForm');
    if (form.checkValidity()) {
        document.getElementById('emailsHidden').value = currentEmails.join(',');
        form.submit();
    } else {
        form.classList.add('was-validated');
    }
}

function deleteConfig(id) {
    // Guardamos el ID en el campo oculto
    document.getElementById('deleteConfigId').value = id;
    // Mostramos el modal
    deleteModal.show();
}

function confirmDelete() {
    const id = document.getElementById('deleteConfigId').value;
    
    // Mostramos el loader
    const loader = document.getElementById('globalLoader');
    if (loader) loader.style.display = 'flex';
    
    // Realizamos la eliminación
    fetch(`/notificaciones/${id}`, { 
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        }
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Error al eliminar la configuración');
        }
        window.location.reload();
    })
    .catch(error => {
        console.error('Error:', error);
        // Aquí podrías mostrar un mensaje de error
    })
    .finally(() => {
        if (loader) loader.style.display = 'none';
        deleteModal.hide();
    });
}

function resetForm() {
    document.getElementById('configForm').reset();
    document.getElementById('configId').value = '';
    currentEmails = [];
    updateEmailList();
    document.getElementById('formTitle').textContent = 'Nueva Configuración';
}

function isValidEmail(email) {
    return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email);
}