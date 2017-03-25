package com.bancomat.springmvc.controller;

import com.bancomat.springmvc.dao.MovimentiDao;
import com.bancomat.springmvc.dao.SedeDao;
import com.bancomat.springmvc.dao.UtenteDao;
import com.bancomat.springmvc.entity.Movimenti;
import com.bancomat.springmvc.entity.Sedi;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bancomat.springmvc.entity.Utente;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DashBoardController {

    /*
	 * This method will serve as default GET handler.
     */
    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String login(HttpServletRequest request, ModelMap model) {
        if (request.getSession().getAttribute("idUtente") == null) {
            model.addAttribute("error", "2");
            return "redirect:/";
        }

        return "dashBoard";
    }

    @RequestMapping(value = "/getMovimenti", method = {RequestMethod.POST})
    @ResponseBody
    public String getMovimenti(HttpServletRequest request, ModelMap model) {

        int id = (Integer) request.getSession().getAttribute("idUtente");

//        String date[] = {"2008/11/", "2008/11/", "2008/11/", "2008/11/", "2008/11/", "2008/11/", "2008/11/", "2008/11/", "2008/11/", "2008/11/"};
//        String causale[] = {"deposito", "macchina", "benzina", "eredità", "stipendio", "steam", "amazon", "reso", "capodanno", "black jack"};
//        double importi[] = {12, -1, -123, 435, 345, -234, -123, 654, -678, -867};
//        String s = "";
//        for (int i = 0; i < date.length; i++) {
//            if (importi[i] < 0) {
//                s += "<tr class=\"meno\">";
//            } else {
//                s += "<tr class=\"piu\">";
//            }
//            s += "<td>" + date[i] + i + "</td>"
//                    + "<td>" + causale[i] + "</td>"
//                    + "<td>" + importi[i] + "</td>"
//                    + "</tr>";
//        }
        ArrayList<Movimenti> movimenti = new ArrayList<Movimenti>();
        movimenti = (ArrayList<Movimenti>) MovimentiDao.getMovimenti(id);
        Iterator itr = movimenti.iterator();
        Movimenti mov;
        String s = "";
        while (itr.hasNext()) {
            mov = (Movimenti) itr.next();

            if (mov.getImporto() < 0) {
                s += "<tr class=\"meno\">";
            } else {
                s += "<tr class=\"piu\">";
            }
            s += "<td>" + mov.getData() + "</td>"
                    + "<td>" + mov.getCausale() + "</td>"
                    + "<td>" + mov.getImporto() + "</td>"
                    + "</tr>";

        }

        return s;
    }

    @RequestMapping(value = "/getUtenti", method = {RequestMethod.POST})
    @ResponseBody
    public String getUtenti(HttpServletRequest request, ModelMap model) {
        int id = (Integer) request.getSession().getAttribute("idUtente");
        ArrayList<Utente> utente = new ArrayList<Utente>();
        utente = (ArrayList<Utente>) UtenteDao.getAllUtenti();
        Iterator itr = utente.iterator();
        String s = "";
        while (itr.hasNext()) {
            Utente obj = (Utente) itr.next();
            if (obj.getIdUtente() != id) {
                s += "<option value=\"" + obj.getIdUtente() + "\">" + obj.getNome() + " " + obj.getCognome() + "</option>";
            }

        }
        return s;
    }

    @RequestMapping(value = "/getBonifico", method = {RequestMethod.POST})
    @ResponseBody
    public String getBonifico(HttpServletRequest request, ModelMap model) {

        int id = (Integer) request.getSession().getAttribute("idUtente");

        if (request.getParameter("danaroBonifico") == null || request.getParameter("causaleBonifico") == null || request.getParameter("utentiBonifico") == null) {
            return "<div style=\"background-color: red\">ERRORE: CAMPI OBBLIGATORI NON COMPILATI</div>";
        }

        String sdanaro = request.getParameter("danaroBonifico");
        String causale = request.getParameter("causaleBonifico");
        String sutente = request.getParameter("utentiBonifico");

        if (!this.isDouble(sdanaro) || !this.isInteger(sutente)) {
            return "<div style=\"background-color: red\">ERRORE: INSERIRE IMPORTO IN CIFRE</div>";
        }

        double danaro = Double.parseDouble(sdanaro);
        int utente = Integer.parseInt(sutente);
        double saldo= UtenteDao.effettuaBonifico(id, utente, danaro, causale);
        if(saldo>0)
            return "<div style=\"background-color: green\">Il tuo saldo è " + saldo + " &euro;</div>";
        return "<div style=\"background-color: red\">ERRORE: SOLDI INSUFFICIENTI</div>";
    }

    @RequestMapping(value = "/getSaldo", method = {RequestMethod.POST})
    @ResponseBody
    public String getSaldo(HttpServletRequest request, ModelMap model) {
        int id = (Integer) request.getSession().getAttribute("idUtente");

        return "Il tuo saldo è " + UtenteDao.getSaldo(id) + " &euro;";
    }

    @RequestMapping(value = "/getDeposito", method = {RequestMethod.POST})
    @ResponseBody
    public String getDeposito(HttpServletRequest request, ModelMap model) {
        int id = (Integer) request.getSession().getAttribute("idUtente");

        if (request.getParameter("danaroDeposito") == null) {
            return "<div style=\"background-color: red\">ERRORE: CAMPI OBBLIGATORI NON COMPILATI</div>";
        }

        String sdeposito = request.getParameter("danaroDeposito");

        if (!this.isDouble(sdeposito)) {
            return "<div style=\"background-color: red\">ERRORE: INSERIRE IMPORTO IN CIFRE</div>";
        }

        double deposito = Double.parseDouble(sdeposito);

        return "<div style=\"background-color: green\">Il tuo saldo è " + UtenteDao.deposita(id, deposito) + " &euro;</div>";
    }

    public boolean isDouble(String s) {
        try {
            Double.parseDouble(s);
        } catch (NumberFormatException e) {
            return false;
        } catch (NullPointerException e) {
            return false;
        }
        return true;
    }

    public boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        } catch (NullPointerException e) {
            return false;
        }
        return true;
    }

    @RequestMapping(value = "/getPrelievo", method = {RequestMethod.POST})
    @ResponseBody
    public String getPrelievo(HttpServletRequest request, ModelMap model) {
        int id = (Integer) request.getSession().getAttribute("idUtente");

        String seuro = request.getParameter("danaroPrelievo");

        if (!this.isDouble(seuro)) {
            return "<div style=\"background-color: red\">ERRORE: INSERIRE IMPORTO IN CIFRE</div>";
        }

        double euro = Double.parseDouble(seuro);

        double saldo= UtenteDao.preleva(id, euro);
        if(saldo>0)
            return "<div style=\"background-color: green\">Il tuo saldo è " + saldo + " &euro;</div>";
        return "<div style=\"background-color: red\">ERRORE: SOLDI INSUFFICIENTI</div>";
    }
}
