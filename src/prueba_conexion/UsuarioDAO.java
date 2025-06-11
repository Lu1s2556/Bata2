/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba_conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    public boolean validarLogin(String usuario, String clave) {
        boolean validado = false;

        Connection con = ConexionDB.conectar();
        if (con == null) {
            System.out.println("🚫 No se pudo establecer conexión.");
            return false;
        }

        String sql = "SELECT * FROM usuarios WHERE usuario = ? AND clave = ?";

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