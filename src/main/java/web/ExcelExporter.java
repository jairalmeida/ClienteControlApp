package web;
import jakarta.servlet.http.HttpServletResponse; // interfaz en la API de Servlets 
//que representa la respuesta HTTP enviada desde el servidor al cliente
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.IOException;
import java.util.List;
import modelo.Cliente;

public class ExcelExporter {

    private List<Cliente> clientes;

    public ExcelExporter(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public void export(HttpServletResponse response) throws IOException {
        /* Es una interfaz de Apache POI que representa un archivo de Excel en memoria.
        La interfaz Workbook es un tipo general que puede manejar tanto archivos de Excel en el formato
        .xls (antiguo) como .xlsx (nuevo)
        
        new XSSFWorkbook(): Esta es la creación del objeto XSSFWorkbook, que es una clase 
        que implementa la interfaz Workbook y se utiliza para trabajar con archivos .xlsx
        
        workbook: Es el nombre de la variable que contiene la referencia al objeto XSSFWorkbook. 
        Esta variable será la que usaremos para interactuar con el archivo Excel en memoria.
        */
        Workbook workbook = new XSSFWorkbook();
        //Agregar hojas de trabajo: Luego, puedes agregar hojas a tu archivo Excel (
        //llamadas Sheet en Apache POI
        Sheet sheet = workbook.createSheet("Clientes");

        // Crear encabezados
        Row headerRow = sheet.createRow(0);
        String[] columnHeaders = {"ID_Cliente", "Nombre", "Apellido", "Email", "Teléfono", "Saldo"};
        for (int i = 0; i < columnHeaders.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columnHeaders[i]);
            CellStyle style = workbook.createCellStyle();
            style.setAlignment(HorizontalAlignment.CENTER);
            cell.setCellStyle(style);
        }

        // Llenar datos
        int rowCount = 1;
        for (Cliente cliente : clientes) {
            Row row = sheet.createRow(rowCount++);
            row.createCell(0).setCellValue(cliente.getIdCliente());
            row.createCell(1).setCellValue(cliente.getNombre());
            row.createCell(2).setCellValue(cliente.getApellido());
            row.createCell(3).setCellValue(cliente.getEmail());
            row.createCell(4).setCellValue(cliente.getTelefono());
            row.createCell(5).setCellValue(cliente.getSaldo());
        }

        // Configurar el tipo de contenido de la respuesta
        //En el contexto de la exportación de Excel, lo usas para configurar y 
        //enviar el archivo al cliente como una descarga
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=clientes.xlsx");

        // Escribir el archivo al cliente
        //Una vez que has agregado todos los datos y configurado el archivo Excel en memoria, puedes escribirlo a un OutputStream (por ejemplo,
        //para enviarlo como respuesta al navegador o guardarlo en el sistema de archivos
        workbook.write(response.getOutputStream());
        workbook.close();
    }
}
