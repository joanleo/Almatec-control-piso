const ALERT_SETTINGS = {
    success: {
        duration: 3000, 
        icon: '<i class="fa-solid fa-circle-check me-2"></i>',
        animation: 'fade-in'
    },
    warning: {
        duration: 8000,
        icon: '<i class="fa-solid fa-triangle-exclamation me-2"></i>',
        animation: 'fade-in'
    },
    danger: {
        duration: 10000,
        icon: '<i class="fa-solid fa-circle-xmark me-2"></i>',
        animation: 'fade-in'
    },
    info: {
        duration: 4000,
        icon: '<i class="fa-solid fa-circle-info me-2"></i>',
        animation: 'fade-in'
    }
};

const styleSheet = document.createElement('style');
styleSheet.textContent = `
    @keyframes fade-in {
        from { opacity: 0; transform: translateY(-20px) translateX(-50%); }
        to { opacity: 1; transform: translateY(0) translateX(-50%); }
    }
    
    @keyframes fade-out {
        from { opacity: 1; transform: translateY(0) translateX(-50%); }
        to { opacity: 0; transform: translateY(-20px) translateX(-50%); }
    }
    
    .alert-custom {
        animation: fade-in 0.3s ease-out forwards;
        transition: opacity 0.3s ease-out;
    }
	
	.alert-wrapper {
        display: flex;
        align-items: center; /* Centra verticalmente el contenido */
        width: 100%;
        gap: 1rem; /* Espacio consistente entre elementos */
    }

    .alert-icon {
        display: flex;
        align-items: center;
        align-self: stretch; /* Se estira para ocupar todo el alto disponible */
        padding-top: 0.25rem; /* Pequeño ajuste fino del padding */
    }

    .alert-message {
        white-space: pre-line !important;
        line-height: 1.6 !important;
        margin: 0;
    }

    .alert-content {
        flex: 1;
        min-width: 0; /* Previene desbordamiento en flexbox */
    }

    .alert-close {
        display: flex;
        align-items: center;
        align-self: stretch;
    }
    
    .alert-custom.hiding {
        animation: fade-out 0.3s ease-out forwards;
    }
`;
document.head.appendChild(styleSheet);

/**
 * Muestra una alerta con estilos y comportamientos mejorados
 * @param {string} mensaje - El mensaje a mostrar
 * @param {string} tipo - El tipo de alerta ('success', 'warning', 'danger', 'info')
 */
function mostrarAlert(mensaje, tipo) {
    // Eliminamos alertas existentes del mismo tipo
    const existingAlerts = document.querySelectorAll(`.alert-${tipo}`);
    existingAlerts.forEach(alert => alert.remove());
	
	const formattedMessage = formatAlertMessage(mensaje);

    // Creamos el contenedor de la alerta
    const alertElement = document.createElement('div');
    alertElement.className = `alert alert-${tipo} alert-dismissible alert-custom`;
    alertElement.role = 'alert';
    
    // Aplicamos los estilos
    Object.assign(alertElement.style, {
		zIndex: '10000',
        position: 'fixed',
        top: '7rem',
        left: '50%',
        transform: 'translateX(-50%)',
        width: '90rem',
        maxWidth: '90%',
        boxShadow: '0 4px 6px rgba(0, 0, 0, 0.1)',
        padding: '1rem 1.25rem'
    });

    // Construimos el contenido de la alerta con el ícono
    alertElement.innerHTML = `
		<div class="alert-wrapper">
			<div class="alert-icon">
	        	${ALERT_SETTINGS[tipo].icon}
			</div>
	        <div class="alert-content">
	            <pre class="alert-message mb-0">${formattedMessage}</pre>
	        </div>
			<div class="alert-close">
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
		</div>
    `;

    // Agregamos al documento
    document.body.appendChild(alertElement);
    
    // Configuramos el auto-cierre si no es una alerta de tipo danger
    if (tipo !== 'danger') {
        setTimeout(() => {
            alertElement.classList.add('hiding');
            setTimeout(() => alertElement.remove(), 300);
        }, ALERT_SETTINGS[tipo].duration);
    }

    // Agregamos manejador de clic para cerrar al hacer clic en cualquier parte
    alertElement.addEventListener('click', () => {
        alertElement.classList.add('hiding');
        setTimeout(() => alertElement.remove(), 300);
    });
}

/**
 * Formatea el mensaje para manejar múltiples líneas y referencias
 * @param {string} mensaje - El mensaje original
 * @returns {string} - El mensaje formateado
 */
function formatAlertMessage(mensaje) {
    if (mensaje.includes('exitosamente')) {
        const lines = mensaje.split('.')
            .filter(line => line.trim())
            .map(line => line.trim());
            
        // Agregamos el punto final a cada línea excepto la última
        return lines
            .map((line, index) => 
                index === lines.length - 1 ? line : line + '.')
            .join('\n');
    }
    return mensaje;
}

/**
 * Muestra el loader con un mensaje personalizable
 * @param {string} message - El mensaje a mostrar con el loader
 */
function showLoader(message = 'Procesando...') {
    const loader = document.getElementById('globalLoader');
    if (loader) {
        const loaderText = loader.querySelector('.loader-text');
        loaderText.textContent = message;
        
        loader.style.display = 'flex';
        loader.style.opacity = '0';
        requestAnimationFrame(() => {
            loader.style.transition = 'opacity 0.3s ease-in-out';
            loader.style.opacity = '1';
        });
    }
}

/**
 * Oculta el loader global con una animación de desvanecimiento
 */
function hideLoader() {
    const loader = document.getElementById('globalLoader');
    if (loader) {
        loader.style.opacity = '0';
        setTimeout(() => {
            loader.style.display = 'none';
        }, 300);
    }
}

// Funciones para el loader de modales
function showModalLoader(modalElement) {
    const loader = document.getElementById('modalLoader');
    if (loader && modalElement) {
        modalElement.appendChild(loader);
        loader.style.display = 'flex';
    }
}

function hideModalLoader() {
    const loader = document.getElementById('modalLoader');
    if (loader) {
        loader.style.display = 'none';
    }
}

// Función para manejar errores
function handleError(error, title = 'Error') {
    hideLoader();
    hideModalLoader();
    
    console.error(error);
    
    // Aquí puedes usar tu sistema de notificaciones preferido
    // Por ejemplo, usando Bootstrap Toast o Alert
    alert(`${title}: ${error.message || 'Ocurrió un error inesperado'}`);
}