/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import java.sql.*;
import javax.swing.JOptionPane;
import conexiones.Conexion;
import java.awt.Color;
/**
 *
 * @author aaron
 */
public class agregarConsulta extends javax.swing.JInternalFrame {

    private String fecha;
    private int idinfo;
    private int idp;
    private int idC;
    public agregarConsulta() {
        initComponents();
        ObtenerIdinfor();
    }
    private void ObtenerIdinfor(){
        try {
            Connection con;
            Conexion conect = new Conexion(Ventana.getruta());
            con = conect.getConnection();
            String sql = "select Id from Informe ORDER BY Id DESC LIMIT 1";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                idinfo= rs.getInt("Id");  
            }
            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,"error al buscar el informe "+e);
        }
    }
    private void obtenerIdconsulta(){
        Connection con;
        Conexion conect = new Conexion(Ventana.getruta());
        con = conect.getConnection();
        String sql = "select Id from Consultas where id_paciente='"+idp+"' and fecha='"+fecha+"'";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                idC = rs.getInt("Id");
            }
            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,"problema con obtener el ID de la consulta"+e);
        }
    }
    private void InsertarEnRecopilacion(){
        try {
        Connection con;
        Conexion conect = new Conexion(Ventana.getruta());
        con = conect.getConnection();
        Statement st = con.createStatement();
        obtenerIdconsulta();
        String sql = "insert into recopilacion(Id_informe,Id_paciente,Id_consulta) values (?,?,?)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, idinfo);
            pst.setInt(2, idp);
            pst.setInt(3, idC);
            int n = pst.executeUpdate();
            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,"problema al insertar los datos en la recopilacion de datos"+e);
        }
                
    }
    private boolean comprobar(){
        int c = 0;
        if(textCedula.getText().isEmpty()){
            textCedula.setBackground(Color.pink);
            c++;
        }
        if(textdia.getText().isEmpty()){
            textdia.setBackground(Color.pink);
            c++;
        }
        if(textmes.getText().isEmpty()){
            textmes.setBackground(Color.pink);
            c++;
        } 
        if(textanio.getText().isEmpty()){
            textanio.setBackground(Color.pink);
            c++;
        }
        if(Jdescripcion.getText().isEmpty()){
            Jdescripcion.setBackground(Color.pink);
            c++;
        }
        if(Recipe.getText().isEmpty()){
            Recipe.setBackground(Color.pink);
            c++;
        }
        if(Indicaciones.getText().isEmpty()){
            Indicaciones.setBackground(Color.pink);
            c++;
        }
        if(edad.getText().isEmpty()){
            edad.setBackground(Color.pink);
            c++;
        }
        if(!textCedula.getText().matches("\\d{1,}")&& !textCedula.getText().isEmpty()){
            textCedula.setBackground(Color.pink);
            c++;
            JOptionPane.showMessageDialog(this,"No introduzca letras en la cedula");
        }
        if(!edad.getText().matches("\\d{1,}")&& !edad.getText().isEmpty()){
            edad.setBackground(Color.pink);
            c++;
            JOptionPane.showMessageDialog(this,"No introduzca letras en la edad");
        }
        if(!textdia.getText().matches("\\d{1,}")&& !textdia.getText().isEmpty()){
            textdia.setBackground(Color.pink);
            c++;
            JOptionPane.showMessageDialog(this,"No introduzca letras en el dia");
        }
        if(!textmes.getText().matches("\\d{1,}")&& !textmes.getText().isEmpty()){
            textmes.setBackground(Color.pink);
            c++;
            JOptionPane.showMessageDialog(this,"No introduzca letras en el mes");
        }
        if(!textanio.getText().matches("\\d{1,}")&& !textanio.getText().isEmpty()){
            textanio.setBackground(Color.pink);
            c++;
            JOptionPane.showMessageDialog(this,"No introduzca letras en el año");
        }
        if(textmes.getText().matches("\\d{1,}")&&textdia.getText().matches("\\d{1,}")){
            if(Integer.parseInt(textmes.getText())==2&&Integer.parseInt(textdia.getText())>29){
                textdia.setBackground(Color.pink);
                c++;
                JOptionPane.showMessageDialog(this,"Dia incorrecto");
            }  
        }
        if(textdia.getText().matches("\\d{1,}")){
            if(Integer.parseInt(textdia.getText())>31||Integer.parseInt(textdia.getText())<1){
                textdia.setBackground(Color.pink);
                c++;
                JOptionPane.showMessageDialog(this,"Dia incorrecto");
            } 
            if(textdia.getText().length()<2){
                textdia.setBackground(Color.pink);
                c++;
                JOptionPane.showMessageDialog(this,"Debe colocar una fecha con dos digitos, por ejemplo: 04");  
            }
            if(textdia.getText().length() > 2)
            {
                textdia.setBackground(Color.pink);
                c++;
                JOptionPane.showMessageDialog(this,"Dia incorrecto");  
            }
        }
        if(textmes.getText().matches("\\d{1,}")){
            if(textmes.getText().length() < 2){
                textmes.setBackground(Color.pink);
                c++;
                JOptionPane.showMessageDialog(this, "debe colocar una fecha con dos digitos, ejemplo: 01");
            }
            if(Integer.parseInt(textmes.getText())>12||Integer.parseInt(textmes.getText())<1){
                textmes.setBackground(Color.pink);
                c++;
                JOptionPane.showMessageDialog(this,"Mes incorrecto");
            }  
        }
        if(textanio.getText().length()<4 || textanio.getText().length() > 4){
            textanio.setBackground(Color.pink);
            c++;
            JOptionPane.showMessageDialog(this, "año incorrecto");
        }
        if(((String)jComboBox1.getSelectedItem()).equals("...")){
            c++;
            JOptionPane.showMessageDialog(this,"Seleccione el tipo de patologia");
        }
        return c == 0;
    }
    private void vaciar(){
        textCedula.setText("");
        textdia.setText("");
        textmes.setText("");
        textanio.setText("");
        Jdescripcion.setText("");
        Recipe.setText("");
        Indicaciones.setText("");
        edad.setText("");
    }
    private int getIdbyCedula(String cedula){
        try {
            Connection con;
            Conexion conect = new Conexion(Ventana.getruta());
            con = conect.getConnection();
            int id = 0;
            String sql = "select Id_paciente from Pacientes where cedula='"+cedula+"'";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                id = Integer.parseInt(rs.getString("Id_paciente"));  
            }
            con.close();
            return id;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,"error al buscar"+e);
            return 0;
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        textCedula = new javax.swing.JTextField();
        textdia = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        textanio = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        textmes = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        edad = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        Jdescripcion = new javax.swing.JTextArea();
        jScrollPane1 = new javax.swing.JScrollPane();
        Recipe = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        Indicaciones = new javax.swing.JTextArea();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(51, 51, 51));
        setBorder(null);
        setClosable(true);
        setIconifiable(true);
        setTitle("Informe Medico");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/imageneseiconos/Consulta.png"))); // NOI18N

        jPanel2.setBackground(new java.awt.Color(51, 51, 51));

        textCedula.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        textCedula.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textCedulaMouseClicked(evt);
            }
        });

        textdia.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        textdia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textdiaMouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("fecha");

        textanio.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        textanio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textanioMouseClicked(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Diagnostico");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("CI del paciente:");

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setText("Crear consulta");
        jButton1.setOpaque(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("/");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("/");

        textmes.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        textmes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textmesMouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Crear Informe Medico");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Recipe:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Indicaciones:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Edad del paciente");

        edad.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        edad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                edadMouseClicked(evt);
            }
        });

        Jdescripcion.setColumns(20);
        Jdescripcion.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        Jdescripcion.setLineWrap(true);
        Jdescripcion.setRows(5);
        jScrollPane4.setViewportView(Jdescripcion);

        Recipe.setColumns(20);
        Recipe.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        Recipe.setLineWrap(true);
        Recipe.setRows(5);
        jScrollPane1.setViewportView(Recipe);

        Indicaciones.setColumns(20);
        Indicaciones.setFont(new java.awt.Font("Arial", 0, 10)); // NOI18N
        Indicaciones.setLineWrap(true);
        Indicaciones.setRows(5);
        jScrollPane2.setViewportView(Indicaciones);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "...", "ABSCESOS", "AMIGDALITIS AGUDA", "ANEMIAS", "ASMA", "BRONQUITIS", "BRONQUITIS AGUDA", "BRONQUITIS CRÓNICA", "CEFALEA", "DERMATITIS", "DIARREA", "DOLOR ABDOMINAL", "ESCABIOSIS", "FARINGITIS AGUDA", "FIEBRE", "HELMINTIASIS (PARASITOS)", "HERIDAS", "HIPERTENSIÓN ARTERIAL", "INFECCION RESPIRATORIA", "INFECCIÓN URINARIA", "MIALGIAS", "NEUMONIA", "NEURALGIAS", "OTITIS", "OTRAS ENF ESOFAGO ESTOMAGO E INTESTINO ", "PIODERMITIS", "RINOFARINGITIS AGUDA", "SINDROME VIRAL", "SINDROME EMETICO", "OTROS TRAUMATISMOS", "OTROS:" }));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Patologia");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel11))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textdia, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textmes, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textanio, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(edad))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(edad, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(textdia, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(textanio, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(textmes, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textanioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textanioMouseClicked
        textanio.setBackground(Color.white);
    }//GEN-LAST:event_textanioMouseClicked

    private void textdiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textdiaMouseClicked
        textdia.setBackground(Color.white);
    }//GEN-LAST:event_textdiaMouseClicked

    private void textCedulaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textCedulaMouseClicked
        textCedula.setBackground(Color.white);
    }//GEN-LAST:event_textCedulaMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(comprobar()){
            if(textCedula.getText().matches("\\d{1,}")){
                idp = getIdbyCedula(textCedula.getText());
                if(idp == 0){
                    JOptionPane.showMessageDialog(this,"El paciente no existe en la base de datos");
                    agregarPaciente v = new agregarPaciente(textCedula.getText());
                    Ventana.getPanel().add(v);
                    v.toFront();
                    v.setVisible(true);
                } else {
                    try {
                        Connection con;
                        Conexion conect = new Conexion(Ventana.getruta());
                        con = conect.getConnection();
                        String sql = "insert into Consultas(fecha,id_paciente,Diagnostico,Recipe,Indicaciones,edad,patologia) values (?,?,?,?,?,?,?)";
                        PreparedStatement pst = con.prepareStatement(sql);
                        pst.setString(1, textdia.getText()+"/"+textmes.getText()+"/"+textanio.getText());
                        fecha = textdia.getText()+"/"+textmes.getText()+"/"+textanio.getText();
                        pst.setInt(2, idp);
                        pst.setString(3, Jdescripcion.getText());
                        pst.setString(4, Recipe.getText());
                        pst.setString(5, Indicaciones.getText());
                        pst.setString(6, edad.getText());
                        pst.setString(7, (String)jComboBox1.getSelectedItem());
                        int n = pst.executeUpdate();
                        if(n>0){
                            InsertarEnRecopilacion();
                            JOptionPane.showMessageDialog(this, "DATOS GUARDADOS CORRECTAMENTE");
                            conect.Desconexion();
                            Informe1 v = new Informe1(textCedula.getText(),fecha,Jdescripcion.getText(),Recipe.getText(),Indicaciones.getText(),edad.getText());
                            Ventana.getPanel().add(v);
                            v.toFront();
                            v.setVisible(true);
                            vaciar();
                        }
                        con.close();
                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(this,"Problema al registrar la consulta" + e);
                    }
                }
            } else {
                textCedula.setBackground(Color.pink);
                JOptionPane.showMessageDialog(this,"No introduzca letras o simbolos en el numero de cedula");
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void textmesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textmesMouseClicked
        textmes.setBackground(Color.white);
    }//GEN-LAST:event_textmesMouseClicked

    private void edadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_edadMouseClicked
        edad.setBackground(Color.white);
    }//GEN-LAST:event_edadMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea Indicaciones;
    private javax.swing.JTextArea Jdescripcion;
    private javax.swing.JTextArea Recipe;
    private javax.swing.JTextField edad;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField textCedula;
    private javax.swing.JTextField textanio;
    private javax.swing.JTextField textdia;
    private javax.swing.JTextField textmes;
    // End of variables declaration//GEN-END:variables
}
