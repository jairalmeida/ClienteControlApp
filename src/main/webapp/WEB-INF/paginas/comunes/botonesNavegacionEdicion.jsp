<section id="accionesEditar" class="py-4 mb-4">
    <div class="container">
        <div class ="row">
            <div class="col-md-3">
                <a href="${pageContext.request.contextPath}/" class="btn btn-secondary btn-block w-100">
                    <i class="bi bi-arrow-left">Regresar al inicio</i>
                </a>
            </div>
            <div class="col-md-3">
                <button type="submit" class="btn btn-success btn-block w-100">
                    <i class="bi bi-check-lg">Guardar Cliente</i>
                </button>
            </div>
            <div class="col-md-3">
                <a href="${pageContext.request.contextPath}/ServletControlador?accion=eliminar&idCliente=${cliente.idCliente}"
                    class="btn btn-danger btn-block w-100">
                    <i class="bi bi-trash">Eliminar Cliente </i>
                </a>
            </div>        
        </div>
</section>