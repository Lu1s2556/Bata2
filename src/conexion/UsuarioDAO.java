/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    private String clave;

    public boolean validarLogin(String usuario, String contrasena) {
        boolean validado = false;
        
        Connection con = main.conectar();
        String sql = "SELECT * FROM usuario WHERE usuario = ? AND clave = ?";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, clave);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                validado = true;
            }
            
            rs.close();
            ps.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("🚫 Error al validar login: " + e.getMessage());
        }
        
        return validado;
    }

   
}