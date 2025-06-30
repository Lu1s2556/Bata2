package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;

public class conexionSQL {
    // Conexion con la base de datos
    Connection cn;
    public Connection conectar(){
        try {
            cn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/medicontrol", "medicontrol", "bata31@");
            System.out.println("Conexion exitosa");
        } catch (SQLException e) {
            System.out.println("Error de conexion" + e);
        }
        return cn;
        
    }
}
