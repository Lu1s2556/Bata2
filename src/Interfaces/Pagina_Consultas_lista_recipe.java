
package Interfaces;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import conexion.conexionSQL;



public class Pagina_Consultas_lista_recipe extends javax.swing.JFrame {
    conexionSQL con = new conexionSQL();
    Connection cn = con.conectar();
  

    
    public Pagina_Consultas_lista_recipe(int idrecipe) {
        setUndecorated(true);
        initComponents();
        cargarDatosRecipe(idrecipe);
    }
    
    private void cargarDatosRecipe(int idRecipe) {
        String sql = 
          "SELECT r.cedula, p.nombre, p.apellido, r.recipe " +
          "FROM recipe r JOIN paciente p ON r.cedula = p.cedula " +
          "WHERE r.id_recipe = ?";
        try (PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idRecipe);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    txtCedula.setText(rs.getString("cedula"));
                    nombre_txt.setText(rs.getString("nombre"));
                    apellido_txt1.setText(rs.getString("apellido"));
                    jTextArea1.setText(rs.getString("recipe"));
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                "Error al cargar datos de la receta: " + ex.getMessage());
        }
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        txtCedula = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        nombre_txt = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        apellido_txt = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        apellido_txt1 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        btnVolverMenu = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(177, 234, 242));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBackground(new java.awt.Color(76, 207, 225));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setBackground(new java.awt.Color(255, 255, 255));
        jLabel12.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Cedula:");
        jPanel4.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 90, -1, -1));

        txtCedula.setBackground(new java.awt.Color(255, 255, 255));
        txtCedula.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        txtCedula.setForeground(new java.awt.Color(76, 207, 225));
        txtCedula.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel4.add(txtCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 90, 159, -1));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nombre(s):");
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 130, -1, 20));

        nombre_txt.setBackground(new java.awt.Color(255, 255, 255));
        nombre_txt.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        nombre_txt.setForeground(new java.awt.Color(76, 207, 225));
        nombre_txt.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel4.add(nombre_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 130, 160, -1));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Edad:");
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 130, -1, -1));

        apellido_txt.setBackground(new java.awt.Color(255, 255, 255));
        apellido_txt.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        apellido_txt.setForeground(new java.awt.Color(76, 207, 225));
        apellido_txt.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel4.add(apellido_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 130, 40, -1));

        jLabel11.setBackground(new java.awt.Color(255, 255, 255));
        jLabel11.setFont(new java.awt.Font("Verdana", 1, 48)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Consultas");
        jPanel4.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 10, 280, -1));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Apellido(s):");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 130, -1, -1));

        apellido_txt1.setBackground(new java.awt.Color(255, 255, 255));
        apellido_txt1.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        apellido_txt1.setForeground(new java.awt.Color(76, 207, 225));
        apellido_txt1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel4.add(apellido_txt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 130, 160, -1));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(-100, 0, 840, 180));

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Recipe:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 180, 100, -1));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, -1, -1));

        jTextArea1.setBackground(new java.awt.Color(255, 255, 255));
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jTextArea1.setForeground(new java.awt.Color(76, 207, 225));
        jTextArea1.setRows(5);
        jTextArea1.setPreferredSize(new java.awt.Dimension(392, 119));
        jScrollPane1.setViewportView(jTextArea1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 210, 560, 340));

        btnVolverMenu.setBackground(new java.awt.Color(255, 255, 255));
        btnVolverMenu.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        btnVolverMenu.setForeground(new java.awt.Color(76, 207, 225));
        btnVolverMenu.setText("Volver Lista");
        btnVolverMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverMenuActionPerformed(evt);
            }
        });
        jPanel1.add(btnVolverMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 570, 260, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 735, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 616, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVolverMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverMenuActionPerformed
        // TODO add your handling code here:
        
        Lista_Recipes LR = new Lista_Recipes();
        this.dispose();
        LR.setVisible(true);
        LR.pack();
        LR.setLocationRelativeTo(null);
        
    }//GEN-LAST:event_btnVolverMenuActionPerformed

    
    public static void main(String[] args) {
    java.awt.EventQueue.invokeLater(() -> {
        int exampleId = 1; // un ID basico por si no se le envia ninguno o se carga fuera de la lista
        new Pagina_Consultas_lista_recipe(exampleId).setVisible(true);
    });
}


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JTextField apellido_txt;
    public javax.swing.JTextField apellido_txt1;
    private javax.swing.JButton btnVolverMenu;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextArea jTextArea1;
    public javax.swing.JTextField nombre_txt;
    private javax.swing.JTextField txtCedula;
    // End of variables declaration//GEN-END:variables


}
