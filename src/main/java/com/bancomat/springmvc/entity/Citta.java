package com.bancomat.springmvc.entity;
// Generated 7-nov-2016 12.41.09 by Hibernate Tools 4.3.1

/**
 * Citta generated by hbm2java
 */
public class Citta  implements java.io.Serializable {


     private String idCitta;
     private String nome;

    public Citta() {
    }

    public Citta(String idCitta, String nome) {
       this.idCitta = idCitta;
       this.nome = nome;
    }
   
    public String getIdCitta() {
        return this.idCitta;
    }
    
    public void setIdCitta(String idCitta) {
        this.idCitta = idCitta;
    }
    public String getNome() {
        return this.nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

}


