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

public class Grupo extends javax.swing.JInternalFrame {

    public int idGrupo;
    public int idSemestre;
    ArrayList<Grupo> gruposCreados = new ArrayList<>();

    public Grupo(int idGrupo, int idSemestre) {
        this.idGrupo = idGrupo;
        this.idSemestre = idSemestre;
    }

    public int getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }

    public int getIdSemestre() {
        return idSemestre;
    }

    public void setIdSemestre(int idSemestre) {
        this.idSemestre = idSemestre;
    }

    

    public boolean crearGrupo() {
        Connection conn = Conexion.conectar();
        String sql = "insert into grupo values (? ,?)";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(txtIdGrupo.getText()));
            statement.setInt(2, Integer.parseInt(cmbIdSemestre.getSelectedItem().toString()));
            int rowsInserted = statement.executeUpdate();
            conn.close();
            verGrupos();
            int asignarMateriasPorDefecto = JOptionPane.showConfirmDialog(null, "¿Desea agregar al grupo TODAS las materias de ese semestre?", "Confirmar: ", JOptionPane.YES_NO_OPTION, JOptionPane. INFORMATION_MESSAGE);
            if (asignarMateriasPorDefecto == 0) {
                //Cargar Materias: insert into asignarMaterias (idMateria, idSemestre, idGrupo) select materia.idMateria, grupo.idSemestre, grupo.idGrupo
                //from materia inner join grupo where materia.idSemestre = grupo.idSemestre = 1;
            }
            if (rowsInserted > 0) {
                return true;
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, ex);

            //JOptionPane.showMessageDialog(null, "Error insertando materia");
        }
        return false;
    }

    public ArrayList verGrupos() throws SQLException {
        limpiarTabla();
        gruposCreados.clear();
        Connection conn = Conexion.conectar();
        String sql = "select * from grupo";
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            int id = result.getInt(1);
            int semester = result.getInt(2);
            
            Grupo g = new Grupo(id, semester);

            gruposCreados.add(g);
        }
        try {
            String datos[][] = new String[gruposCreados.size()][2];
            for (int i = 0; i < gruposCreados.size(); i++) {
                datos[i][0] = String.valueOf(gruposCreados.get(i).idGrupo);                              
                datos[i][1] = String.valueOf(gruposCreados.get(i).idSemestre);                
            }
            jTableGrupo.setModel(new javax.swing.table.DefaultTableModel(
                    datos, new String[]{
                     "Numero del grupo", "Semestre"
                }));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }
        return gruposCreados;
    }

    public void limpiarTabla() {
        txtIdGrupo.setText("");
        cmbIdSemestre.setSelectedIndex(0);
        String vacio[][] = {{"", ""}, {"", ""}};
        jTableGrupo.setModel(new javax.swing.table.DefaultTableModel(
                vacio, new String[]{
                     "Numero del grupo", "Semestre"
                }
        ));
    }

    public Grupo() {
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
        jLabelIDGrupo = new javax.swing.JLabel();
        jLabelIdSemestre = new javax.swing.JLabel();
        txtIdGrupo = new javax.swing.JTextField();
        cmbIdSemestre = new javax.swing.JComboBox<>();
        jButtonCrear = new javax.swing.JButton();
        jButtonLeer = new javax.swing.JButton();
        jButtonActualizar = new javax.swing.JButton();
        jButtonEliminar = new javax.swing.JButton();
        jButtonLimpiar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableGrupo = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Grupo");
        setNormalBounds(new java.awt.Rectangle(0, 0, 0, 0));

        jLabel1.setFont(new java.awt.Font("Gadugi", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Licenciatura en Música - UPN 2022 - Version 1");

        jLabelIDGrupo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelIDGrupo.setText("Grupo Numero:");
        jLabelIDGrupo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabelIdSemestre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelIdSemestre.setText("Semestre:");
        jLabelIdSemestre.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        cmbIdSemestre.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));

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

        jTableGrupo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Numero del grupo", "Semestre"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableGrupo.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jScrollPane1.setViewportView(jTableGrupo);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 672, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(107, 107, 107))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(159, 159, 159)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButtonCrear)
                                .addGap(262, 262, 262))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(95, 95, 95)
                                .addComponent(jButtonLeer)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonActualizar)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonEliminar)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonLimpiar))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(215, 215, 215)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(243, 243, 243)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabelIDGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtIdGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabelIdSemestre)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cmbIdSemestre, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabelIDGrupo, jLabelIdSemestre});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButtonActualizar, jButtonCrear, jButtonEliminar, jButtonLeer, jButtonLimpiar});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelIDGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelIdSemestre)
                    .addComponent(cmbIdSemestre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButtonLimpiar)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonCrear)
                        .addComponent(jButtonLeer)
                        .addComponent(jButtonActualizar)
                        .addComponent(jButtonEliminar)))
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabelIDGrupo, jLabelIdSemestre});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(94, 94, 94)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 733, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(212, Short.MAX_VALUE))
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
            int filaseleccionada = jTableGrupo.getSelectedRow();
            String idBorrarGrupo,idBorrarSemestre;
            if (filaseleccionada != -1) {
                idBorrarGrupo = ((String) jTableGrupo.getValueAt(filaseleccionada, 0));
                idBorrarSemestre = ((String) jTableGrupo.getValueAt(filaseleccionada, 1));
                try {
                    Connection conn = Conexion.conectar();
                    String delete1 = "SET foreign_key_checks = 0";
                    String delete2 = "delete from grupo where idGrupo = ? and idSemestre = ?;";
                    String delete3 = "SET foreign_key_checks = 1";
                    PreparedStatement sentencia1 = conn.prepareStatement(delete1);
                    PreparedStatement sentencia2 = conn.prepareStatement(delete2);
                    PreparedStatement sentencia3 = conn.prepareStatement(delete3);
                    sentencia2.setString(1, idBorrarGrupo);
                    sentencia2.setString(2, idBorrarSemestre);
                    sentencia1.executeUpdate();
                    int rowsDeleted = sentencia2.executeUpdate();
                    sentencia3.executeUpdate();
                    conn.close();
                    limpiarTabla();
                    verGrupos();
                } catch (SQLException ex) {
                    Logger.getLogger(Grupo.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(rootPane, "Debe seleccionar una fila");
            }
        }
    }//GEN-LAST:event_jButtonEliminarActionPerformed

    private void jButtonActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonActualizarActionPerformed
        JOptionPane.showMessageDialog(rootPane, "Opcion no disponible");
        /*int filaseleccionada = jTableGrupo.getSelectedRow();
        String idActualizar;
        if (filaseleccionada != -1) {
            idActualizar = ((String) jTableGrupo.getValueAt(filaseleccionada, 0));
            try {
                Connection conn = Conexion.conectar();
                String update1 = "update grupo set Nombre_Materia= ? where idMateria = ? ";
                PreparedStatement sentencia1 = conn.prepareStatement(update1);
                String nameUpdated=JOptionPane.showInputDialog("Escriba el nuevo nombre de la Materia: ");
                sentencia1.setString(1, nameUpdated);
                sentencia1.setString(2, idActualizar);
                int rowsUpdated1 = sentencia1.executeUpdate();
                String update2 = "update materia set idSemestre= ? where idMateria = ? ";
                PreparedStatement sentencia2 = conn.prepareStatement(update2);
                String semesterUpdated=JOptionPane.showInputDialog("Escriba la nueva ubicación semestral: ");
                sentencia2.setString(1, semesterUpdated);
                sentencia2.setString(2, idActualizar);
                int rowsUpdated2 = sentencia2.executeUpdate();
                String update3 = "update materia set nivel= ? where idMateria = ? ";
                PreparedStatement sentencia3 = conn.prepareStatement(update3);
                String levelUpdated=JOptionPane.showInputDialog("Escriba el nuevo nivel: ");
                sentencia3.setString(1, levelUpdated);
                sentencia3.setString(2, idActualizar);
                int rowsUpdated3 = sentencia3.executeUpdate();
                String update4 = "update materia set horas= ? where idMateria = ? ";
                PreparedStatement sentencia4 = conn.prepareStatement(update4);
                String hoursUpdated=JOptionPane.showInputDialog("Escriba la nueva cantidad de horas: ");
                sentencia4.setString(1, hoursUpdated);
                sentencia4.setString(2, idActualizar);
                int rowsUpdated4 = sentencia4.executeUpdate();
                conn.close();
                limpiarTabla();
                verMaterias();
            } catch (SQLException ex) {
                Logger.getLogger(Grupo.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Debe seleccionar una fila");
        }*/
    }//GEN-LAST:event_jButtonActualizarActionPerformed

    private void jButtonLeerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLeerActionPerformed
        try {
            limpiarTabla();
            verGrupos();
        } catch (SQLException ex) {
            Logger.getLogger(Grupo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonLeerActionPerformed

    private void jButtonCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCrearActionPerformed
        crearGrupo();
    }//GEN-LAST:event_jButtonCrearActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cmbIdSemestre;
    private javax.swing.JButton jButtonActualizar;
    private javax.swing.JButton jButtonCrear;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JButton jButtonLeer;
    private javax.swing.JButton jButtonLimpiar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelIDGrupo;
    private javax.swing.JLabel jLabelIdSemestre;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableGrupo;
    private javax.swing.JTextField txtIdGrupo;
    // End of variables declaration//GEN-END:variables
}
