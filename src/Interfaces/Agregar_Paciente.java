package Interfaces;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import conexion.*;
import java.awt.Color;
import java.awt.Font;
import java.sql.*;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;

public class Agregar_Paciente extends javax.swing.JFrame {

    conexionSQL con = new conexionSQL();
    Connection cn = con.conectar();
    
    private listaPacientes ventana;
    
    public Agregar_Paciente() {        
        initComponents();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    public Agregar_Paciente(listaPacientes ventana) {
        initComponents();
        this.ventana = ventana;
    }
    
    private JButton historial_btn;
    private JButton modificar_btn;
    private JButton actualizar_btn;
    private listaPacientes padre;
    
    // Ventana de información, solo lectura
    public Agregar_Paciente (String cedula, listaPacientes padre) {
        initComponents();
        this.padre = padre;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        cargarDatos(cedula);
        configurarBotones();
        jLabel1.setText("Paciente");
        agr_paciente_btn.setVisible(false);
        soloLectura();
    }
    
    // Metodo para cargar los datos de un paciente existente
    private void cargarDatos(String cedula){
        DAOPacientes pacientesDAO = new DAOPacientes(cn);
        Pacientes paciente = pacientesDAO.obtenerPaciente(cedula);
        
        if (paciente != null) {
            // Asignar datos a los campos de la interfaz
            cedulaOriginal = paciente.getCedula();
            ci_txt.setText(paciente.getCedula());
            nombre_txt.setText(paciente.getNombre());
            apellido_txt.setText(paciente.getApellido());
            telefono_txt.setText(paciente.getTelefono());
            direccion_txt.setText(paciente.getDireccion());
            email_txt.setText(paciente.getEmail());
            gruposangre_box.setSelectedItem(paciente.getGrupoS());
            nacimiento_dte.setDate(paciente.getFecha());
            
            // Asignar sexo con los checkbox
            hombre_chk.setSelected("Hombre".equals(paciente.getSexo()));
            mujer_chk.setSelected("Mujer".equals(paciente.getSexo()));
        
            soloLectura();

        } else {
            JOptionPane.showMessageDialog(null, "Paciente no encontrado");
        }
    }
    
    // Los datos en solo lectura
    private void soloLectura() {
        // Deshabilitar edición en los JTextField
        ci_txt.setEditable(false);
        nombre_txt.setEditable(false);
        apellido_txt.setEditable(false);
        telefono_txt.setEditable(false);
        direccion_txt.setEditable(false);
        email_txt.setEditable(false);

        // Deshabilitar interacción en JComboBox y JDateChooser
        gruposangre_box.setEnabled(false);
        nacimiento_dte.setEnabled(false);

        // Deshabilitar selección en los JCheckBox
        hombre_chk.setEnabled(false);
        mujer_chk.setEnabled(false);

    }

    // Para editar los datos de un paciente ya existente
    private void activarEdicion() {
        ci_txt.setEditable(true);
        nombre_txt.setEditable(true);
        apellido_txt.setEditable(true);
        telefono_txt.setEditable(true);
        direccion_txt.setEditable(true);
        email_txt.setEditable(true);

        gruposangre_box.setEnabled(true);
        nacimiento_dte.setEnabled(true);

        hombre_chk.setEnabled(true);
        mujer_chk.setEnabled(true);

        agr_paciente_btn.setVisible(true); // Opcional: mostrar botón de agregar paciente si es necesario
    }
    
    private String cedulaOriginal;

    // Método para actualizar paciente en la base de datos
    private void actualizarPaciente() {
        // Crear objeto con datos modificados
        Pacientes pacienteModificado = new Pacientes();
        pacienteModificado.setCedula(ci_txt.getText());
        pacienteModificado.setNombre(nombre_txt.getText());
        pacienteModificado.setApellido(apellido_txt.getText());
        pacienteModificado.setTelefono(telefono_txt.getText());
        pacienteModificado.setDireccion(direccion_txt.getText());
        pacienteModificado.setEmail(email_txt.getText());
        pacienteModificado.setGrupoS(gruposangre_box.getSelectedItem().toString());
        pacienteModificado.setSexo(hombre_chk.isSelected() ? "Hombre" : "Mujer");
        
        java.util.Date utilDate = nacimiento_dte.getDate();
        if (utilDate != null) {
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        pacienteModificado.setFecha(sqlDate);
        } else {
            pacienteModificado.setFecha(null);
        }
        // Llamar al DAO para actualizar la información
        DAOPacientes dao = new DAOPacientes(cn);
        boolean exito = dao.actualizarPaciente(pacienteModificado, cedulaOriginal);

        // Mostrar mensaje según el resultado
        if (exito) {
            listaPacientes lp = new listaPacientes();
            this.dispose();
            lp.setVisible(true);
            lp.pack();
            lp.setLocationRelativeTo(null);
            JOptionPane.showMessageDialog(null, "Paciente actualizado correctamente");
        } else {
            JOptionPane.showMessageDialog(null, "Error al actualizar paciente");
        }

        // Opcional: Ocultar el botón "Actualizar" después de actualizar
        actualizar_btn.setVisible(false);
    }
    
        private void configurarBotones() {
        modificar_btn = new JButton("Modificar");
        actualizar_btn = new JButton("Actualizar");
        historial_btn = new JButton("Antecedentes");
        
        Font fuenteBoton = new Font("Verdana", Font.BOLD, 12);
        Color fondo = new Color(255, 255, 255);
        Color texto = new Color(76, 207, 225);
        Border borde = BorderFactory.createBevelBorder(BevelBorder.RAISED);

        // Estilos para el botón Modificar
        modificar_btn.setFont(fuenteBoton);
        modificar_btn.setBackground(fondo);
        modificar_btn.setForeground(texto);
        modificar_btn.setBorder(borde);

        // Estilos para el botón Actualizar
        actualizar_btn.setFont(fuenteBoton);
        actualizar_btn.setBackground(fondo);
        actualizar_btn.setForeground(texto);
        actualizar_btn.setBorder(borde);
        
        // Estilos para el botón Actualizar
        historial_btn.setFont(fuenteBoton);
        historial_btn.setBackground(fondo);
        historial_btn.setForeground(texto);
        historial_btn.setBorder(borde);

        // Acción del boton modificar
        modificar_btn.addActionListener(e -> {
            activarEdicion();
            modificar_btn.setVisible(false);
            actualizar_btn.setVisible(true);
            agr_paciente_btn.setVisible(false);
            JOptionPane.showMessageDialog(null, "Puede actualizar los datos");
        });
        
        // Acción del boton actualizar
        actualizar_btn.addActionListener(e -> {
            actualizarPaciente();
            soloLectura();
            actualizar_btn.setVisible(false);
            modificar_btn.setVisible(true);
            agr_paciente_btn.setVisible(false);
            if (padre != null) {
                padre.recargarPacientes();
            }
        });
        
        // Acción del boton antecedentes
        historial_btn.addActionListener(e -> {
            String cedula = ci_txt.getText();
            Antecedentes an = new Antecedentes(cedula);
            this.dispose();
            an.setVisible(true);
            an.pack();
            an.setLocationRelativeTo(null);
        });
        
        // Posiciones y limites de los botones
        modificar_btn.setBounds(50, 350, 80, 30);
        actualizar_btn.setBounds(50, 350, 80, 30);
        historial_btn.setBounds(250, 350, 100, 30);
        
        jPanel1.add(modificar_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 350, 80, 30));
        jPanel1.add(actualizar_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 350, 80, 30));
        jPanel1.add(historial_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 350, 100, 30));

        modificar_btn.setVisible(true);
        actualizar_btn.setVisible(false);

        jPanel1.revalidate();
        jPanel1.repaint();
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup_SexCheck = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        apellido_txt = new javax.swing.JTextField();
        email_txt = new javax.swing.JTextField();
        telefono_txt = new javax.swing.JTextField();
        direccion_txt = new javax.swing.JTextField();
        nombre_txt = new javax.swing.JTextField();
        ci_txt = new javax.swing.JTextField();
        mujer_chk = new javax.swing.JCheckBox();
        hombre_chk = new javax.swing.JCheckBox();
        agr_paciente_btn = new javax.swing.JButton();
        gruposangre_box = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        nacimiento_dte = new com.toedter.calendar.JDateChooser();
        Volver_btn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(177, 234, 242));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(76, 207, 225));
        jPanel2.setForeground(new java.awt.Color(76, 207, 225));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Verdana", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Agregar Paciente");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(178, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(125, 125, 125))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 650, 70));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nombre(s):");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, 20));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Apellido(s):");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 110, -1, -1));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("C.I:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 140, -1, -1));

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Sexo:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 170, -1, -1));

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Dirección:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 260, -1, -1));

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Telefono:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 260, -1, -1));

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Correo electronico:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, -1, -1));

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Fecha de nacimiento:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 290, -1, 30));

        apellido_txt.setBackground(new java.awt.Color(255, 255, 255));
        apellido_txt.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        apellido_txt.setForeground(new java.awt.Color(76, 207, 225));
        apellido_txt.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        apellido_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apellido_txtActionPerformed(evt);
            }
        });
        jPanel1.add(apellido_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 110, 160, -1));

        email_txt.setBackground(new java.awt.Color(255, 255, 255));
        email_txt.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        email_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                email_txtActionPerformed(evt);
            }
        });
        jPanel1.add(email_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 290, 140, -1));

        telefono_txt.setBackground(new java.awt.Color(255, 255, 255));
        telefono_txt.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        telefono_txt.setForeground(new java.awt.Color(76, 207, 225));
        telefono_txt.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        telefono_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                telefono_txtActionPerformed(evt);
            }
        });
        jPanel1.add(telefono_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 260, 160, -1));

        direccion_txt.setBackground(new java.awt.Color(255, 255, 255));
        direccion_txt.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        direccion_txt.setForeground(new java.awt.Color(76, 207, 225));
        direccion_txt.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        direccion_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                direccion_txtActionPerformed(evt);
            }
        });
        jPanel1.add(direccion_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 260, 160, -1));

        nombre_txt.setBackground(new java.awt.Color(255, 255, 255));
        nombre_txt.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        nombre_txt.setForeground(new java.awt.Color(76, 207, 225));
        nombre_txt.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        nombre_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombre_txtActionPerformed(evt);
            }
        });
        jPanel1.add(nombre_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 110, 160, -1));

        ci_txt.setBackground(new java.awt.Color(255, 255, 255));
        ci_txt.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        ci_txt.setForeground(new java.awt.Color(76, 207, 225));
        ci_txt.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        ci_txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ci_txtActionPerformed(evt);
            }
        });
        jPanel1.add(ci_txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 140, 160, -1));

        buttonGroup_SexCheck.add(mujer_chk);
        mujer_chk.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        mujer_chk.setForeground(new java.awt.Color(255, 255, 255));
        mujer_chk.setText("Mujer");
        mujer_chk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mujer_chkActionPerformed(evt);
            }
        });
        jPanel1.add(mujer_chk, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 170, 80, -1));

        buttonGroup_SexCheck.add(hombre_chk);
        hombre_chk.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        hombre_chk.setForeground(new java.awt.Color(255, 255, 255));
        hombre_chk.setText("Hombre");
        hombre_chk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hombre_chkActionPerformed(evt);
            }
        });
        jPanel1.add(hombre_chk, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 170, 80, -1));

        agr_paciente_btn.setBackground(new java.awt.Color(255, 255, 255));
        agr_paciente_btn.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        agr_paciente_btn.setForeground(new java.awt.Color(0, 207, 225));
        agr_paciente_btn.setText("Agregar paciente");
        agr_paciente_btn.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        agr_paciente_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agr_paciente_btnActionPerformed(evt);
            }
        });
        jPanel1.add(agr_paciente_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 350, 130, 30));

        gruposangre_box.setBackground(new java.awt.Color(255, 255, 255));
        gruposangre_box.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        gruposangre_box.setForeground(new java.awt.Color(76, 207, 225));
        gruposangre_box.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "A+", "A-", "B+", "B-", "AB+", "AB-", "O+ ", "O-" }));
        gruposangre_box.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.add(gruposangre_box, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 140, 160, 20));

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Grupo sanguineo:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 140, -1, 20));

        nacimiento_dte.setBackground(new java.awt.Color(255, 255, 255));
        nacimiento_dte.setForeground(new java.awt.Color(76, 207, 225));
        nacimiento_dte.setDateFormatString("yyyy-MM-dd");
        jPanel1.add(nacimiento_dte, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 290, 140, 30));

        Volver_btn.setBackground(new java.awt.Color(255, 255, 255));
        Volver_btn.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N
        Volver_btn.setForeground(new java.awt.Color(0, 207, 225));
        Volver_btn.setText("VOLVER");
        Volver_btn.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        Volver_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Volver_btnActionPerformed(evt);
            }
        });
        jPanel1.add(Volver_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 350, 80, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void apellido_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_apellido_txtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_apellido_txtActionPerformed

    private void email_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_email_txtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_email_txtActionPerformed

    private void telefono_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_telefono_txtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_telefono_txtActionPerformed

    private void direccion_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_direccion_txtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_direccion_txtActionPerformed

    private void nombre_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombre_txtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombre_txtActionPerformed

    private void ci_txtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ci_txtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ci_txtActionPerformed

    private void agr_paciente_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agr_paciente_btnActionPerformed
        
        String cedula = ci_txt.getText();
        String nombre = nombre_txt.getText();
        String apellido = apellido_txt.getText();
        String grupoS = gruposangre_box.getSelectedItem().toString();
        String telefono = telefono_txt.getText();
        String direccion = direccion_txt.getText();
        String email = email_txt.getText();
        String fecha = ((JTextField) nacimiento_dte.getDateEditor().getUiComponent()).getText();
        String sexo = hombre_chk.isSelected() ? "Hombre" : mujer_chk.isSelected() ? "Mujer" : null;

        if (sexo == null) {
            JOptionPane.showMessageDialog(null, "DEBE SELECCIONAR EL SEXO");
            return;
        }

        if (nombre.isEmpty() || apellido.isEmpty() || email.isEmpty() || cedula.isEmpty() || telefono.isEmpty() || direccion.isEmpty() || fecha.isEmpty()) {
            JOptionPane.showMessageDialog(null, "DEBE COMPLETAR LOS DATOS");
            return;
        }

        // Creación del objeto Pacientes
        Pacientes p = new Pacientes(cedula, nombre, apellido, sexo, grupoS, telefono, direccion, email, Date.valueOf(fecha));

        // Llamada al DAO
        DAOPacientes dao = new DAOPacientes(cn);
        if (dao.agregarPaciente(p)) {
            JOptionPane.showMessageDialog(null, "SE PUDO AGREGAR EL PACIENTE EXITOSAMENTE!");
            if (ventana != null) {
                ventana.recargarPacientes(); // ¡Actualiza la tabla!
            }
        } else {
            JOptionPane.showMessageDialog(null, "NO SE PUDO AGREGAR EL PACIENTE");
        }
        Antecedentes an = new Antecedentes(cedula);
        this.dispose();
        an.setVisible(true);
        an.pack();
        an.setLocationRelativeTo(null);
        
        /*
        String cedula = ci_txt.getText();
        String nombre = nombre_txt.getText();
        String apellido = apellido_txt.getText();
        String grupoS = gruposangre_box.getSelectedItem().toString();
        String telefono = telefono_txt.getText();
        String direccion = direccion_txt.getText();
        String email = email_txt.getText();
        String fecha = ((JTextField)nacimiento_dte.getDateEditor().getUiComponent()).getText();
        String sexo = null;
        
        if(hombre_chk.isSelected()) {
            sexo = "Hombre";
        } else if (mujer_chk.isSelected()) {
            sexo = "Mujer";
        } else {
            JOptionPane.showMessageDialog(null, "DEBE SELECCIONAR EL SEXO");
        }
        
        if(nombre.isEmpty()||apellido.isEmpty()||email.isEmpty()||cedula.isEmpty()||telefono.isEmpty()||direccion.isEmpty()||fecha.isEmpty()) {
            JOptionPane.showMessageDialog(null, "DEBE COMPLETAR LOS DATOS");
        }else{
            Pacientes p = new Pacientes(cedula, nombre, apellido, sexo, grupoS, telefono, direccion, email, Date.valueOf(fecha));
            try {
                PreparedStatement ps = cn.prepareStatement("INSERT INTO paciente(cedula, nombre, apellido, sexo, `grupo sanguineo`, telefono, direccion, email, `fecha de nacimiento`) VALUES (?,?,?,?,?,?,?,?,?)");
                ps.setString(1, cedula);
                ps.setString(2, nombre);
                ps.setString(3, apellido);
                ps.setString(4, sexo);
                ps.setString(5, grupoS);
                ps.setString(6, telefono);
                ps.setString(7, direccion);
                ps.setString(8, email);
                ps.setDate(9, p.getFecha());
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "SE PUDO AGREGAR EL PACIENTE EXITOSAMENTE!");
            } catch (Exception e) {
                // JOptionPane.showMessageDialog(null, "NO SE PUDO AGREGAR EL PACIENTE" + e);
                System.out.println("error: " + e);
            }
        }*/ 
    }//GEN-LAST:event_agr_paciente_btnActionPerformed

    private void hombre_chkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hombre_chkActionPerformed
        
    }//GEN-LAST:event_hombre_chkActionPerformed

    private void mujer_chkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mujer_chkActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mujer_chkActionPerformed

    private void Volver_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Volver_btnActionPerformed
        listaPacientes lp = new listaPacientes();
        this.dispose();
        lp.setVisible(true);
        lp.pack();
        lp.setLocationRelativeTo(null);
    }//GEN-LAST:event_Volver_btnActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Agregar_Paciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Agregar_Paciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Agregar_Paciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Agregar_Paciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        try { 
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel"); 
        } catch(Exception ignored){}

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Agregar_Paciente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton Volver_btn;
    private javax.swing.JButton agr_paciente_btn;
    public javax.swing.JTextField apellido_txt;
    private javax.swing.ButtonGroup buttonGroup_SexCheck;
    public javax.swing.JTextField ci_txt;
    public javax.swing.JTextField direccion_txt;
    public javax.swing.JTextField email_txt;
    public javax.swing.JComboBox<String> gruposangre_box;
    public javax.swing.JCheckBox hombre_chk;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    public javax.swing.JCheckBox mujer_chk;
    public com.toedter.calendar.JDateChooser nacimiento_dte;
    public javax.swing.JTextField nombre_txt;
    public javax.swing.JTextField telefono_txt;
    // End of variables declaration//GEN-END:variables
}
