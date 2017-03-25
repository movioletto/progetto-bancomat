/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bancomat.springmvc.dao;

import com.bancomat.springmvc.entity.Sedi;
import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Maria
 */
public class SedeDao {
    
    private static Session session;


    public static ArrayList<Sedi> getSedi() {
        ArrayList<Sedi> sedi = new ArrayList<Sedi>();
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction tsc = session.beginTransaction();
        String selectAll = "select c.nome, s.idCitta, s.indirizzo from Sedi s, Citta c where c.idCitta=s.idCitta";

        Query qry = session.createQuery(selectAll);
        sedi = (ArrayList<Sedi>) qry.list();
        
        return sedi;
    }
}
