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
    boolean[] habilitar = {false,false};
    int turno = 0;
    int turnoNoPreferencial = 0;
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
    
    public boolean[] solicitarTurno(){
        
        String id = JOptionPane.showInputDialog("Ingrese el numero de documento");
        if(verificarString(id)==false){
            JOptionPane.showMessageDialog(
                null,
                "Id con menos de 8 dígitos o con caracteres diferentes a números.",
                "Advertencia",
                JOptionPane.ERROR_MESSAGE
            );
            return habilitar;
        }
        String clientes[][] = retornarMatriz();
        for (Cliente Cliente : colaDePrioridades) {
            if(id.equalsIgnoreCase(Cliente.getId())){
                JOptionPane.showMessageDialog(
                    null,
                    "El Id ingresado ya cuenta con un turno en la cola.",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE
                );
                return habilitar;
            }
        }
        for (String Cliente : colaSinPrioridad) {
            if(id.equalsIgnoreCase(Cliente)){
                JOptionPane.showMessageDialog(
                    null,
                    "El Id ingresado ya cuenta con un turno en la cola.",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE
                );
                return habilitar;
            }
        }
        for (int i = 0; i < 10; i++) {
            if(clientes[i][0].equalsIgnoreCase(id)){
                Cliente cliente = new Cliente(clientes[i][0],clientes[i][1],clientes[i][2],clientes[i][3],clientes[i][4],clientes[i][5]);
                colaDePrioridades.add(cliente);
                JOptionPane.showMessageDialog(null, "Se ha añadido a la cola preferencial");
                habilitar[0]=true;
                return habilitar;
            }
        }
        colaSinPrioridad.add(id);
        JOptionPane.showMessageDialog(null, "Se ha añadido a la cola no preferencial");
        habilitar[1]=true;
        return habilitar;
    }
    
    public String turno(){
        String nombrePreferencial = "";
        try{
            turno++;
            nombrePreferencial = turno +". "+ colaDePrioridades.poll().getNombre();
            JOptionPane.showMessageDialog(
                null,
                "Atendiendo al cliente",
                "En proceso...",
                JOptionPane.INFORMATION_MESSAGE
            );
        }catch(Exception e ){
            turno = -1;
        }
        
        
        return nombrePreferencial;
    }
    
    public String atenderNoPreferencial(){
        String nombreNoPreferencial = "";
        
        try{
            turnoNoPreferencial++;
            nombreNoPreferencial = turnoNoPreferencial +". "+ colaSinPrioridad.poll();
            JOptionPane.showMessageDialog(
                null,
                "Atendiendo al cliente",
                "En proceso...",
                JOptionPane.INFORMATION_MESSAGE
            );
        }catch(Exception e ){
            turnoNoPreferencial = -1;
        }
        
        
        return nombreNoPreferencial;
    }
    
    public String siguienteTurno1(){
        String nombrePreferencial = "";
        try{
           nombrePreferencial = (turno+1) +". "+ colaDePrioridades.peek().getNombre();
        }catch(Exception e ){
        }
        return nombrePreferencial;
    }
    
    public String siguienteTurno2(){
        String nombreNoPreferencial = "";
        try{
            nombreNoPreferencial = (turnoNoPreferencial+1) +". "+ colaSinPrioridad.peek().toString();
        }catch(Exception e ){
            
        }
        return nombreNoPreferencial;
    }
    
    
     public static boolean verificarString(String cadena) {
        int contadorDigitos = 0;

        for (int i = 0; i < cadena.length(); i++) {
            char c = cadena.charAt(i);
            
            if (Character.isDigit(c)) {
                contadorDigitos++;
            } else {
                return false;
            }
        }

        return contadorDigitos >= 8;
    }
    
   public void consultarTurno(){
       int contador = -1;
      String id = JOptionPane.showInputDialog(null, "Ingrese su id");
      if(verificarString(id)==false){
            JOptionPane.showMessageDialog(
                null,
                "Id con menos de 8 dígitos o con caracteres diferentes a números.",
                "Advertencia",
                JOptionPane.ERROR_MESSAGE
            );
          return;
      }
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
       JOptionPane.showMessageDialog(
            null,
            "Id sin ningún turno asignado.",
            "Advertencia",
            JOptionPane.WARNING_MESSAGE
        );
   }
   
   public String[] consultarCola(){
       String[] array = new String[2];
       String mensaje= "";
       for (Cliente colaDePrioridade : colaDePrioridades) {
           mensaje += colaDePrioridade.getId()+ " "+colaDePrioridade.getNombre() + "\n";
       }
       array[0] = mensaje;
       mensaje= "";
       for (String colaDePrioridade : colaSinPrioridad) {
           mensaje += colaDePrioridade + "\n";
       }
       array[1] = mensaje;
       return array;
   }
}
