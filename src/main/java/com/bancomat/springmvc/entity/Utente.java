package com.bancomat.springmvc.entity;
// Generated 7-nov-2016 12.41.09 by Hibernate Tools 4.3.1

/**
 * Utente generated by hbm2java
 */
public class Utente  implements java.io.Serializable {


     private int idUtente;
     private String nome;
     private String cognome;
     private int pin;
     private double saldo;

    public Utente() {
    }

    public Utente(int idUtente, String nome, String cognome, int pin, double saldo) {
       this.idUtente = idUtente;
       this.nome = nome;
       this.cognome = cognome;
       this.pin = pin;
       this.saldo = saldo;
    }
   
    public int getIdUtente() {
        return this.idUtente;
    }
    
    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }
    public String getNome() {
        return this.nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCognome() {
        return this.cognome;
    }
    
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
    public int getPin() {
        return this.pin;
    }
    
    public void setPin(int pin) {
        this.pin = pin;
    }
    public double getSaldo() {
        return this.saldo;
    }
    
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }




}

