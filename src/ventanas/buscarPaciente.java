/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import conexiones.Conexion;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author aaron
 */
public class buscarPaciente extends javax.swing.JInternalFrame {

    public buscarPaciente() {
        initComponents();
        CedulaT.setVisible(false);
        jLabel17.setVisible(false);
        Empresa.setVisible(false);
        jLabel18.setVisible(false);
    }
    public buscarPaciente(String cedula) {
        initComponents();
        Busqueda.setText(cedula);
        buscarP();
        CedulaT.setVisible(false);
        jLabel17.setVisible(false);
        Empresa.setVisible(false);
        jLabel18.setVisible(false);
    }
    
    private void buscarP(){
       if(Busqueda.getText().isEmpty()){
           
        } else {
            if(Busqueda.getText().matches("\\d{1,}")){
                try {
                    Connection con;
                    Conexion conect = new Conexion(Ventana.getruta());
                    con = conect.getConnection();
                    String cedula = Busqueda.getText();
                    String sql = "select * from Pacientes where cedula='"+cedula+"'";
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery(sql);
                    int id = 0;
                    while(rs.next()){ 
                        id = rs.getInt("Id_paciente");
                        nombre.setText(rs.getString("nombre"));
                        Apellido.setText(rs.getString("apellido"));
                        Cedula.setText(rs.getString("cedula"));
                        sexo.setText(rs.getString("sexo"));
                        direccion.setText(rs.getString("direccion"));
                        telefono.setText(rs.getString("numerotlf"));
                        descripcion.setText(rs.getString("descripcion"));
                        Aspecto.setText(rs.getString("Aspecto"));
                        CedulaT.setText(rs.getString("CedulaTitular"));
                        Empresa.setText(rs.getString("Empresa"));
                        jComboBox1.removeAllItems();
                    }
                    con.close();
                    if(Aspecto.getText().equals("Familiar de Trabajador")){
                        jLabel17.setVisible(true);
                        CedulaT.setVisible(true);
                    }
                    if(Aspecto.getText().equals("Personal Empresa que hace vida en las instalaciones")||Aspecto.getText().equals("Otras Instituciones del estado")){
                        jLabel18.setVisible(true);
                        Empresa.setVisible(true);
                    }
                    if(!rs.next() && id == 0){
                        JOptionPane.showMessageDialog(this,"No se ha encontrado el paciente");
                        agregarPaciente v = new agregarPaciente(Busqueda.getText());
                        Ventana.getPanel().add(v);
                        v.toFront();
                        v.setVisible(true);
                    }
                    buscarConsultas(id);
                    Busqueda.setText("");
                } catch(NullPointerException e){
                    JOptionPane.showMessageDialog(this,"El paciente no tiene consultas registradas");
                }catch (SQLException e) {
                    JOptionPane.showMessageDialog(this,"Probema con la busqueda "+e);
                }  
            }
            else {
                JOptionPane.showMessageDialog(this,"No introduzca letras o simbolos en el numero de cedula");
            }
        }        
    }
    
    
    private void buscarConsultas(int id){
        try {
            Connection con;
            Conexion conect = new Conexion(Ventana.getruta());
            con = conect.getConnection();
            String sql = "select id,fecha from Consultas where id_paciente='"+id+"' ORDER BY id DESC";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){ 
                jComboBox1.addItem(id+" "+rs.getString("id")+" "+rs.getString("fecha"));
            }
            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,"Probema con la busqueda "+e);
        } catch (NullPointerException e){
            JOptionPane.showMessageDialog(this, "Este paciente no tiene consultas registradas.");
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        telefono = new javax.swing.JLabel();
        sexo = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        Cedula = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        direccion = new javax.swing.JTextArea();
        Busqueda = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        nombre = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        descripcion = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        Apellido = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        Aspecto = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        CedulaT = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        Empresa = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setTitle("Buscar por numero de cedula");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/imageneseiconos/Buscar.png"))); // NOI18N
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Sexo:");

        telefono.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        telefono.setForeground(new java.awt.Color(255, 255, 255));
        telefono.setText("...");

        sexo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        sexo.setForeground(new java.awt.Color(255, 255, 255));
        sexo.setText("...");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Descripcion:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("consultas:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Apellido:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Direccion:");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imageneseiconos/BuscarH.png"))); // NOI18N
        jButton1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imageneseiconos/Buscar.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jComboBox1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        Cedula.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Cedula.setForeground(new java.awt.Color(255, 255, 255));
        Cedula.setText("...");

        direccion.setEditable(false);
        direccion.setColumns(20);
        direccion.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        direccion.setLineWrap(true);
        direccion.setRows(5);
        jScrollPane3.setViewportView(direccion);

        Busqueda.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Busqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                BusquedaKeyReleased(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("CI:");

        nombre.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        nombre.setForeground(new java.awt.Color(255, 255, 255));
        nombre.setText("...");

        descripcion.setEditable(false);
        descripcion.setColumns(20);
        descripcion.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        descripcion.setLineWrap(true);
        descripcion.setRows(5);
        jScrollPane2.setViewportView(descripcion);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nombre: ");

        Apellido.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Apellido.setForeground(new java.awt.Color(255, 255, 255));
        Apellido.setText("...");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Telefono:");

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Aspecto:");

        Aspecto.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Aspecto.setForeground(new java.awt.Color(255, 255, 255));
        Aspecto.setText("...");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Cedula titular:");

        CedulaT.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        CedulaT.setForeground(new java.awt.Color(255, 255, 255));
        CedulaT.setText("...");

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Empresa:");

        Empresa.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        Empresa.setForeground(new java.awt.Color(255, 255, 255));
        Empresa.setText("...");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(Busqueda)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(21, 21, 21))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(Apellido))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(telefono))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Cedula))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sexo))
                            .addComponent(jLabel6)
                            .addComponent(jLabel10)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(nombre))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 346, Short.MAX_VALUE)
                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 492, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel16)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(Aspecto))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel17)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(CedulaT))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel18)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(Empresa)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Busqueda, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 15, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(nombre))
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(Apellido))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel17)
                                .addComponent(CedulaT))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(Cedula)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(sexo)
                            .addComponent(jLabel18)
                            .addComponent(Empresa))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(telefono))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(77, 77, 77))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(Aspecto))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        buscarP();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        if((evt.getModifiers()&16)!=0){
            String info = (String) jComboBox1.getSelectedItem();
            String id_p = info.split(" ")[0];
            String id_con = info.split(" ")[1];
            Informe1 V = new Informe1(id_p, id_con);
            Ventana.getPanel().add(V);
            V.toFront();
            V.setVisible(true); 
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed

    }//GEN-LAST:event_formKeyPressed

    private void BusquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BusquedaKeyReleased
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            buscarP();
        }   
    }//GEN-LAST:event_BusquedaKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Apellido;
    private javax.swing.JLabel Aspecto;
    private javax.swing.JTextField Busqueda;
    private javax.swing.JLabel Cedula;
    private javax.swing.JLabel CedulaT;
    private javax.swing.JLabel Empresa;
    private javax.swing.JTextArea descripcion;
    private javax.swing.JTextArea direccion;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel nombre;
    private javax.swing.JLabel sexo;
    private javax.swing.JLabel telefono;
    // End of variables declaration//GEN-END:variables
}
