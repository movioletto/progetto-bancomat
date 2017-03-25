package com.bancomat.springmvc.controller;

import com.bancomat.springmvc.dao.SedeDao;
import com.bancomat.springmvc.dao.UtenteDao;
import com.bancomat.springmvc.entity.Sedi;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bancomat.springmvc.entity.Utente;
import java.util.ArrayList;
import java.util.Iterator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

    /*
	 * This method will serve as default GET handler.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(HttpServletRequest request, ModelMap model) {
        if (request.getSession().getAttribute("idUtente") != null) {
            request.getSession().removeAttribute("idUtente");
        }
        return "index";
    }

    @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
//    @ResponseBody
    public String login(HttpServletRequest request, ModelMap model) {
        String sid = request.getParameter("idUtente");
        String spin = request.getParameter("pin");

        if (!this.isInteger(sid) || !this.isInteger(spin)) {
            model.addAttribute("error", "1");
            return "redirect:/";
        }

        int id = Integer.parseInt(sid);
        int pin = Integer.parseInt(spin);

        Utente utente = UtenteDao.getUtente(id, pin);

        if (utente != null) {
            HttpSession session = request.getSession();
            session.setAttribute("idUtente", id);
            return "redirect:dashboard";
        }

        model.addAttribute("error", "1");
        return "redirect:/";
    }

    @RequestMapping(value = "/getSedi", method = RequestMethod.POST)
    @ResponseBody
    public String getSediGet(ModelMap model) {
        
        ArrayList<Sedi> sedi = new ArrayList<Sedi>();
        sedi = (ArrayList<Sedi>) SedeDao.getSedi();
        Iterator itr = sedi.iterator();
        String s= "";
        while (itr.hasNext())
        {
            Object[] obj = (Object[]) itr.next();
            s+="<option>"+ String.valueOf(obj[2])+ " - " +String.valueOf(obj[0])+" ["+String.valueOf(obj[1])+"]" +"</option>";
        }
        return s;
    }

    public boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        } catch (NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }
}
