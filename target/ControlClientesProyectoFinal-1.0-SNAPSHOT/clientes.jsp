<!doctype html>
<html data-bs-theme="dark">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Control de clientes</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <!-- Bootstrap´iconos -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">

    <style>
      /* Hacer que la página ocupe toda la altura de la ventana */
      html, body {
        height: 100%;  /* Asegura que la página ocupe toda la altura disponible */
        margin: 0;  /* Elimina márgenes por defecto */
      }

      /* Usar Flexbox para que el contenido principal ocupe todo el espacio */
      .content-wrapper {
        display: flex;
        flex-direction: column;
        min-height: 100%;  /* Esto hace que el contenedor ocupe el 100% de la altura de la ventana */
      }

      /* Hacer que el pie de página se quede al fondo */
      footer {
        margin-top: auto;  /* Esto empuja el pie de página hacia el fondo */
      }
    </style>
  </head>
  <body>
    <div class="content-wrapper">
      <!-- Cabecero -->
      <jsp:include page="/WEB-INF/paginas/comunes/cabecero.jsp"/>
      
      <!-- Botones de navegacion -->
      <jsp:include page="/WEB-INF/paginas/comunes/botonesNavegacion.jsp"/>
      
      <!-- Listado de Clientes -->
      <jsp:include page="/WEB-INF/paginas/cliente/listadoClientes.jsp"/>
      
      <!-- Modal agregar un cliente -->
      <jsp:include page="/WEB-INF/paginas/cliente/agregarCliente.jsp"/>
      
      
      <!-- Modal de filtro -->
      <jsp:include page="/WEB-INF/paginas/cliente/filtroModal.jsp"/>
      
   
      
      <!-- pie de pagina -->
      <jsp:include page="/WEB-INF/paginas/comunes/piePagina.jsp"/>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
  </body>
</html>
