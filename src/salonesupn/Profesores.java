package salonesupn;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Profesores extends javax.swing.JInternalFrame {

    public int idProfesor;
    public String nombre;
    public String telefono;
    public String observacion;
    ArrayList<Profesores> profesCreados = new ArrayList<>();

    public Profesores(int idProfesor, String nombre, String telefono, String observacion) {
        this.idProfesor = idProfesor;
        this.nombre = nombre;
        this.telefono = telefono;
        this.observacion = observacion;
    }

    public int getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(int idProfesor) {
        this.idProfesor = idProfesor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
     
    

    public boolean crearProfe() {
        Connection conn = Conexion.conectar();
        String sql = "insert into profesor values (? ,? , ?, ?)";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt (1, Integer.parseInt(txtCCProfe.getText()));
            statement.setString(2, txtNombre.getText());
            statement.setString(3, txtTelefono.getText());
            statement.setString(4, txtObservaciones.getText());
            int rowsInserted = statement.executeUpdate();
            conn.close();
            verProfes();
            if (rowsInserted > 0) {
                return true;
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, ex);
        }
        return false;
    }

    public ArrayList verProfes() throws SQLException {
        limpiarTabla();
        profesCreados.clear();
        Connection conn = Conexion.conectar();
        String sql = "select * from profesor";
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            int id = result.getInt(1);
            String name = result.getString(2);
            String tel = result.getString(3);
            String notes= result.getString(4);
            
            Profesores p = new Profesores(id, name, tel, notes);

            profesCreados.add(p);
        }
        try {
            String datos[][] = new String[profesCreados.size()][4];
            for (int i = 0; i < profesCreados.size(); i++) {
                datos[i][0] = String.valueOf(profesCreados.get(i).idProfesor);
                datos[i][1] = profesCreados.get(i).nombre;                
                datos[i][2] = profesCreados.get(i).telefono;
                datos[i][3] = profesCreados.get(i).observacion;
            }
            jTableSalones.setModel(new javax.swing.table.DefaultTableModel(
                    datos, new String[]{
                    "Cedula Profe", "Nombre y Apellido", "Telefono", "Observaciones"
                }));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }
        return profesCreados;
    }

    public void limpiarTabla() {
        txtCCProfe.setText("");
        txtNombre.setText("");
        txtTelefono.setText("");
        txtObservaciones.setText("");
        String vacio[][] = {{"", "", "", ""}, {"", "", "", ""}};
        jTableSalones.setModel(new javax.swing.table.DefaultTableModel(
                vacio, new String[]{
                    "Cedula Profe", "Nombre y Apellido", "Telefono", "Observaciones"
                }
        ));
    }

    public Profesores() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabelCCProfe = new javax.swing.JLabel();
        jLabelNombre = new javax.swing.JLabel();
        jLabelTelefono = new javax.swing.JLabel();
        jLabelObservacion = new javax.swing.JLabel();
        txtCCProfe = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtObservaciones = new javax.swing.JTextField();
        jButtonCrear = new javax.swing.JButton();
        jButtonLeer = new javax.swing.JButton();
        jButtonActualizar = new javax.swing.JButton();
        jButtonEliminar = new javax.swing.JButton();
        jButtonLimpiar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableSalones = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Profesores");
        setNormalBounds(new java.awt.Rectangle(0, 0, 0, 0));

        jLabel1.setFont(new java.awt.Font("Gadugi", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Licenciatura en Música - UPN 2022 - Version 1");

        jLabelCCProfe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelCCProfe.setText("Cedula Profe");
        jLabelCCProfe.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabelNombre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelNombre.setText("Nombre y Apellido");
        jLabelNombre.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabelTelefono.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTelefono.setText("Telefono");
        jLabelTelefono.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabelObservacion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelObservacion.setText("Observaciones");
        jLabelObservacion.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jButtonCrear.setText("Crear");
        jButtonCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCrearActionPerformed(evt);
            }
        });

        jButtonLeer.setText("Leer");
        jButtonLeer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLeerActionPerformed(evt);
            }
        });

        jButtonActualizar.setText("Actualizar");
        jButtonActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonActualizarActionPerformed(evt);
            }
        });

        jButtonEliminar.setText("Eliminar");
        jButtonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarActionPerformed(evt);
            }
        });

        jButtonLimpiar.setText("Limpiar");
        jButtonLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLimpiarActionPerformed(evt);
            }
        });

        jTableSalones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Cedula Profe", "Nombre y Apellido", "Telefono", "Observaciones"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableSalones.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jScrollPane1.setViewportView(jTableSalones);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 878, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(216, 216, 216)
                        .addComponent(jButtonCrear)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonLeer)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonActualizar)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonEliminar)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonLimpiar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 672, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(107, 107, 107))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelCCProfe, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCCProfe, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelNombre))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelTelefono))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtObservaciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelObservacion))
                        .addGap(178, 178, 178))))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabelCCProfe, jLabelNombre, jLabelObservacion, jLabelTelefono});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtCCProfe, txtNombre, txtObservaciones, txtTelefono});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButtonActualizar, jButtonCrear, jButtonEliminar, jButtonLeer, jButtonLimpiar});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCCProfe, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelNombre)
                    .addComponent(jLabelTelefono)
                    .addComponent(jLabelObservacion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCCProfe, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtObservaciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonLimpiar)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonCrear)
                        .addComponent(jButtonLeer)
                        .addComponent(jButtonActualizar)
                        .addComponent(jButtonEliminar)))
                .addGap(41, 41, 41)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabelCCProfe, jLabelNombre, jLabelObservacion, jLabelTelefono});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtCCProfe, txtNombre, txtObservaciones, txtTelefono});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(72, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLimpiarActionPerformed
        limpiarTabla();
    }//GEN-LAST:event_jButtonLimpiarActionPerformed

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed
        int confirmación = JOptionPane.showConfirmDialog(null, "Esta acción afectará a todos los registros relacionados en el sistema. ¿Está segur@ que desea eliminar?", "Confirmar borrado", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (confirmación == 0) {
        int filaseleccionada = jTableSalones.getSelectedRow();
        String idBorrar;
        if (filaseleccionada != -1) {
            idBorrar = ((String) jTableSalones.getValueAt(filaseleccionada, 0));
            try {
                Connection conn = Conexion.conectar();
                String delete1 = "SET foreign_key_checks = 0";
                String delete2 = "delete from profesor where idProfesor = ? ";
                String delete3 = "SET foreign_key_checks = 1";
                PreparedStatement sentencia1 = conn.prepareStatement(delete1);
                PreparedStatement sentencia2 = conn.prepareStatement(delete2);
                PreparedStatement sentencia3 = conn.prepareStatement(delete3);
                sentencia2.setString(1, idBorrar);
                sentencia1.executeUpdate();
                int rowsDeleted = sentencia2.executeUpdate();
                sentencia3.executeUpdate();                
                conn.close();
                limpiarTabla();
                verProfes();
            } catch (SQLException ex) {
                Logger.getLogger(Profesores.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Debe seleccionar una fila");
        }
        }
    }//GEN-LAST:event_jButtonEliminarActionPerformed

    private void jButtonActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonActualizarActionPerformed
        int filaseleccionada = jTableSalones.getSelectedRow();
        String idActualizar;
        if (filaseleccionada != -1) {
            idActualizar = ((String) jTableSalones.getValueAt(filaseleccionada, 0));
            try {
                Connection conn = Conexion.conectar();
                String update1 = "update profesor set nombre = ? where idProfesor = ? ;";
                PreparedStatement sentencia1 = conn.prepareStatement(update1);
                String nameUpdated=JOptionPane.showInputDialog("Escriba el nuevo nombre del docente: ");
                sentencia1.setString(1, nameUpdated);
                sentencia1.setString(2, idActualizar);
                int rowsUpdated1 = sentencia1.executeUpdate();
                String update2 = "update profesor set telefono = ? where idProfesor = ? ";
                PreparedStatement sentencia2 = conn.prepareStatement(update2);
                String telUpdated=JOptionPane.showInputDialog("Escriba el nuevo telefono: ");
                sentencia2.setString(1, telUpdated);
                sentencia2.setString(2, idActualizar);
                int rowsUpdated2 = sentencia2.executeUpdate();
                String update3 = "update profesor set observacion = ? where idProfesor = ? ";
                PreparedStatement sentencia3 = conn.prepareStatement(update3);
                String notesUpdated=JOptionPane.showInputDialog("Escriba una nueva observacion: ");
                sentencia3.setString(1, notesUpdated);
                sentencia3.setString(2, idActualizar);
                int rowsUpdated3 = sentencia3.executeUpdate();                
                conn.close();
                limpiarTabla();
                verProfes();
            } catch (SQLException ex) {
                Logger.getLogger(Profesores.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Debe seleccionar una fila");
        }
    }//GEN-LAST:event_jButtonActualizarActionPerformed

    private void jButtonLeerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLeerActionPerformed
        try {
            limpiarTabla();
            verProfes();
        } catch (SQLException ex) {
            Logger.getLogger(Profesores.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonLeerActionPerformed

    private void jButtonCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCrearActionPerformed
        crearProfe();
    }//GEN-LAST:event_jButtonCrearActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonActualizar;
    private javax.swing.JButton jButtonCrear;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JButton jButtonLeer;
    private javax.swing.JButton jButtonLimpiar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelCCProfe;
    private javax.swing.JLabel jLabelNombre;
    private javax.swing.JLabel jLabelObservacion;
    private javax.swing.JLabel jLabelTelefono;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableSalones;
    private javax.swing.JTextField txtCCProfe;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtObservaciones;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}