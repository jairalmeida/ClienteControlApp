package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Cliente;

public class ClienteDAO {
    //CRUD Create - Read - Update - Delete

    private static final String SQL_SELECT = "SELECT idcliente, nombre, apellido, email"
            + ",telefono, saldo FROM clientes ";

    private static final String SQL_INSERT
            = "INSERT INTO clientes(nombre, apellido, email, telefono, saldo) VALUES(?,?,?,?,?)";

    private static final String SQL_SELECT_BY_ID
            = "SELECT idcliente, nombre, apellido, email, telefono, saldo FROM clientes WHERE idcliente = ?";

    private static final String SQL_UPDATE
            = "UPDATE clientes SET nombre = ?, apellido = ?, email = ?, telefono = ?, saldo = ? WHERE idcliente = ?";

    private static final String SQL_DELETE = "DELETE FROM clientes WHERE idcliente = ?";

   
    
   

    public List<Cliente> listar() {
        List<Cliente> clientes = new ArrayList<>();
        try (Connection conn = Conexion.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(SQL_SELECT);
            ResultSet rs = stmt.executeQuery();

            //Iteramos los clientes de la bd
            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getInt("idcliente"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("email"),
                        rs.getString("telefono"),
                        rs.getDouble("saldo")
                );

                clientes.add(cliente);

            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return clientes;
    }

    public int insertar(Cliente cliente) {
        int rows = 0;
        try (Connection conn = Conexion.getConnection(); PreparedStatement stmt = conn.prepareStatement(SQL_INSERT)) {
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getApellido());
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getTelefono());
            stmt.setDouble(5, cliente.getSaldo());

            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }

        return rows;
    }

    public Cliente encontrarCliente(Cliente cliente) {
        //Analizar bien el codigo

        try (Connection conn = Conexion.getConnection(); PreparedStatement stmt = conn.prepareStatement(SQL_SELECT_BY_ID)) {

            stmt.setInt(1, cliente.getIdCliente());
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    cliente.setNombre(rs.getString("nombre"));
                    cliente.setApellido(rs.getString("apellido"));
                    cliente.setEmail(rs.getString("email"));
                    cliente.setTelefono(rs.getString("telefono"));
                    cliente.setSaldo(rs.getDouble("saldo"));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return cliente;
    }

    public int actualizar(Cliente cliente) {
        int rows = 0;
        try (Connection conn = Conexion.getConnection(); PreparedStatement stmt = conn.prepareStatement(SQL_UPDATE)) {
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getApellido());
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getTelefono());
            stmt.setDouble(5, cliente.getSaldo());
            //El 6to elemento es el ID, recordemos que va en orden en la sentencia SQL
            stmt.setInt(6, cliente.getIdCliente());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("El error fue: \n");
            ex.printStackTrace(System.out);
        }
        return rows;
    }
    
     public boolean eliminar(int idCliente) {
        boolean exito = false;

        try (Connection conn = Conexion.getConnection(); PreparedStatement stmt = conn.prepareStatement(SQL_DELETE);) {

            stmt.setInt(1, idCliente);
            int rows = stmt.executeUpdate();

            if (rows > 0) {
                exito = true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        return exito;
    }
     //REVISION
public List<Cliente> filtrar(String campoFiltro, String terminoBusqueda) {
    List<Cliente> clientesFiltrados = new ArrayList<>();
    // Validamos que el campoFiltro sea uno válido
    if (!campoFiltro.equals("nombre") && !campoFiltro.equals("apellido") && !campoFiltro.equals("email")) {
        System.out.println("Campo de filtro no válido: " + campoFiltro);
        return clientesFiltrados; // Retorna lista vacía si el campo no es válido
    }

    String query = "SELECT * FROM clientes WHERE " + campoFiltro + " LIKE ?";
    try (Connection conn = Conexion.getConnection(); 
         PreparedStatement ps = conn.prepareStatement(query)) {
         ps.setString(1, "%" + terminoBusqueda + "%");
         ResultSet rs = ps.executeQuery();
         while (rs.next()) {
             Cliente cliente = new Cliente();
             cliente.setIdCliente(rs.getInt("idcliente"));
             cliente.setNombre(rs.getString("nombre"));
             cliente.setApellido(rs.getString("apellido"));
             cliente.setEmail(rs.getString("email"));
             cliente.setTelefono(rs.getString("telefono"));
             cliente.setSaldo(rs.getDouble("saldo"));
             clientesFiltrados.add(cliente);
         }
     } catch (SQLException e) {
         e.printStackTrace(System.out);
     }
     return clientesFiltrados;
 }

     //REVSION
     
}
