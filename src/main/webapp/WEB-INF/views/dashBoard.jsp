<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c"  uri="http://java.sun.com/jstl/core"%>
<!DOCTYPE html>
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
        <div class="row">
            <div class="col-md-3">
                <div class="pulsante" id="bLogout">Log Out</div>
                <div class="pulsante" id="bPrelievo">Prelievo</div>
                <div class="pulsante" id="bDeposito">Deposito</div>
            </div>
            <div  class="col-md-6" id="caricamento">
            </div>
            <div  class="col-md-6" id="home">
                Seleziona una voce del Menù.
            </div>
            <div  class="col-md-6" id="prelievo">
                <form method="post" >
                    <div class="row">
                        <div class="col-md-12">
                            <div class="col-md-12" data-toggle="buttons" style="display: none" >
                                <label class="btn btn-default active euro">
                                    <input type="radio" name="danaroButtonPrelievo" value="5" checked autocomplete="off"> 5€
                                </label>
                                <label class="btn btn-default euro">
                                    <input type="radio" name="danaroButtonPrelievo" value="10"  autocomplete="off"> 10€
                                </label>
                                <label class="btn btn-default euro">
                                    <input type="radio" name="danaroButtonPrelievo" value="20"  autocomplete="off"> 20€
                                </label>
                                <label class="btn btn-default euro">
                                    <input type="radio" name="danaroButtonPrelievo" value="50"  autocomplete="off"> 50€
                                </label>
                                <label class="btn btn-default euro">
                                    <input type="radio" name="danaroButtonPrelievo" value="100"  autocomplete="off"> 100€
                                </label>
                                <label class="btn btn-default euro">
                                    <input type="radio" name="danaroButtonPrelievo" value="200"  autocomplete="off"> 200€
                                </label>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <input type="text" placeholder="Scrivi quanto vuoi prelevare" id="danaroPrelievo" class="col-md-10 col-md-offset-1" style="height: 50px; margin-top: 10px;border-radius:10px;">
                    </div>
                    <div class="row">
                        <input type="button" value="Esegui il prelievo" id="prelievoButton" class="col-md-10 col-md-offset-1" style="height: 50px; margin-top: 10px;border-radius:10px;">
                    </div>

                </form>

                <div class="row">
                    <div class="col-md-12">
                        <div style="display: none" id="prelievoM"></div>
                    </div>
                </div>

            </div>
            <div  class="col-md-6" id="deposito">
                <div class="row">
                    <div class="col-md-10 col-md-offset-1">
                        <form>
                            <div class="form-group">
                                <input type="text" class="col-md-12" style="height: 50px; margin-top: 10px;border-radius:10px;" id="danaroDeposito" name="danaroDeposito" placeholder="Quanto vuoi depositare?">
                            </div>
                            <br>
                            <button type="button" class="btn btn-default col-md-12" style="height: 50px; margin-top: 10px;border-radius:10px;" id="deposita">Deposita</button>
                        </form>
                    </div>
                </div>


                <div class="row">
                    <div class="col-md-12">
                        <div style="display: none" id="depositoM"></div>
                    </div>
                </div>
            </div>
            <div  class="col-md-6" id="bonifico">
                <div class="col-md-10 col-md-offset-1">
                    <form>
                        <div class="form-group">
                            <input type="text" class="col-md-12" style="height: 50px; margin-top: 10px;border-radius:10px;" id="danaroBonifico" name="danaroBonifico" placeholder="Inserisci l'importo">
                        </div>
                        <div class="form-group">
                            <input type="text" class="col-md-12" style="height: 50px; margin-top: 10px;border-radius:10px;" id="causaleBonifico" name="causaleDeposito" placeholder="Causale">
                        </div>
                        <div class="form-group">
                            <select class="col-md-12" style="height: 50px; margin-top: 10px;border-radius:10px;" id="utentiBonifico">
                                <option value="-1">Scegli l'utente</option>
                                <option value="0">Altro</option>
                            </select>
                        </div>
                        <div class="form-group" style="display: none" id="iban">
                            <input type="text" class="col-md-12" style="height: 50px; margin-top: 10px;border-radius:10px;" placeholder="Scrivi l'IBAN">
                        </div>
                        <br>
                        <button type="button" class="btn btn-default col-md-12" style="height: 50px; margin-top: 10px;border-radius:10px;" id="bonifica">Effettua bonifico</button>
                    </form>
                </div>


                <div class="row">
                    <div class="col-md-12">
                        <div style="display: none" id="bonificoM"></div>
                    </div>
                </div>
            </div>
            <div  class="col-md-6" id="saldo">
                Il tuo saldo è di
            </div>
            <div  class="col-md-6" id="movimenti">
                <table class="col-md-10 col-md-offset-1">
                    <thead>
                    <th>Data</th>
                    <th>Causale</th>
                    <th>Importo</th>
                    </thead>
                    <tbody id="mov">
                    </tbody>
                </table>
            </div>
            <div class="col-md-3">
                <div class="pulsante2" id="bBonifico">Bonifico</div>
                <div class="pulsante2" id="bSaldo">Saldo</div>
                <div class="pulsante2" id="bMovimenti">Movimenti</div>
            </div>
        </div>

    </body>
</html>
