/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexiones;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
import ventanas.Ventana;

/**
 *
 * @author aaron
 */
public class LectorArchivo {
    private FileWriter fw;
    private FileReader fr;
    private String rt;
    private int c;
    public LectorArchivo(){
        rt="";
    }
    public void escribir(String r){
        try {
            fw = new FileWriter("C:\\SGP\\files\\conexion.txt");
            fw.write(r);
            fw.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(Ventana.getPanel(),"problema con la lectura del archivo para establecer la conexion "+ex);
        }
    }
    public String Buscar(){
        try {
            fr = new FileReader("C:\\SGP\\files\\conexion.txt");
            c=fr.read();
            while(c!=-1){
                rt+=(char)c;
                c=fr.read();
            }
            return rt;
        } catch (IOException e) {
            JOptionPane.showMessageDialog(Ventana.getPanel(),"problema con la lectura del archivo para establecer la conexion "+e);
            return "";
        }
    }
}
