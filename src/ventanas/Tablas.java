/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventanas;

import conexiones.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;



public class Tablas extends javax.swing.JInternalFrame {

    private String dato[] = new String[11];
    private String dia[] = new String[7];
    private int Res[][] = new int[14][30];
    private int idia = 0;
    private DefaultTableModel tm,tm2,tm3,tm4;
    private LinkedHashMap<String,Integer> aspecto = new LinkedHashMap<>();
    private LinkedHashMap<String,Integer> patologias = new LinkedHashMap<>();
    private LinkedHashMap<String,Integer> patologiasIn = new LinkedHashMap<>();
    private LinkedHashMap<String,Integer> pacientesxdia = new LinkedHashMap<>();
    private LinkedHashMap<String,Integer> Hxdia = new LinkedHashMap<>();
    private LinkedHashMap<String,Integer> Mxdia = new LinkedHashMap<>();
    private int total, totalM, totalF;
    public Tablas() {
        initComponents();
        LlenarCombo();
        tm = (DefaultTableModel) jTable1.getModel();
        tm2 = (DefaultTableModel) jTable2.getModel();
        tm3 = (DefaultTableModel) jTable3.getModel();
        tm4 = (DefaultTableModel) jTable4.getModel();
        aspecto.put("Trabajador Titular",0);
        aspecto.put("Familiar de Trabajador",0);
        aspecto.put("Personal Empresa que hace vida en las instalaciones",0);
        aspecto.put("Otras Instituciones del estado",0);
        aspecto.put("Casos Comunitarios",0);
        total = 0;
        totalM = 0;
        totalF = 0;
        patologias.put("ABSCESOS",0);
        patologias.put("AMIGDALITIS AGUDA",0);
        patologias.put("ANEMIAS",0);
        patologias.put("ASMA",0);
        patologias.put("BRONQUITIS",0);
        patologias.put("BRONQUITIS AGUDA",0);
        patologias.put("BRONQUITIS CRÓNICA",0);
        patologias.put("CEFALEA",0);
        patologias.put("DERMATITIS",0);
        patologias.put("DIARREA",0);
        patologias.put("DOLOR ABDOMINAL",0);
        patologias.put("ESCABIOSIS",0);
        patologias.put("FARINGITIS AGUDA",0);
        patologias.put("FIEBRE",0);
        patologias.put("HELMINTIASIS (PARASITOS)",0);
        patologias.put("HERIDAS",0);
        patologias.put("HIPERTENSIÓN ARTERIAL",0);
        patologias.put("INFECCION RESPIRATORIA",0);
        patologias.put("INFECCIÓN URINARIA",0);
        patologias.put("MIALGIAS",0);
        patologias.put("NEUMONIA",0);
        patologias.put("NEURALGIAS",0);
        patologias.put("OTITIS",0);
        patologias.put("OTRAS ENF ESOFAGO ESTOMAGO E INTESTINO",0); 
        patologias.put("PIODERMITIS",0);
        patologias.put("RINOFARINGITIS AGUDA",0);
        patologias.put("SINDROME VIRAL",0);
        patologias.put("SINDROME EMETICO",0);
        patologias.put("OTROS TRAUMATISMOS",0);
        patologias.put("OTROS:",0);
        
        patologiasIn.put("ABSCESOS",0);
        patologiasIn.put("AMIGDALITIS AGUDA",1);
        patologiasIn.put("ANEMIAS",2);
        patologiasIn.put("ASMA",3);
        patologiasIn.put("BRONQUITIS",4);
        patologiasIn.put("BRONQUITIS AGUDA",5);
        patologiasIn.put("BRONQUITIS CRÓNICA",6);
        patologiasIn.put("CEFALEA",7);
        patologiasIn.put("DERMATITIS",8);
        patologiasIn.put("DIARREA",9);
        patologiasIn.put("DOLOR ABDOMINAL",10);
        patologiasIn.put("ESCABIOSIS",11);
        patologiasIn.put("FARINGITIS AGUDA",12);
        patologiasIn.put("FIEBRE",13);
        patologiasIn.put("HELMINTIASIS (PARASITOS)",14);
        patologiasIn.put("HERIDAS",15);
        patologiasIn.put("HIPERTENSIÓN ARTERIAL",16);
        patologiasIn.put("INFECCION RESPIRATORIA",17);
        patologiasIn.put("INFECCIÓN URINARIA",18);
        patologiasIn.put("MIALGIAS",19);
        patologiasIn.put("NEUMONIA",20);
        patologiasIn.put("NEURALGIAS",21);
        patologiasIn.put("OTITIS",22);
        patologiasIn.put("OTRAS ENF ESOFAGO ESTOMAGO E INTESTINO",23); 
        patologiasIn.put("PIODERMITIS",24);
        patologiasIn.put("RINOFARINGITIS AGUDA",25);
        patologiasIn.put("SINDROME VIRAL",26);
        patologiasIn.put("SINDROME EMETICO",27);
        patologiasIn.put("OTROS TRAUMATISMOS",28);
        patologiasIn.put("OTROS:",29);
    }
    private void LlenarCombo(){
        Connection con;
        Conexion conect = new Conexion(Ventana.getruta());
        con = conect.getConnection();
        String sql = "select * from Informe ORDER BY Id DESC";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                jComboBox1.addItem(rs.getString("Id")+" Desde: "+rs.getString("Fecha_inicio")+" Hasta: "+rs.getString("Fecha_fin"));
            }
            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,"problema con obtener los informes "+e);
        }
    }
    private void ObtenerP(int id){
        Connection con;
        Conexion conect = new Conexion(Ventana.getruta());
        con = conect.getConnection();
        String sql = "select cedula,nombre,apellido,sexo,Aspecto,Empresa,CedulaTitular,Area from Pacientes where Id_paciente='"+id+"'";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                dato[0] = rs.getString("nombre")+" "+rs.getString("apellido");
                dato[1] = rs.getString("cedula");
                dato[2] = rs.getString("sexo");
                dato[3] = rs.getString("Aspecto");
                dato[4] = rs.getString("CedulaTitular");
                dato[5] = rs.getString("Empresa");
                dato[6] = rs.getString("Area");
            }
            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,"problema con obtener los pacientes "+e);
        }
    }
    
    private void ObtenerC(int id){
        String p = "";
        Connection con;
        Conexion conect = new Conexion(Ventana.getruta());
        con = conect.getConnection();
        String sql = "select fecha,Diagnostico,edad,patologia from Consultas where Id='"+id+"'";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                dato[7] = rs.getString("edad");
                dato[8] = rs.getString("Diagnostico");
                dato[9] = rs.getString("fecha");
                dato[10]= rs.getString("patologia");
                p = rs.getString("patologia");
                if(!pacientesxdia.containsKey(dato[9])){
                    pacientesxdia.put(dato[9],0);
                    tm4.addColumn(dato[9]);
                    dia[idia] = dato[9];
                    idia++;
                }
                if(!Hxdia.containsKey(dato[9])){
                    Hxdia.put(dato[9],0);
                }
                if(!Mxdia.containsKey(dato[9])){
                    Mxdia.put(dato[9],0);
                }
            }
        con.close();
        patologias.put(p, (patologias.get(p)+1));  
        LlenarResumen(dato[9], dato[2], patologiasIn.get(p));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,"problema con obtener los pacientes "+e);
        }
    }
    
    private void LlenarResumen(String fecha, String sexo, int patologia){
        try {
        int indice = 0;
        for(int i = 0; i < 7; i++){
            if(dia[i].equals(fecha)){
                indice = i;
                break;
            }   
        }
        switch(indice){
            case 0:
                if(sexo.equals("Masculino")){
                    Res[0][patologia]++;
                }
                if(sexo.equals("Femenino")){
                    Res[1][patologia]++;
                }
            break;
            case 1:
                if(sexo.equals("Masculino")){
                    Res[2][patologia]++;
                }
                if(sexo.equals("Femenino")){
                    Res[3][patologia]++;
                }
            break;
            case 2:
                if(sexo.equals("Masculino")){
                    Res[4][patologia]++;
                }
                if(sexo.equals("Femenino")){
                    Res[5][patologia]++;
                }
            break;
            case 3:
                if(sexo.equals("Masculino")){
                    Res[6][patologia]++;
                }
                if(sexo.equals("Femenino")){
                    Res[7][patologia]++;
                }
            break;
            case 4:
                if(sexo.equals("Masculino")){
                    Res[8][patologia]++;
                }
                if(sexo.equals("Femenino")){
                    Res[9][patologia]++;
                }
            break;
            case 5:
                if(sexo.equals("Masculino")){
                    Res[10][patologia]++;
                }
                if(sexo.equals("Femenino")){
                    Res[11][patologia]++;
                }
            break;
            case 6:
                if(sexo.equals("Masculino")){
                    Res[11][patologia]++;
                }
                if(sexo.equals("Femenino")){
                    Res[13][patologia]++;
                }
            break;
        }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }
    
    private void LlenarTabla(int idinfo){
        int idp;
        int idc;
        vaciarTabla(tm);
        Connection con;
        Conexion conect = new Conexion(Ventana.getruta());
        con = conect.getConnection();
        String sql = "select Id_paciente,Id_consulta from recopilacion where Id_informe='"+idinfo+"'";
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                idp = rs.getInt("Id_paciente");
                idc = rs.getInt("Id_consulta");
                ObtenerP(idp);
                ObtenerC(idc);
                tm.addRow(dato);
                aspecto.put(dato[3],(aspecto.get(dato[3])+1));
                pacientesxdia.put(dato[9],(pacientesxdia.get(dato[9])+1));
                if(dato[2].equals("Masculino")){
                    Hxdia.put(dato[9],(Hxdia.get(dato[9])+1));
                    totalM++;
                }
                if(dato[2].equals("Femenino")){
                    Mxdia.put(dato[9],(Mxdia.get(dato[9])+1));
                    totalF++;
                }
                total++;
            }
        con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,"problema con obtener los pacientes "+e);
        }
    }
    
    private void vaciarTabla(DefaultTableModel m){
        for( int i = m.getRowCount() - 1; i >= 0; i-- ) {
            m.removeRow(i);
        }
    }
    private void llenartablaresumen(){
        Object row[] = new Object[9];
        int i = 0;
        for (Map.Entry<String, Integer> entry : patologias.entrySet()) {
            if(entry.getValue()>0){
                row[0] = entry.getKey();
                row[1] = entry.getValue();
                row[2] = "M: "+Res[0][i]+"   F: "+Res[1][i];
                row[3] = "M: "+Res[2][i]+"   F: "+Res[3][i];
                row[4] = "M: "+Res[4][i]+"   F: "+Res[5][i];
                row[5] = "M: "+Res[6][i]+"   F: "+Res[7][i];
                row[6] = "M: "+Res[8][i]+"   F: "+Res[9][i];
                row[7] = "M: "+Res[10][i]+"   F: "+Res[11][i];
                row[8] = "M: "+Res[12][i]+"   F: "+Res[13][i];
                tm4.addRow(row);   
            }
            i++;
	}
    }
    private void llenarAspectos(){
        vaciarTabla(tm2);
        Object row[] = new Object[2];
        aspecto.entrySet().stream().map((entry) -> {
            row[0] = entry.getKey();
            return entry;
        }).map((entry) -> {
            row[1] = entry.getValue();
            return entry;
        }).forEachOrdered((_item) -> {
            tm2.addRow(row);
        });
        row[0] = "Total";
        row[1] = total;
        tm2.addRow(row);
    }
    
    private void LlenarPacientesD(){
        vaciarTabla(tm3);
        Object row[] = new Object[4];
        pacientesxdia.entrySet().stream().map((entry) -> {
            row[0] = entry.getKey();
            return entry;
        }).map((entry) -> {
            row[1] = Mxdia.get(entry.getKey());
            return entry;
        }).map((entry) -> {
            row[2] = Hxdia.get(entry.getKey());
            return entry;
        }).map((entry) -> {
            row[3] = entry.getValue();
            return entry;
        }).forEachOrdered((_item) -> {
            tm3.addRow(row);
        });
        row[0] = "Total";
        row[1] = totalF;
        row[2] = totalM;
        row[3] = total;
        tm3.addRow(row);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/imageneseiconos/TablaH.png"))); // NOI18N

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Tablas:");

        jComboBox1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jButton1.setText("Cerrar Tabla");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Aspecto", "nro"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTable2);

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Pacientes diarios ", "F", "M", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(jTable3);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Ci", "Sexo", "Aspecto", "Ci Titula", "Empresa", "Area De Adscripcion en Bolipuertos", "Edad", "Diagnostico", "Fecha", "Patologia"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Patologias Frecuentes", "Total Pacientes por patologia"
            }
        ));
        jScrollPane5.setViewportView(jTable4);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(258, 258, 258)
                        .addComponent(jButton1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 572, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 362, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jScrollPane2.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane2)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        if((evt.getModifiers()&16)!=0){
            String info = (String) jComboBox1.getSelectedItem();
            String id_infor= info.split(" ")[0];
            int id = Integer.parseInt(id_infor);
            LlenarTabla(id);
            llenarAspectos();
            LlenarPacientesD();
            llenartablaresumen();
            jComboBox1.setEnabled(false);
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        cerrarT v = new cerrarT();
        Ventana.getPanel().add(v);
        v.toFront();
        v.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    // End of variables declaration//GEN-END:variables
}
