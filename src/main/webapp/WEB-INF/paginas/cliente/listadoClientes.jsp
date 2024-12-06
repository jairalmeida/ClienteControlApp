<!-- Listado Clientes -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<section id="clientes">
    <div class="container">
        <div class="row">
            <div class="col-md-9">
                <div class="card">
                    <div class="card-header">
                        <h4>Listado de Clientes</h4>
                    </div>
                    <table class="table table-stripe">
                        <thead class="thead-dark">
                            <tr>
                                <th>#</th>
                                <th>Nombre</th>
                                <th>Saldo</th><!-- comment -->
                                <th>Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="cliente" items="${clientes}" varStatus="status">
                                <tr>
                                    <td>${status.count}</td>
                                    <td>${cliente.nombre} ${cliente.apellido}</td>
                                    <td>
                                        <fmt:formatNumber value="${cliente.saldo}" type="currency" currencySymbol="$"/>
                                    </td>
                                    <td>
                                        <a href="${pageContext.request.contextPath}/ServletControlador?accion=editar&idCliente=${cliente.idCliente}"
                                           class="btn btn-secondary btn-sm">
                                            <i class="bi bi-pencil">Editar</i></a>
                                        <a href="${pageContext.request.contextPath}/ServletControlador?accion=eliminar&idCliente=${cliente.idCliente}" 
                                           class="btn btn-danger btn-sm">
                                            <i class="bi bi-trash">Eliminar</i>
                                        </a>

                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <!-- Totales -->
            <div class="col-md-3">
                <div class="card text-center bg-danger text-white mb-3">
                    <div class="card-body">
                        <h3>Saldo total</h3> 
                        <h4 class="display-6">
                            <fmt:formatNumber value="${saldoTotal}" type="currency" currencySymbol="$"/>
                        </h4>
                    </div>
                </div>
                        <div class="card text-center bg-success text-white mb-3">
                            <div class="card-body">
                                <h3>Total Cliente</h3>
                                <h4 class="display-6">
                                    <i class="bi bi-people"></i>${totalClientes}  
                                </h4>
                            </div>
                            
                        </div>
                        
            </div>
        </div>
        
    </div>
</section>
