/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package web;

import datos.ClienteDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;
import modelo.Cliente;

/**
 *
 * @author jair-
 */
@WebServlet(name = "ServletControlador", urlPatterns = {"/ServletControlador"})
public class ServletControlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //si no se pasa un parámetro accion en la URL (como en el caso cuando accedes directamente a la página
        //sin especificar una acción), el valor de accion será "listar" por defecto
        String accion = Optional.ofNullable(request.getParameter("accion")).orElse("listar");

        switch (accion) {
            case "listar" ->
                this.listarClientes(request, response);
            case "editar" ->
                this.editarClientes(request, response);

            //CBM
            case "eliminar" ->
                this.eliminarCliente(request, response);

            case "descargarExcel" ->
                response.sendRedirect(request.getContextPath() + "/exportarExcel"); // Redirige al servlet de Excel

           

            default ->
                this.listarClientes(request, response);
        }
    }

    
    //REVISION
    private void filtrarClientes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String campoFiltro = request.getParameter("campoFiltro"); // Nombre, Apellido, Correo
        String terminoBusqueda = request.getParameter("terminoBusqueda"); // El valor a buscar

        System.out.println("Nombre filtrado: " + campoFiltro); 
            System.out.println("Saldo filtrado: " + terminoBusqueda);
        
        // Usar el DAO para filtrar los clientes según el campo y el término de búsqueda
        List<Cliente> clientesFiltrados = new ClienteDAO().filtrar(campoFiltro, terminoBusqueda);

        // Pasar los resultados a la sesión o al JSP
        HttpSession sesion = request.getSession();
        sesion.setAttribute("cliente", clientesFiltrados);
        sesion.setAttribute("totalClientes", clientesFiltrados.size());
        
        
        System.out.println("Clientes filtrados: " + clientesFiltrados.size());

        
        
        // Redirigir al listado de clientes (la vista que muestra los clientes filtrados)
        request.getRequestDispatcher("/WEB-INF/paginas/cliente/clientesFiltrados.jsp").forward(request, response);
    }
    //REVISION
    
    
    
    /*El servlet no crea un objeto Cliente. En lugar de eso, obtiene una lista de objetos Cliente de la base de datos a través del DAO y luego los maneja (por ejemplo, guardándolos en la sesión).
    El proceso de crear los objetos Cliente ocurre en el método listar de la clase ClienteDAO, donde se ejecutan las consultas SQL y se crean los objetos.*/
    private void listarClientes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Cliente> clientes = new ClienteDAO().listar();
        //Suelta al output, para terminos de prueba:
        System.out.println("clientes: " + clientes);
        //Obtenemos la sesion
        HttpSession sesion = request.getSession();
        sesion.setAttribute("clientes", clientes);
        sesion.setAttribute("totalClientes", clientes.size());
        sesion.setAttribute("saldoTotal", this.calcularSaldoTotal(clientes));

        //Enviamos la respuesta al jsp de clientes
        request.getRequestDispatcher("clientes.jsp").forward(request, response);
    }

    //Esta seria la forma clasica de hacer el metodo
    private double calcularSaldoTotal(List<Cliente> clientes) {
//        double saldoTotal = 0.0;
//        for(Cliente cliente: clientes){
//            saldoTotal += cliente.getSaldo();
//        }
//        return saldoTotal;

        //Forma actual
        return clientes.stream().mapToDouble(Cliente::getSaldo).sum();
    }

    private void editarClientes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
        //Revisar linea de codigo con chatgpt
        Cliente cliente = new ClienteDAO().encontrarCliente(new Cliente(idCliente));
        //Compartimos el objeto de tipo cliente que hemos recuperado de la bd segun la id 
        request.setAttribute("cliente", cliente);

        System.out.println("Cliente recuperado: " + cliente);
        //Recuperamos el jsp a editar

        String jspEditar = "/WEB-INF/paginas/cliente/editarCliente.jsp";
        request.getRequestDispatcher(jspEditar).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = Optional.ofNullable(request.getParameter("accion")).orElse("listar");
        switch (accion) {
            case "insertar" ->
                this.insertarClientes(request, response);
            case "modificar" ->
                this.modificarClientes(request, response);
            
                //CUESTIONAR A CHATGPT POR QUE SOLO FUNCIONA EN POST
             case "filtrar" ->
                this.filtrarClientes(request, response);    

            default ->
                this.insertarClientes(request, response);
        }
    }

    //Codigo mio
    private void eliminarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Obtener el id de los parametros de la url
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));

        // Llamar al método de DAO para eliminar el cliente de la base de datos
        new ClienteDAO().eliminar(idCliente);
        this.listarClientes(request, response);
    }

    private void insertarClientes(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Procesamos los datos del formulario
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");
        double saldo = Double.parseDouble(request.getParameter("saldo"));

        //Creamos un objeto de tipo cliente
        //Recordemos que no es necesario crear un objeto con id, el sql se encargará de incrementarla
        Cliente cliente = new Cliente(nombre, apellido, email, telefono, saldo);
        /*ESTUDIAR ESTA LINEA */ new ClienteDAO().insertar(cliente);

        //Listamos los clientes
        this.listarClientes(request, response);
    }

    /*
sí se crea un objeto Cliente en el servlet, porque tomamos los datos enviados desde el formulario (idCliente, nombre, apellido, etc.) y creando un nuevo objeto Cliente con esos valores:
Este objeto Cliente se pasa luego al ClienteDAO para actualizar la base de datos:
new ClienteDAO().actualizar(cliente);
Aquí, el servlet sí crea el objeto Cliente para manipular los datos del cliente y luego realizar la actualización en la base de datos.


     */
    private void modificarClientes(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Procesamos los datos del formulario
        //En este caso, si es necesario recuperar la id del cliente que hemos asignado desde los prm

        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");
        Double saldo = Double.parseDouble(request.getParameter("saldo"));

        Cliente cliente = new Cliente(idCliente, nombre, apellido, email, telefono, saldo);
        //Nuevo objeto de la clase DAO y llamamos el metodo actualizar
        //new ClienteDAO().actualizar(cliente);
        //Este objeto Cliente se pasa luego al ClienteDAO para actualizar la base de datos
        new ClienteDAO().actualizar(cliente);

        //Redirigimos al caso de listar
        this.listarClientes(request, response);

    }

}
