package com.bancomat.springmvc.entity;
// Generated 7-nov-2016 12.41.09 by Hibernate Tools 4.3.1



/**
 * Sedi generated by hbm2java
 */
public class Sedi  implements java.io.Serializable {


     private int idSede;
     private String indirizzo;
     private String telefono;
     private String mail;
     private String idCitta;

    public Sedi() {
    }

    public Sedi(int idSede, String indirizzo, String telefono, String mail, String idCitta) {
       this.idSede = idSede;
       this.indirizzo = indirizzo;
       this.telefono = telefono;
       this.mail = mail;
       this.idCitta = idCitta;
    }
   
    public int getIdSede() {
        return this.idSede;
    }
    
    public void setIdSede(int idSede) {
        this.idSede = idSede;
    }
    public String getIndirizzo() {
        return this.indirizzo;
    }
    
    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }
    public String getTelefono() {
        return this.telefono;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getMail() {
        return this.mail;
    }
    
    public void setMail(String mail) {
        this.mail = mail;
    }
    public String getIdCitta() {
        return this.idCitta;
    }
    
    public void setIdCitta(String idCitta) {
        this.idCitta = idCitta;
    }




}


