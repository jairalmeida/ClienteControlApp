<%@taglib uri="http://java.sun.com/jsp/jstl/core"prefix="c" %>
<!doctype html>
<html data-bs-theme="dark">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Resultados Filtrados</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
  </head>
  <body>
    <div class="container">
      <h1>Resultados Filtrados</h1>
      
      <!-- Mostrar los resultados filtrados -->
      <table class="table">
        <thead>
          <tr>
            <th scope="col">ID</th>
            <th scope="col">Nombre</th>
            <th scope="col">Apellido</th>
            <th scope="col">Email</th>
            <th scope="col">Telefono</th>
            <th scope="col">Saldo</th>
          </tr>
        </thead>
        <tbody>
          <!-- Usamos JSTL para iterar sobre los resultados -->
          <c:forEach var="cliente" items="${cliente}">
            <tr>
              <td>${cliente.idCliente}</td>
              <td>${cliente.nombre}</td>
              <td>${cliente.apellido}</td>
              <td>${cliente.email}</td>
              <td>${cliente.telefono}</td>
              <td>${cliente.saldo}</td>
            </tr>
          </c:forEach>
        </tbody>
      </table>

      <!-- Botón para regresar a la página principal -->
       <a href="${pageContext.request.contextPath}/clientes.jsp" class="btn btn-secondary">Regresar</a> 
    </div>
  </body>
</html>
