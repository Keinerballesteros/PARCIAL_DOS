/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.banco;

public class Cliente {
    private  String id;
    private  String tipo;
    private  String nombre;
    private  String correo;
    private  String telefono;
    private  double saldo;
    
    public Cliente(String id, String tipo, String nombre, String correo, String telefono, String saldo) {
        this.id = id;
        this.tipo = tipo;
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.saldo = Double.parseDouble(saldo);
    }
   public Cliente(String[] array){
       this.id = array[0];
        this.tipo = array[1];
        this.nombre = array[2];
        this.correo = array[3];
        this.telefono = array[4];
        this.saldo = Double.parseDouble(array[5]);
   }
    public String getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return "Cliente{" + "id=" + id + ", tipo=" + tipo + ", nombre=" + nombre + ", correo=" + correo + ", telefono=" + telefono + ", saldo=" + saldo + '}';
    }
    
    
}
