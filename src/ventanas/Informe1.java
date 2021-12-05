/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import conexiones.Conexion;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

import javax.swing.JOptionPane;

public class Informe1 extends javax.swing.JInternalFrame {

    private String Nombre;
    private String Fecha;
    private String FechaE;
    private String Cedula;
    private String Sexo;
    private String dig;
    private String rp;
    private String ind;
    private String edad;
    
    public Informe1(String idp, String idc) {
        paciente(idp);
        consulta(idc);
        initComponents();
        setTitle(Nombre+" "+Fecha);
        FechaE = Fecha.split("/")[0]+Fecha.split("/")[1]+Fecha.split("/")[2];
        informeM1.info(Cedula, Nombre, Fecha, Sexo, dig, rp, ind, edad);
    }
    public Informe1(String cedula,String fecha,String dig,String rp, String ind,String edad){
        initComponents();
        paciente2(cedula);
        setTitle(Nombre+" "+Fecha);
        this.Cedula = cedula;
        this.Fecha = fecha;
        FechaE = Fecha.split("/")[0]+Fecha.split("/")[1]+Fecha.split("/")[2];
        this.dig = dig;
        this.rp = rp;
        this.ind = ind;
        this.edad = edad;
        informeM1.info(Cedula, Nombre, Fecha, Sexo, this.dig, this.rp, this.ind, this.edad);
    }
    private void paciente2(String cedula){
        try {
        Connection con;
        Conexion conect = new Conexion(Ventana.getruta());
        con = conect.getConnection();
        String sql = "select nombre,apellido,sexo from Pacientes where cedula='"+cedula+"'";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        while(rs.next()){ 
            Nombre = rs.getString("nombre")+" "+rs.getString("apellido");
            Sexo = rs.getString("sexo");
        }
        con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,"Probema con la busqueda "+e);
        }
    }
    private void paciente(String id){
        try {
                    Connection con;
                    Conexion conect = new Conexion(Ventana.getruta());
                    con = conect.getConnection();
                    String sql = "select * from Pacientes where Id_paciente='"+id+"'";
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery(sql);
                    while(rs.next()){ 
                        Nombre = rs.getString("nombre")+" "+rs.getString("apellido");
                        Cedula = rs.getString("cedula");
                        Sexo = rs.getString("sexo");
                    }
                    con.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(this,"Probema con la busqueda "+e);
                }
    }
    private void consulta(String id){
            try {
                    Connection con;
                    Conexion conect = new Conexion(Ventana.getruta());
                    con = conect.getConnection();
                    String sql = "select * from Consultas where id='"+id+"'";
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery(sql);
                    while(rs.next()){ 
                        dig = rs.getString("Diagnostico");
                        rp= rs.getString("Recipe");
                        ind = rs.getString("Indicaciones");
                        Fecha=rs.getString("fecha");
                        edad=rs.getString("edad");
                    }
                    con.close();
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(this,"Probema con la busqueda "+e);
                }
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
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        informeM1 = new ventanas.InformeM();

        setClosable(true);
        setIconifiable(true);
        setResizable(true);
        setTitle("Informe Tamaño legal");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/imageneseiconos/Impresora.png"))); // NOI18N

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imageneseiconos/Impresora.png"))); // NOI18N
        jButton1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/imageneseiconos/ImpresoraH.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(informeM1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 604, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
//        PrinterJob pj = PrinterJob.getPrinterJob();
//        pj.setPrintable(informeM1);
//        if(pj.printDialog()){
//            try {
//                pj.print();
//                JOptionPane.showMessageDialog(this, "Operacion Exitosa");
//            } catch (PrinterException ex) {
//                Logger.getLogger(Informe1.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        } else {
//            JOptionPane.showMessageDialog(this,"impresion cancelada");
//        }
        BufferedImage imagen = new BufferedImage(informeM1.getWidth(), informeM1.getHeight(), BufferedImage.TYPE_INT_ARGB);
        informeM1.paint(imagen.getGraphics());
       try {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.showOpenDialog(fileChooser);
            String ruta = fileChooser.getSelectedFile().getAbsolutePath(); 
            ImageIO.write(imagen, "png", new File(ruta+".png"));
            JOptionPane.showMessageDialog(this, "imagen guardada");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar imagen "+ ex);
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private ventanas.InformeM informeM1;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
