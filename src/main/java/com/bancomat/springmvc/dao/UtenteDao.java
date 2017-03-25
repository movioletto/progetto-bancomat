/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bancomat.springmvc.dao;

import com.bancomat.springmvc.entity.Utente;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Maria
 */
public class UtenteDao {

    private static Session session;

    public static Utente getUtente(int id, int pin) {
        Utente utente = null;
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction tsc = session.beginTransaction();

        Query qry = session.createQuery("from Utente where idUtente = :id and pin = :pin");
        qry.setInteger("id", id);
        qry.setInteger("pin", pin);
        utente = (Utente) qry.uniqueResult();

        return utente;
    }

    public static ArrayList<Utente> getAllUtenti() {
        ArrayList<Utente> utenti = new ArrayList<Utente>();
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction tsc = session.beginTransaction();

        Query qry = session.createQuery("from Utente");

        utenti = (ArrayList<Utente>) qry.list();

        return utenti;
    }

    public static double getSaldo(int id) {
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction tsc = session.beginTransaction();
        Double saldo;
        String selectAll = "select u.saldo from Utente u where idUtente = :id";

        Query qry = session.createQuery(selectAll);
        qry.setInteger("id", id);

        saldo = (Double) qry.uniqueResult();

        return saldo;
    }

    public static double deposita(int id, double deposito) {

        double saldo = getSaldo(id);
        saldo += deposito;
        updateSaldo(id, saldo);
        MovimentiDao.insertMovimento(id, "deposito", deposito);
        return saldo;
    }

    public static double preleva(int id, double importo) {
        int result = -1;
        double saldo = getSaldo(id);

        if (saldo >= importo) {
            saldo -= importo;
            result = updateSaldo(id, saldo);
            MovimentiDao.insertMovimento(id, "prelievo", -importo);
            return saldo;
        } else {
            return result;
        }
    }

    public static int updateSaldo(int id, double saldo) {
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction tsc = session.beginTransaction();

        String updateQuery = "update Utente u set u.saldo = :saldo where idUtente = :id";
        Query qry = session.createQuery(updateQuery);
        qry.setDouble("saldo", saldo);
        qry.setInteger("id", id);
        int result = qry.executeUpdate();
        session.getTransaction().commit();
        return result;
    }

    public static double effettuaBonifico(int id, int idBeneficiario, double importo, String causale) {
        int result = -1;
        double saldo = getSaldo(id);

        if (saldo >= importo) {
            saldo -= importo;
            result = updateSaldo(id, saldo);
            MovimentiDao.insertMovimento(id, causale, -importo);

            effettuaBonificoByIban(idBeneficiario, importo, causale);

            return saldo;
        } else {
            return result;
        }
    }

    public static void effettuaBonificoByIban(int id, double importo, String causale) {
        if (isUtente(id)) {
            double saldo = getSaldo(id);
            saldo += importo;
            updateSaldo(id, saldo);
            MovimentiDao.insertMovimento(id, causale, importo);
        }
    }

    public static boolean isUtente(int id) {
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction tsc = session.beginTransaction();
        Query qry = session.createQuery("from Utente where idUtente = :id");
        qry.setInteger("id", id);
        if (qry.uniqueResult() != null) {
            return true;
        } else {
            return false;
        }

    }

}
