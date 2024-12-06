<section id="actions" class="py-4 mb-4">
    <div class="container">
        <div class="row">
            <div class="col-md-3">
                <button type="button" class="btn btn-primary btn-block w-100" data-bs-toggle="modal"
                        data-bs-target="#agregarClienteModal">
                    <i class="bi bi-plus-circle"></i>Agregar Cliente
                </button>
            </div>
            <!-- Cada botón tiene su propia columna col-md-3. Esto hace que ambos 
            botones se ubiquen horizontalmente en pantallas medianas (y más grandes)
            Ambos botones tienen la clase btn-block y w-100, lo que asegura que cada 
            uno ocupe el 100% del ancho de su columna respectiva
            El ícono de Excel se obtiene mediante bi bi-file-earmark-excel
            -->
            <div class="col-md-3">
                <a href="${pageContext.request.contextPath}/ServletControlador?accion=descargarExcel" class="btn btn-success btn-block w-100">
                    <i class="bi bi-file-earmark-excel"></i> Descargar Excel
                </a>
            </div>
            <!-- Boton par filtrar busqueda -->        
            <div class="col-md-3">
                <button type="button" class="btn btn-primary btn-block w-100" data-bs-toggle="modal"
                        data-bs-target="#filtroModal">
                    <i class="bi bi-funnel"></i> Filtrar Búsqueda
                </button>
            </div>


        </div>
    </div>

</section>