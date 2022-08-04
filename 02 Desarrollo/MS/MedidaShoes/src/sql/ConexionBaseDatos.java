package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.*;

public class ConexionBaseDatos {

    public static String url = "jdbc:mysql://localhost/medidashoes";
    public static String usuario = "root";
    public static String contraseña = "123456"; //Cambiar contraseña
    public static String clase = "com.mysql.cj.jdbc.Driver";//antes com.mysql.jdbc.Driver

    public static Connection conectar() {
        Connection conexion = null;
        try {
            Class.forName(clase);
            conexion = (Connection) DriverManager.getConnection(url, usuario, contraseña);
            System.out.println("Conexion lograda");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
        }
        return conexion;
    }
}
