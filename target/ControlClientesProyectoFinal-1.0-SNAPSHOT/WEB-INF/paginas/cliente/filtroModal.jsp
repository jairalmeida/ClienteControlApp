<!-- Modal de filtro de búsqueda -->
<div class="modal fade" id="filtroModal" tabindex="-1" aria-labelledby="filtroModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="filtroModalLabel">Filtrar Clientes</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            
                <form action="${pageContext.request.contextPath}/ServletControlador?accion=filtrar" method="POST" class="was-validated">
                 <div class="modal-body">   
                    <div class="mb-3">
                        <label for="campoFiltro" class="form-label">Selecciona un campo de búsqueda:</label>
                        <select class="form-select" id="campoFiltro" name="campoFiltro" required>
                            <option value="nombre">Nombre</option>
                            <option value="apellido">Apellido</option>
                            <option value="correo">Correo</option>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="terminoBusqueda" class="form-label">Ingresa el valor a buscar:</label>
                        <input type="text" class="form-control" id="terminoBusqueda" name="terminoBusqueda" required>
                    </div>
                    <button type="submit" class="btn btn-primary">Buscar</button>
                
            </div>
         </form>        
        </div>
    </div>
</div>
