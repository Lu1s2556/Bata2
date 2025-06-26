/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexion;

import java.sql.*;

/**
 *
 * @author Burrx
 */
public class DAOlogin {
    
    private Connection cn;
    
    public DAOlogin(Connection cn){
        this.cn = cn;
    }
    
    
    public boolean ValidarUsuario(String usuario, String contraseña){
        boolean veri = false;
        String SQL = "SELECT * FROM login WHERE usuario = '"+usuario+"'";
        
        try(PreparedStatement PS = cn.prepareStatement(SQL);
            ResultSet rs = PS.executeQuery();) {
            
            if(rs.next()){
                String user = rs.getString("usuario");
                String pass = rs.getString("contraseña");
                
                
                if (pass.equals(contraseña) && user.equals(usuario)){
                    veri = true;
                }
            }
            
        } catch (Exception e) {
            veri = false;
        }
        return veri;
    }
}
