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

public class Salon extends javax.swing.JInternalFrame {

    public String idSalon;
    public int capacidad;
    public String descripcion;
    public String elementos;
    ArrayList<Salon> salonesCreados = new ArrayList<>();

    public Salon(String idSalon, int capacidad, String descripcion, String elementos) {
        this.idSalon = idSalon;
        this.capacidad = capacidad;
        this.descripcion = descripcion;
        this.elementos = elementos;
    }

    public String getIdSalon() {
        return idSalon;
    }

    public void setIdSalon(String idSalon) {
        this.idSalon = idSalon;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getElementos() {
        return elementos;
    }

    public void setElementos(String elementos) {
        this.elementos = elementos;
    }


      
    

    public boolean crearSalon() {
        Connection conn = Conexion.conectar();
        String sql = "insert into salon values (? ,? , ?, ?)";
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, txtCodigoSalon.getText());
            statement.setInt(2, Integer.parseInt(txtCapacidad.getText()));
            statement.setString(3, txtDescripcion.getText());
            statement.setString(4, txtElementos.getText());
            int rowsInserted = statement.executeUpdate();
            conn.close();
            verSalones();
            if (rowsInserted > 0) {
                return true;
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, ex);
        }
        return false;
    }

    public ArrayList verSalones() throws SQLException {
        limpiarTabla();
        salonesCreados.clear();
        Connection conn = Conexion.conectar();
        String sql = "select * from salon";
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(sql);
        while (result.next()) {
            String id = result.getString(1);
            int capacity = result.getInt(2);
            String description = result.getString(3);
            String elements= result.getString(4);
            
            Salon s = new Salon(id, capacity, description, elements);

            salonesCreados.add(s);
        }
        try {
            String datos[][] = new String[salonesCreados.size()][4];
            for (int i = 0; i < salonesCreados.size(); i++) {
                datos[i][0] = salonesCreados.get(i).idSalon;
                datos[i][1] = String.valueOf(salonesCreados.get(i).capacidad);                
                datos[i][2] = salonesCreados.get(i).descripcion;
                datos[i][3] = salonesCreados.get(i).elementos;
            }
            jTableSalones.setModel(new javax.swing.table.DefaultTableModel(
                    datos, new String[]{
                    "Codigo Salon", "Capacidad", "Descripcion", "Elementos"
                }));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, e.getMessage());
        }
        return salonesCreados;
    }

    public void limpiarTabla() {
        txtCodigoSalon.setText("");
        txtCapacidad.setText("");
        txtDescripcion.setText("");
        txtElementos.setText("");
        String vacio[][] = {{"", "", "", ""}, {"", "", "", ""}};
        jTableSalones.setModel(new javax.swing.table.DefaultTableModel(
                vacio, new String[]{
                    "Codigo Salon", "Capacidad", "Descripcion", "Elementos"
                }
        ));
    }

    public Salon() {
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
        jLabelCodigoSalon = new javax.swing.JLabel();
        jLabelCapacidad = new javax.swing.JLabel();
        jLabelDescripcion = new javax.swing.JLabel();
        jLabelElementos = new javax.swing.JLabel();
        txtCodigoSalon = new javax.swing.JTextField();
        txtCapacidad = new javax.swing.JTextField();
        txtDescripcion = new javax.swing.JTextField();
        txtElementos = new javax.swing.JTextField();
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
        setTitle("Salones");
        setNormalBounds(new java.awt.Rectangle(0, 0, 0, 0));

        jLabel1.setFont(new java.awt.Font("Gadugi", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Licenciatura en M??sica - UPN 2022 - Version 1");

        jLabelCodigoSalon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelCodigoSalon.setText("Codigo Salon");
        jLabelCodigoSalon.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabelCapacidad.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelCapacidad.setText("Capacidad");
        jLabelCapacidad.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabelDescripcion.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelDescripcion.setText("Descripcion");
        jLabelDescripcion.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabelElementos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelElementos.setText("Elementos");
        jLabelElementos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

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
                "Codigo Salon", "Capacidad", "Descripcion", "Elementos"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
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
                            .addComponent(jLabelCodigoSalon, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCodigoSalon, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCapacidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelCapacidad))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelDescripcion))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtElementos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelElementos))
                        .addGap(178, 178, 178))))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabelCapacidad, jLabelCodigoSalon, jLabelDescripcion, jLabelElementos});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtCapacidad, txtCodigoSalon, txtDescripcion, txtElementos});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButtonActualizar, jButtonCrear, jButtonEliminar, jButtonLeer, jButtonLimpiar});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelCodigoSalon, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelCapacidad)
                    .addComponent(jLabelDescripcion)
                    .addComponent(jLabelElementos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCodigoSalon, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtElementos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCapacidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabelCapacidad, jLabelCodigoSalon, jLabelDescripcion, jLabelElementos});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtCapacidad, txtCodigoSalon, txtDescripcion, txtElementos});

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
        int confirmaci??n = JOptionPane.showConfirmDialog(null, "Esta acci??n afectar?? a todos los registros relacionados en el sistema. ??Est?? segur@ que desea eliminar?", "Confirmar borrado", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (confirmaci??n == 0) {
        int filaseleccionada = jTableSalones.getSelectedRow();
        String idBorrar;
        if (filaseleccionada != -1) {
            idBorrar = ((String) jTableSalones.getValueAt(filaseleccionada, 0));
            try {
                Connection conn = Conexion.conectar();
                String delete1 = "SET foreign_key_checks = 0";
                String delete2 = "delete from salon where idSalon = ? ";
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
                verSalones();
            } catch (SQLException ex) {
                Logger.getLogger(Salon.class.getName()).log(Level.SEVERE, null, ex);
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
                String update1 = "update salon set capacidad = ? where idSalon = ? ;";
                PreparedStatement sentencia1 = conn.prepareStatement(update1);
                String capacityUpdated=JOptionPane.showInputDialog("Escriba la nueva capacidad del salon: ");
                sentencia1.setString(1, capacityUpdated);
                sentencia1.setString(2, idActualizar);
                int rowsUpdated1 = sentencia1.executeUpdate();
                String update2 = "update salon set descripcion = ? where idSalon = ? ";
                PreparedStatement sentencia2 = conn.prepareStatement(update2);
                String descriptionUpdated=JOptionPane.showInputDialog("Escriba la nueva descripcion: ");
                sentencia2.setString(1, descriptionUpdated);
                sentencia2.setString(2, idActualizar);
                int rowsUpdated2 = sentencia2.executeUpdate();
                String update3 = "update salon set elementos = ? where idSalon = ? ";
                PreparedStatement sentencia3 = conn.prepareStatement(update3);
                String elementsUpdated=JOptionPane.showInputDialog("Escriba los nuevos elementos: ");
                sentencia3.setString(1, elementsUpdated);
                sentencia3.setString(2, idActualizar);
                int rowsUpdated3 = sentencia3.executeUpdate();                
                conn.close();
                limpiarTabla();
                verSalones();
            } catch (SQLException ex) {
                Logger.getLogger(Salon.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(rootPane, "Debe seleccionar una fila");
        }
    }//GEN-LAST:event_jButtonActualizarActionPerformed

    private void jButtonLeerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLeerActionPerformed
        try {
            limpiarTabla();
            verSalones();
        } catch (SQLException ex) {
            Logger.getLogger(Salon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonLeerActionPerformed

    private void jButtonCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCrearActionPerformed
        crearSalon();
    }//GEN-LAST:event_jButtonCrearActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonActualizar;
    private javax.swing.JButton jButtonCrear;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JButton jButtonLeer;
    private javax.swing.JButton jButtonLimpiar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelCapacidad;
    private javax.swing.JLabel jLabelCodigoSalon;
    private javax.swing.JLabel jLabelDescripcion;
    private javax.swing.JLabel jLabelElementos;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableSalones;
    private javax.swing.JTextField txtCapacidad;
    private javax.swing.JTextField txtCodigoSalon;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtElementos;
    // End of variables declaration//GEN-END:variables
}
