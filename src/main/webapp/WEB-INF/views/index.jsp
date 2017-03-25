<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jstl/core"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link rel="stylesheet" type="text/css" href="<c:url value='/static/css/bootstrap.min.css' />">
        <link rel="stylesheet" type="text/css" href="<c:url value='/static/css/style.css' />">

        <script type="text/javascript" src="<c:url value='/static/js/jquery-3.1.1.min.js' />"></script>
        <script type="text/javascript" src="<c:url value='/static/js/bootstrap.min.js' />"></script>
        <script type="text/javascript" src="<c:url value='/static/js/script.js' />"></script>

        <title>JSP Page</title>
    </head>
    <body>
        <div class="row" style="margin-top: 40px">

            <div class="col-md-4 col-md-offset-4" style="text-align: center">
                <img src="<c:url value='/static/img/logo.png' />">
            </div>
        </div>
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                
                <%--
                    <form:form action="<c:url value="/login" />" method="post" modelAttribute="utente">

                    <select class="col-md-12" style="height: 50px; margin-top: 10px;border-radius:10px;" id="sede">
                        <option>Scegli la sede</option>
                        <option>Via Roma</option>
                        <option>Via Milano</option>
                    </select>
                    
                    <form:errors path="*" />
                    <p>
                        <label>Nome:</label>
                        
                        <form:input path="name"/>
                    </p>

                    <p>
                        <label>Cognome:</label>
                        <form:input path="surname" />
                        <form:errors path="surname" />
                    </p>

                    <p>
                        <input type="submit"/>
                    </p>

                </form:form>
                --%>


                <form action="login" method="post">
                    <%
                        if(request.getParameter("error")!=null)
                        {
                            
                    %>
                    <div style="background-color: red;font-size: 30px;text-align: center;">ERRORE</div>
                    <%
                        }
                    %>
                    <div class="form-group">
                        <select class="col-md-12" style="height: 50px; margin-top: 10px;border-radius:10px;" id="sede">
                            <option>Scegli la sede</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <input type="text" placeholder="ID Utente" class="col-md-12" style="height: 50px; margin-top: 10px;border-radius:10px;" id="idUtente" name="idUtente">
                    </div>
                    <br><br>
                    <div class="form-group">
                        <input type="password" class="col-md-12"  style="height: 50px; margin-top: 10px;border-radius:10px;"  id="pin" placeholder="PIN" name="pin">
                    </div>
                    <br>
                    <button type="submit" class="btn btn-default col-md-12" style="height: 50px; margin-top: 10px;border-radius:10px;">Submit</button>
                </form>
            </div>
        </div>
    </body>
</html>
