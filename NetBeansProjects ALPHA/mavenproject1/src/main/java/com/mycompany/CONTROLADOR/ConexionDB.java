package com.mycompany.CONTROLADOR;

import com.mycompany.DAO.Reserva;
import com.mycompany.MODELO.ENTIDADES.Producto;
import com.mycompany.MODELO.ENTIDADES.Usuario;
import java.sql.*;
import java.util.ArrayList;

public class ConexionDB {
    private Connection conexion;

    // Constructor para establecer la conexión
    public ConexionDB() {
        try {
            // Cambia "tienda_virtual" a "buffet" (asegúrate de que la URL sea correcta)
            this.conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/buffet", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Registrar un nuevo usuario en la base de datos
    public boolean registrarUsuario(Usuario usuario) {
        try {
            String sql = "INSERT INTO usuarios (nombre, contraseña, es_admin) VALUES (?, ?, ?)";
            PreparedStatement stmt = conexion.prepareStatement(sql);
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getContraseña());
            stmt.setBoolean(3, usuario.esAdmin());
            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Verificar si un usuario existe en la base de datos
    public Usuario verificarUsuario(String nombre, String contraseña) {
        try {
            String sql = "SELECT * FROM usuarios WHERE nombre = ? AND contraseña = ?";
            PreparedStatement stmt = conexion.prepareStatement(sql);
            stmt.setString(1, nombre);
            stmt.setString(2, contraseña);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                boolean esAdmin = rs.getBoolean("es_admin");
                return new Usuario(nombre, contraseña, esAdmin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Cargar todos los productos desde la base de datos
    public ArrayList<Producto> cargarProductosDesdeDB() {
        ArrayList<Producto> productos = new ArrayList<>();
        try {
            // Consulta SQL correcta para cargar todos los productos
            String sql = "SELECT * FROM productos";
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            // Recorremos los resultados obtenidos de la consulta
            while (rs.next()) {
                int id = rs.getInt("id");  // Obtenemos el ID del producto
                String nombre = rs.getString("nombre");  // Obtenemos el nombre del producto
                double precio = rs.getDouble("precio");  // Obtenemos el precio del producto
                int cantidadDisponible = rs.getInt("cantidad");  // Obtenemos la cantidad disponible (la columna correcta)
                
                // Creamos un nuevo objeto Producto y lo agregamos a la lista
                productos.add(new Producto(id, nombre, precio, cantidadDisponible));
            }
        } catch (SQLException e) {
            e.printStackTrace();  // Si ocurre un error, lo imprimimos
        }
        return productos;  // Devolvemos la lista de productos
    }

    // Insertar un nuevo producto en la base de datos
    public void insertarProducto(Producto producto) {
        try {
            String sql = "INSERT INTO productos (nombre, precio, cantidad) VALUES (?, ?, ?)";
            PreparedStatement stmt = conexion.prepareStatement(sql);
            stmt.setString(1, producto.getNombre());
            stmt.setDouble(2, producto.getPrecio());
            stmt.setInt(3, producto.getCantidadDisponible());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Actualizar un producto existente en la base de datos
    public void actualizarProducto(Producto producto) {
        try {
            String sql = "UPDATE productos SET nombre = ?, precio = ?, cantidad = ? WHERE id = ?";
            PreparedStatement stmt = conexion.prepareStatement(sql);
            stmt.setString(1, producto.getNombre());
            stmt.setDouble(2, producto.getPrecio());
            stmt.setInt(3, producto.getCantidadDisponible());
            stmt.setInt(4, producto.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Borrar un producto de la base de datos por su ID
    public void borrarProducto(int id) {
        try {
            String sql = "DELETE FROM productos WHERE id = ?";
            PreparedStatement stmt = conexion.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // Insertar una reserva en la base de datos
 public void confirmarReserva(Reserva reserva) {
    try {
        String sql = "INSERT INTO reservas (producto_id, cantidad, horario, usuario_id) VALUES (?, ?, ?, ?)";
        PreparedStatement stmt = conexion.prepareStatement(sql);
        stmt.setInt(1, reserva.getProducto().getId());
        stmt.setInt(2, reserva.getCantidad());

        // Convierte LocalDateTime a Timestamp
        stmt.setTimestamp(3, Timestamp.valueOf(reserva.getHorario()));
        stmt.setInt(4, reserva.getUsuarioId());

        // Ejecuta la consulta de inserción
        int filasAfectadas = stmt.executeUpdate();
        if (filasAfectadas > 0) {
            System.out.println("Reserva insertada correctamente.");
        } else {
            System.out.println("No se pudo insertar la reserva.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    // Cerrar la conexión con la base de datos
    public void cerrarConexion() {
        try {
            if (conexion != null) {
                conexion.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}