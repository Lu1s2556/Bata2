package conexion;

import java.sql.Connection;
import java.sql.DriverManager;

public class conexionSQL {
    Connection cn;
    public Connection conectar(){
        try {
            // Esto de la contraseña y usuario estan con el xampp por defecto, si la base la tienen en un usuario especifico pues la ponen.
            cn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=CONVERT_TO_NULL", "root", "");
            System.out.println("Conexion exitosa");
        } catch (Exception e) {
            System.out.println("Error de conexion" + e);
        }
        return cn;
        
    }
}
