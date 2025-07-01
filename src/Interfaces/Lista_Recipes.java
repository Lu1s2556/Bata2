
package Interfaces;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import conexion.conexionSQL;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.sql.Connection;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;



public class Lista_Recipes extends javax.swing.JFrame {
    //conexion con la base de datos y declaracion del tablemodel
    private DefaultTableModel modeloTabla;
    conexionSQL con = new conexionSQL();
    Connection cn = con.conectar();
    

    //clase de arranque donde en este caso tiene mas utilidad para ir ajustando todo
    public Lista_Recipes() {
        // configuracion del estilo de la tabla con UIMANAGER
        FlatLightLaf.setup();
        UIManager.put("Table.background",             new Color(240,248,255));
        UIManager.put("Table.alternateRowBackground", new Color(224,238,238));
        UIManager.put("Table.selectionBackground",    new Color(30,136,229));
        UIManager.put("Table.selectionForeground",    Color.WHITE);
        UIManager.put("Table.gridColor",              new Color(187,222,251));
        UIManager.put("TableHeader.background",       new Color(30,136,229));
        UIManager.put("TableHeader.foreground",       Color.WHITE);
        UIManager.put("TableHeader.font",             new Font("Verdana", Font.BOLD, 16));
        

        // arranque de la ventana y eliminacion de barra de windows
        setUndecorated(true);
        initComponents();
        SwingUtilities.updateComponentTreeUI(this);

        // se Inicializa el modelo y se asigna a la tabla con los datos a cargar
        modeloTabla = new DefaultTableModel();
        modeloTabla.setColumnIdentifiers(
            new Object[]{"ID Receta", "Cédula", "Nombre Paciente"}
        );
        Tabla_recipes.setModel(modeloTabla);

        // se llama a funcion que aplica estilo a la tabla
        configurarTabla();

        // funcion para cargar los datos
        cargarDatosRecetas();

        // Listener para que seleccione una opcion de la tabla
        Tabla_recipes.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                abrirPaginaConsulta();
            }
        });
    }
    
    //Funcion para cargar los datos desde la BD a la tabla
    private void cargarDatosRecetas() {
     // Ahora modeloTabla nunca es null
        modeloTabla.setRowCount(0);

        String sql = """
            SELECT r.id_recipe,
                   r.cedula,
                   CONCAT(p.nombre, ' ', p.apellido) AS nombre
                    FROM recipe r
                    JOIN paciente p ON r.cedula = p.cedula
            """;
        try (PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                modeloTabla.addRow(new Object[]{
                    rs.getInt("id_recipe"),
                    rs.getString("cedula"),
                    rs.getString("nombre")
                });
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,
                "Error al cargar recetas: " + ex.getMessage());
        }
}
    
    private void abrirPaginaConsulta() {
        int fila = Tabla_recipes.getSelectedRow();
        if (fila < 0) return; // nada seleccionado

        //traduce índice de vista a modelo:
        fila = Tabla_recipes.convertRowIndexToModel(fila);

        int idRecipe = (int) modeloTabla.getValueAt(fila, 0);
        // abre la ventana de consulta pasando el idRecipe
        Pagina_Consultas_lista_recipe pagina =
            new Pagina_Consultas_lista_recipe(idRecipe);
        pagina.setLocationRelativeTo(this);
        pagina.setVisible(true);
        this.dispose();
    }
    
    //configuracion del estilo metro de la tabla
    private void configurarTabla() {
        // Encabezado
        JTableHeader header = Tabla_recipes.getTableHeader();
        header.setBackground(new Color(30,136,229));
        header.setForeground(Color.WHITE);
        header.setFont(new Font("Segoe UI", Font.BOLD, 16));

        // Renderer de cada fila
        TableCellRenderer metroRenderer = new DefaultTableCellRenderer() {
            private final Color EVEN  = new Color(240,248,255);
            private final Color ODD   = new Color(240,248,255);
            private final Color SEL   = new Color(30,136,229);
            @Override
            public Component getTableCellRendererComponent(
                JTable table, Object value, boolean isSelected,
                boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (isSelected) {
                    setBackground(SEL);
                    setForeground(Color.WHITE);
                } else {
                    setBackground(row % 2 == 0 ? EVEN : ODD);
                    setForeground(Color.BLACK);
                }
                return this;
            }
        };

        // Asigno el renderer a cada columna
        for (int i = 0; i < Tabla_recipes.getColumnCount(); i++) {
            Tabla_recipes.getColumnModel()
                         .getColumn(i)
                         .setCellRenderer(metroRenderer);
        }

        // Otras opciones de estilo
        Tabla_recipes.setShowGrid(true);
        Tabla_recipes.setGridColor(new Color(187,222,251));
        Tabla_recipes.setRowHeight(30);
        Tabla_recipes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }



    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Panel_2 = new javax.swing.JPanel();
        Panel_1 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        TITULO = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabla_recipes = new javax.swing.JTable();
        BTN_VOLVER = new javax.swing.JButton();
        TXT_filtroCedula = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1092, 572));
        setMinimumSize(new java.awt.Dimension(1092, 572));
        setPreferredSize(getPreferredSize());

        Panel_2.setBackground(new java.awt.Color(177, 234, 242));
        Panel_2.setMinimumSize(new java.awt.Dimension(1092, 572));
        Panel_2.setPreferredSize(new java.awt.Dimension(1092, 572));
        Panel_2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(76, 207, 225));

        TITULO.setBackground(new java.awt.Color(255, 255, 255));
        TITULO.setFont(new java.awt.Font("Verdana", 1, 48)); // NOI18N
        TITULO.setForeground(new java.awt.Color(255, 255, 255));
        TITULO.setText("Lista de recipes");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(326, 326, 326)
                .addComponent(TITULO)
                .addContainerGap(533, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(TITULO)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout Panel_1Layout = new javax.swing.GroupLayout(Panel_1);
        Panel_1.setLayout(Panel_1Layout);
        Panel_1Layout.setHorizontalGroup(
            Panel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        Panel_1Layout.setVerticalGroup(
            Panel_1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        Panel_2.add(Panel_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 110));

        Tabla_recipes.setBackground(new java.awt.Color(255, 255, 255));
        Tabla_recipes.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        Tabla_recipes.setForeground(new java.awt.Color(76, 207, 225));
        Tabla_recipes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Cedula", "Nombre"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(Tabla_recipes);

        Panel_2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 160, 820, 290));

        BTN_VOLVER.setBackground(new java.awt.Color(255, 255, 255));
        BTN_VOLVER.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        BTN_VOLVER.setForeground(new java.awt.Color(76, 207, 225));
        BTN_VOLVER.setText("Menu Principal");
        BTN_VOLVER.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTN_VOLVERActionPerformed(evt);
            }
        });
        Panel_2.add(BTN_VOLVER, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 460, 320, 40));

        TXT_filtroCedula.setBackground(new java.awt.Color(255, 255, 255));
        TXT_filtroCedula.setFont(new java.awt.Font("Verdana", 1, 14)); // NOI18N
        TXT_filtroCedula.setForeground(new java.awt.Color(76, 207, 225));
        TXT_filtroCedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TXT_filtroCedulaKeyReleased(evt);
            }
        });
        Panel_2.add(TXT_filtroCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 120, 370, -1));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Verdana", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Cedula: ");
        Panel_2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 120, -1, 20));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel_2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Panel_2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    //configuracion de busqueda automatica en el cuadro cedula
    private void TXT_filtroCedulaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TXT_filtroCedulaKeyReleased
       DefaultTableModel modelo = (DefaultTableModel) Tabla_recipes.getModel();
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(modelo);
        Tabla_recipes.setRowSorter(sorter);

        String filtro = TXT_filtroCedula.getText().trim();

        // Filtra por la columna 1 (Cédula) delvalor escrito
        RowFilter<DefaultTableModel, Object> rf = RowFilter.regexFilter("(?i)^" + filtro, 1);
        sorter.setRowFilter(rf);
        
    }//GEN-LAST:event_TXT_filtroCedulaKeyReleased

    private void BTN_VOLVERActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTN_VOLVERActionPerformed
        Menu_Principal mp = new Menu_Principal();
        this.dispose();
        mp.setVisible(true);
        mp.pack();
        mp.setLocationRelativeTo(null);
    }//GEN-LAST:event_BTN_VOLVERActionPerformed

    
    public static void main(String args[]) {
        
        FlatLightLaf.setup();

        // 2) Override de colores si quieres afinarlos
        UIManager.put("Table.background",        new Color(240,248,255));
        UIManager.put("Table.alternateRowBackground", new Color(224,238,238));
        UIManager.put("Table.selectionBackground",    new Color(30,136,229));
        UIManager.put("Table.selectionForeground",    Color.WHITE);
        UIManager.put("Table.gridColor",              new Color(187,222,251));
        UIManager.put("TableHeader.background",       new Color(30,136,229));
        UIManager.put("TableHeader.foreground",       Color.WHITE);
        UIManager.put("TableHeader.font",             new Font("Segoe UI", Font.BOLD, 14));
        // …otros overrides…
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Lista_Recipes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BTN_VOLVER;
    private javax.swing.JPanel Panel_1;
    private javax.swing.JPanel Panel_2;
    private javax.swing.JLabel TITULO;
    private javax.swing.JTextField TXT_filtroCedula;
    private javax.swing.JTable Tabla_recipes;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
