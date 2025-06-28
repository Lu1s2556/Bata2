
package conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class DAOrecipes {
    
    private Connection cn;

    public DAOrecipes(Connection cn) {
        this.cn = cn;
    }
    
    
    public void cargarDatosRecetas(JTable tabla) {
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        model.setRowCount(0); // limpiar la tabla antes de cargar

        String sql = "SELECT r.id_recipe, r.cedula, p.nombre " +
                     "FROM recipe r INNER JOIN paciente p ON r.cedula = p.cedula";

        try (PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Object[] fila = {
                    rs.getInt("id_recipe"),
                    rs.getString("cedula"),
                    rs.getString("nombre")
                };
                model.addRow(fila);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error al cargar recetas: " + ex.getMessage());
        }
    
    }

        
}
    
    

