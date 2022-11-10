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

public class AsignarMaterias extends javax.swing.JInternalFrame {

    public int idGrupo;
    public int idSemestre;
    public int idMateria;
    public String nombreMateria;
    ArrayList<AsignarMaterias> materiasAsignadas = new ArrayList<>();

    public AsignarMaterias(int idGrupo, String nombreMateria, int idSemestre, int idMateria) {
        this.idGrupo = idGrupo;
        this.nombreMateria = nombreMateria;
        this.idSemestre = idSemestre;
        this.idMateria = idMateria;

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

    public int getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public boolean asignarMateria() {
        Connection conn = Conexion.conectar();
        String sql = "insert into asignarmaterias (idMateria, idSemestre, idGrupo) values (? ,?, ?)";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, Integer.parseInt(txtIdMateria.getText()));
            statement.setInt(2, Integer.parseInt(txtIdGrupo.getText()));
            statement.setInt(3, Integer.parseInt(cmbIdSemestre.getSelectedItem().toString()));
            int rowsInserted = statement.executeUpdate();
            conn.close();
            verMateriasAsignadas();
            if (rowsInserted > 0) {
                return true;
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, ex);

            //JOptionPane.showMessageDialog(null, "Error insertando materia");
        }
        return false;
    }

    public ArrayList verMateriasAsignadas() throws SQLException {
        limpiarTabla();
        materiasAsignadas.clear();
        String identifGrupo, identifSemestre;
        identifSemestre = cmbIdSemestre.getSelectedItem().toString();
        identifGrupo = txtIdGrupo.getText();
        try {
            Connection conn = Conexion.conectar();
            String select1 = "select asignarMaterias.idMateria, materia.Nombre_Materia, asignarMaterias.idGrupo, asignarMaterias.idSemestre from "
                    + "asignarMaterias left join materia on asignarMaterias.idMateria = materia.idMateria where "
                    + "asignarMaterias.idGrupo = ? and asignarMaterias.idSemestre = ? order by asignarMaterias.idGrupo";
            PreparedStatement sentencia1 = conn.prepareStatement(select1);
            sentencia1.setString(1, identifGrupo);
            sentencia1.setString(2, identifSemestre);
            ResultSet result = sentencia1.executeQuery();
            while (result.next()) {
                int idSubject = result.getInt(1);
                String nameSubject = result.getString(2);
                int idGroup = result.getInt(3);
                int idSemester = result.getInt(4);
                AsignarMaterias am = new AsignarMaterias(idSubject, nameSubject, idGroup, idSemester);
                materiasAsignadas.add(am);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }
        try {
            String datos[][] = new String[materiasAsignadas.size()][4];
            for (int i = 0; i < materiasAsignadas.size(); i++) {
                datos[i][0] = String.valueOf(materiasAsignadas.get(i).idMateria);
                datos[i][1] = materiasAsignadas.get(i).nombreMateria;
                datos[i][2] = String.valueOf(materiasAsignadas.get(i).idGrupo);
                datos[i][3] = String.valueOf(materiasAsignadas.get(i).idSemestre);
            }
            jTableAsignarMaterias.setModel(new javax.swing.table.DefaultTableModel(
                    datos, new String[]{
                        "Codigo Materia", "Nombre Materia", "Grupo", "Semestre"
                    }));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }
        return materiasAsignadas;
    }

    public void limpiarTabla() {
        txtIdGrupo.setText("");
        cmbIdSemestre.setSelectedIndex(0);
        txtIdMateria.setText("");
        String vacio[][] = {{"", "", ""}, {"", "", ""}};
        jTableAsignarMaterias.setModel(new javax.swing.table.DefaultTableModel(
                vacio, new String[]{
                    "Coodigo Materia", "Nombre Materia", "Grupo", "Semestre"
                }
        ));
    }

    public AsignarMaterias() {
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
        jLabelIdMateria = new javax.swing.JLabel();
        txtIdMateria = new javax.swing.JTextField();
        jButtonCrear = new javax.swing.JButton();
        jButtonLeer = new javax.swing.JButton();
        jButtonActualizar = new javax.swing.JButton();
        jButtonEliminar = new javax.swing.JButton();
        jButtonLimpiar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableAsignarMaterias = new javax.swing.JTable();

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
        jLabelIDGrupo.setText("Numero de Grupo:");
        jLabelIDGrupo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabelIdSemestre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelIdSemestre.setText("Semestre:");
        jLabelIdSemestre.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        cmbIdSemestre.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));

        jLabelIdMateria.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelIdMateria.setText("Codigo de Materia:");
        jLabelIdMateria.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

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

        jTableAsignarMaterias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Codigo Materia", "Nombre Materia", "Grupo", "Semestre"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableAsignarMaterias.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jScrollPane1.setViewportView(jTableAsignarMaterias);

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
                            .addComponent(jButtonCrear)
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
                        .addGap(243, 243, 243)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabelIdMateria)
                            .addComponent(jLabelIDGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelIdSemestre))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtIdGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIdMateria, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbIdSemestre, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 649, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabelIDGrupo, jLabelIdSemestre});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButtonActualizar, jButtonCrear, jButtonEliminar, jButtonLeer, jButtonLimpiar});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelIDGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelIdSemestre)
                    .addComponent(cmbIdSemestre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtIdMateria, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelIdMateria))
                .addGap(21, 21, 21)
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
                .addContainerGap(94, Short.MAX_VALUE)
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
        int confirmación = JOptionPane.showConfirmDialog(null, "Esta acción afectará la programación final. ¿Está segur@ que desea eliminar?", "Confirmar borrado", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (confirmación == 0) {
            int filaseleccionada = jTableAsignarMaterias.getSelectedRow();
            String idBorrarGrupo, idBorrarSemestre, idBorrarMateria;
            if (filaseleccionada != -1) {
                idBorrarMateria = ((String) jTableAsignarMaterias.getValueAt(filaseleccionada, 0));
                idBorrarGrupo = ((String) jTableAsignarMaterias.getValueAt(filaseleccionada, 2));
                idBorrarSemestre = ((String) jTableAsignarMaterias.getValueAt(filaseleccionada, 3));
                try {
                    Connection conn = Conexion.conectar();
                    String delete1 = "SET foreign_key_checks = 0";
                    String delete2 = "delete from asignarMaterias where idMateria = ? and idGrupo=? and idSemestre=? ;";
                    String delete3 = "SET foreign_key_checks = 1";
                    PreparedStatement sentencia1 = conn.prepareStatement(delete1);
                    PreparedStatement sentencia2 = conn.prepareStatement(delete2);
                    PreparedStatement sentencia3 = conn.prepareStatement(delete3);
                    sentencia2.setString(1, idBorrarMateria);
                    sentencia2.setString(2, idBorrarGrupo);
                    sentencia2.setString(3, idBorrarSemestre);
                    sentencia1.executeUpdate();
                    int rowsDeleted = sentencia2.executeUpdate();
                    sentencia3.executeUpdate();
                    conn.close();
                    limpiarTabla();
                    verMateriasAsignadas();
                } catch (SQLException ex) {
                    Logger.getLogger(AsignarMaterias.class.getName()).log(Level.SEVERE, null, ex);
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
            verMateriasAsignadas();
        } catch (SQLException ex) {
            Logger.getLogger(AsignarMaterias.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonLeerActionPerformed

    private void jButtonCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCrearActionPerformed
        asignarMateria();
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
    private javax.swing.JLabel jLabelIdMateria;
    private javax.swing.JLabel jLabelIdSemestre;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableAsignarMaterias;
    private javax.swing.JTextField txtIdGrupo;
    private javax.swing.JTextField txtIdMateria;
    // End of variables declaration//GEN-END:variables
}
