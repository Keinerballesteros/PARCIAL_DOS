/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.banco;

import com.mycompany.banco.managerfile.ReadFile;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;



public class Banco {
    int turno = 1;
    int turnoNoPreferencial = 1;
    Queue<Cliente> colaDePrioridades = new LinkedList<>();
    Queue<String> colaSinPrioridad = new LinkedList<>();
    
    public String[][] retornarMatriz(){
        File file = new File("clientes.txt");
        ReadFile readFile = null;
        try {
            readFile = new ReadFile(file);
        } catch (IOException ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<String> line = readFile.loadData();
        
        String matriz[][] = new String[10][6];
        for (int i = 0; i < 10; i++) {
            String[] datos = line.get(i).split(",");
            for (int j = 0; j < 6; j++) {
                matriz[i][j] = datos[j];
            }
        }
        return matriz;
    }
    
    public void solicitarTurno(){
        String id = JOptionPane.showInputDialog("Ingrese el numero de documento");
        String clientes[][] = retornarMatriz();
        for (int i = 0; i < 10; i++) {
            if(clientes[i][0].equalsIgnoreCase(id)){
                Cliente cliente = new Cliente(clientes[i][0],clientes[i][1],clientes[i][2],clientes[i][3],clientes[i][4],clientes[i][5]);
                colaDePrioridades.add(cliente);
                JOptionPane.showMessageDialog(null, "Se ha añadido a la cola preferencial");
                return;
            }
        }
        colaSinPrioridad.add(id);
        JOptionPane.showMessageDialog(null, "Se ha añadido a la cola no preferencial");
    }
    
    public String turno(){
        String nombrePreferencial = "";
        try{
            nombrePreferencial = turno +". "+ colaDePrioridades.poll().getNombre();
        }catch(Exception e ){
            turno = -1;
        }
        
        turno++;
        return nombrePreferencial;
    }
    
    public String atenderNoPreferencial(){
        String nombreNoPreferencial = "";
        
        try{
            nombreNoPreferencial = turnoNoPreferencial +". "+ colaSinPrioridad.poll().toString();
        }catch(Exception e ){
            turnoNoPreferencial = -1;
        }
        
        turnoNoPreferencial++;
        return nombreNoPreferencial;
    }
    
    public String siguienteTurno1(){
        String nombrePreferencial = "";
        try{
           nombrePreferencial = turno++ +". "+ colaDePrioridades.peek().getNombre();
        }catch(Exception e ){
        }
        return nombrePreferencial;
    }
    
    public String siguienteTurno2(){
        String nombreNoPreferencial = "";
        try{
            nombreNoPreferencial = turno++ +". "+ colaSinPrioridad.peek().toString();
        }catch(Exception e ){
            
        }
        return nombreNoPreferencial;
    }
    
   public void consultarTurno(){
       int contador = -1;
      String id = JOptionPane.showInputDialog(null, "Ingrese su id");
       for (Cliente colaDePrioridade : colaDePrioridades) {
           contador++;
           if(colaDePrioridade.getId().equalsIgnoreCase(id)){
               JOptionPane.showMessageDialog(null, "Faltan "+contador+" turnos");
               return;
           }
       }
       contador = -1;
       for (String colaDePrioridade : colaSinPrioridad) {
           contador++;
           if(colaDePrioridade.toString().equalsIgnoreCase(id)){
               JOptionPane.showMessageDialog(null, "Faltan "+contador+" turnos");
               return;
           }
       }
       JOptionPane.showMessageDialog(null, "IDENTIFICATE EN ESTA MONDA");  
   }
   
   public String[] consultarCola(){
       String[] array = new String[2];
       String mensaje= "";
       for (Cliente colaDePrioridade : colaDePrioridades) {
           mensaje += colaDePrioridade.toString();
       }
       array[0] = mensaje;
       mensaje= "";
       for (String colaDePrioridade : colaSinPrioridad) {
           mensaje += colaDePrioridade.toString();
       }
       array[1] = mensaje;
       return array;
   }
}
