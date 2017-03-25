<%@page import="com.bancomat.springmvc.entity.Movimenti"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="com.bancomat.springmvc.entity.Utente"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.bancomat.springmvc.entity.Sedi"%>
<%@page import="com.bancomat.springmvc.dao.UtenteDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="uBean" scope="page" class="com.bancomat.springmvc.dao.UtenteDao"></jsp:useBean>
<jsp:useBean id="sBean" scope="page" class="com.bancomat.springmvc.dao.SedeDao"></jsp:useBean>
<jsp:useBean id="mBean" scope="page" class="com.bancomat.springmvc.dao.MovimentiDao"></jsp:useBean>
    <!DOCTYPE html>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>JSP Page</title>
        </head>
        <body>
            <h1>Hello World!</h1>
            <h2>
            <%
                /*
                Utente utente = new Utente();
                utente = uBean.getUtente(1, 12345);
                out.println(utente.getNome());
                out.println(utente.getCognome());
                out.println(utente.getPin());
                
                out.println(uBean.getSaldo(1));
                 */
            %>
            <br><br>
            <%
                /*
                uBean.deposita(1, 100);
                out.println(uBean.getSaldo(1));
                */
                uBean.effettuaBonifico(1, 2, 100.0, "causale");
                 out.println(uBean.getSaldo(1));
            %>
            <br><br>  
            <% /*
        ArrayList<Sedi> sedi = new ArrayList<Sedi>();
        sedi = (ArrayList<Sedi>)sBean.getSedi();
        out.println(sedi.size());
        Sedi s = new Sedi();
        s = sedi.get(0);
        out.println(s.getIndirizzo());
                 */
                /*
            ArrayList<Sedi> sedi = new ArrayList<Sedi>();
            sedi = (ArrayList<Sedi>) sBean.getSedi();
            Iterator itr = sedi.iterator();
            while (itr.hasNext()) {
                Object[] obj = (Object[]) itr.next();
                //now you have one array of Object for each row
                String client = String.valueOf(obj[1]); // don't know the type of column CLIENT assuming String 
                out.println(client);
            }
                 */
                /*
                ArrayList<Movimenti> movimenti = new ArrayList<Movimenti>();
                movimenti = (ArrayList<Movimenti>) mBean.getMovimenti();
                Iterator itr = movimenti.iterator();
                Movimenti mov;
                while (itr.hasNext()) {
                    mov = (Movimenti) itr.next();
                    //now you have one array of Object for each row
                    //String client = String.valueOf(obj[1]); // don't know the type of column CLIENT assuming String 
                    out.println(mov.getData());
                */
                    %><br><%
                /*}*/
            %>
        </h2>
        
    </body>
</html>
