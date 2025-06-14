package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;

public class conexionSQL {
    Connection cn;
    public Connection conectar(){
        try {
            // Esto de la contraseña y usuario estan con el xampp por defecto, si la base la tienen en un usuario especifico pues la ponen.
            cn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/medicontrol", "medicontrol", "bata31@");
            System.out.println("Conexion exitosa");
        } catch (SQLException e) {
            System.out.println("Error de conexion" + e);
        }
        return cn;
        
    }
}
