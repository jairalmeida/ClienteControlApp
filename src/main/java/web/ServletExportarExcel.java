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
import java.util.List;
import modelo.Cliente;

/**
 *
 * @author jair-
 */
@WebServlet(name = "ServletExportarExcel", urlPatterns = {"/exportarExcel"})
public class ServletExportarExcel extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener datos de la base de datos
        ClienteDAO clienteDAO = new ClienteDAO();
        List<Cliente> clientes = clienteDAO.listar();

        // Crear instancia de ExcelExporter y exportar
        ExcelExporter excelExporter = new ExcelExporter(clientes);
        excelExporter.export(response);
    }

   /*
<a href="exportarExcel" class="btn btn-success">Exportar a Excel</a>


*/

}
