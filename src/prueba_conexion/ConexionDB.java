/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba_conexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    private static final String URL = "jdbc:mysql://localhost:3306/medicontrol?zeroDateTimeBehavior=CONVERT_TO_NULL";
    private static final String USER = "medicontrol";
    private static final String PASSWORD = "bata31@";

    public static Connection conectar() {
        Connection conexion = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Conexión exitosa a la base de datos.");
        } catch (ClassNotFoundException e) {
            System.out.println("🚫 Error al cargar el driver: " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("🚫 Error al conectar: " + e.getMessage());
        }

        return conexion;
    }
}