/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bancomat.springmvc.dao;

import com.bancomat.springmvc.entity.Movimenti;
import com.bancomat.springmvc.entity.Sedi;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class MovimentiDao {

    private static Session session;

    public static void insertMovimento(int id, String causale, double importo) {
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction tsc = session.beginTransaction();

            Movimenti movimento = new Movimenti();

            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Calendar cal = Calendar.getInstance();
            String data = dateFormat.format(cal.getTime());

            movimento.setData(dateFormat.parse(data));
            movimento.setCausale(causale);
            movimento.setIdUtente(id);
            movimento.setImporto(importo);
            session.save(movimento);
            tsc.commit();
            
        } catch (ParseException ex) {
            Logger.getLogger(MovimentiDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static ArrayList<Movimenti> getMovimenti(int id) {
        ArrayList<Movimenti> movimenti = new ArrayList<Movimenti>();
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction tsc = session.beginTransaction();
        String select = "from Movimenti m where idUtente = :id order by m.data desc ";

        Query qry = session.createQuery(select);
        qry.setInteger("id", id);
        qry.setMaxResults(10);
        movimenti = (ArrayList<Movimenti>) qry.list();
        
        return movimenti;
    }
}
