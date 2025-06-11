/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package conexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class main {

    // Cambia el nombre de la base de datos si es necesario
    private static final String URL = "jdbc:mysql://localhost:3306/medicontrol?zeroDateTimeBehavior=CONVERT_TO_NULL";
    private static final String USER = "medicontrol";
    private static final String PASSWORD = "bata31@";

    public static Connection conectar() {
        Connection conexion = null;

        try {
            // Cargar el driver (en las versiones modernas ya no es obligatorio, pero lo pongo por seguridad)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establecer la conexión
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
