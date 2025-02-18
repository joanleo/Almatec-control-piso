document.addEventListener('DOMContentLoaded', function() {
    // Debug: Contar cuántos botones toggle hay
    console.log('Toggle buttons found:', document.querySelectorAll('.toggle-btn').length);

    // Inicializar todos los botones toggle
    document.querySelectorAll('.toggle-btn').forEach(button => {
        // Debug: Mostrar información del botón
        console.log('Button target:', button.getAttribute('data-bs-target'));
        
        button.addEventListener('click', function(e) {
            e.preventDefault();
            
            // Obtener el elemento collapse
            const targetId = this.getAttribute('data-bs-target');
            const collapseElement = document.querySelector(targetId);
            
            // Debug: Verificar si se encontró el elemento collapse
            console.log('Collapse element found:', collapseElement !== null);
            
            if (collapseElement) {
                // Obtener o crear la instancia de collapse
                let bsCollapse = bootstrap.Collapse.getInstance(collapseElement);
                if (!bsCollapse) {
                    bsCollapse = new bootstrap.Collapse(collapseElement, {
                        toggle: false
                    });
                }
                
                // Toggle del collapse
                bsCollapse.toggle();
                
                // Toggle del icono
                const icon = this.querySelector('i');
                if (icon) {
                    icon.classList.toggle('fa-rotate-90');
                }
            }
        });
    });

    // Manejar eventos de collapse para los iconos
    document.querySelectorAll('[data-bs-toggle="collapse"]').forEach(button => {
        const targetId = button.getAttribute('data-bs-target');
        const collapseElement = document.querySelector(targetId);
        
        if (collapseElement) {
            collapseElement.addEventListener('show.bs.collapse', function () {
                const icon = button.querySelector('i');
                if (icon) icon.classList.add('fa-rotate-90');
            });

            collapseElement.addEventListener('hide.bs.collapse', function () {
                const icon = button.querySelector('i');
                if (icon) icon.classList.remove('fa-rotate-90');
            });
        }
    });
});