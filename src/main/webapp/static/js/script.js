$(document).ready(function ()
{
    var doppioni=false;
    
//    FUNCTION 
    var nascondiTuttoTranne = function (nome)
    {
        $("#caricamento").css("display", "none");
        $("#home").css("display", "none");
        $("#bonifico").css("display", "none");
        $("#prelievo").css("display", "none");
        $("#saldo").css("display", "none");
        $("#movimenti").css("display", "none");
        $("#deposito").css("display", "none");

        $(nome).css("display", "block");
    };

    var getUrlParameter = function getUrlParameter(sParam) {
        var sPageURL = decodeURIComponent(window.location.search.substring(1)),
                sURLVariables = sPageURL.split('&'),
                sParameterName,
                i;

        for (i = 0; i < sURLVariables.length; i++) {
            sParameterName = sURLVariables[i].split('=');

            if (sParameterName[0] === sParam) {
                return sParameterName[1] === undefined ? true : sParameterName[1];
            }
        }
        return false;
    };


//    LOGOUT
    $("#bLogout").click(function ()
    {
        window.location.replace("./");
    });


//    BONIFICO
    $("#bBonifico").click(function ()
    {
        nascondiTuttoTranne("#bonifico");
        
        if(!doppioni)
            $.ajax(
                    {
                        method: "POST",
                        url: "getUtenti"
                    })
                    .done(function (msg)
                    {
                        $("#utentiBonifico").append(msg);
                        doppioni= true;
                    });
    });
    
    

    $("#utentiBonifico").change(function()
    {
        if($("#utentiBonifico").val()==0)
            $("#iban").css("display", "block");
        else
            $("#iban").css("display", "none");
    });

    $("#bonifica").click(function ()
    {

        $.ajax(
                {
                    method: "POST",
                    url: "getBonifico",
                    data:
                            {
                                danaroBonifico: $("#danaroBonifico").val(),
                                causaleBonifico: $("#causaleBonifico").val(),
                                utentiBonifico: $("#utentiBonifico").val(),
                            }
                })
                .done(function (msg)
                {
                    $("#danaroBonifico").val("");
                    $("#causaleBonifico").val("");
                    $("#utentiBonifico").val("");
                    $("#bonificoM").html(msg).css("display", "block");
                });
    });


//    PRELIEVO
    $("#bPrelievo").click(function ()
    {
        nascondiTuttoTranne("#prelievo");

    });

    $("#prelievoButton").click(function ()
    {

//        var importo= 0;
//        if ($("#danaroPrelievo").val() != "")
//            importo= $("#danaroPrelievo").val();
//        else
//            importo= $("#danaroButtonPrelievo").val();
//        alert(importo)
        $.ajax(
                {
                    method: "POST",
                    url: "getPrelievo",
                    data:
                            {
                                danaroPrelievo: $("#danaroPrelievo").val()
                            }
                })
                .done(function (msg)
                {
                    $("#danaroBonifico").val("");
                    $("#prelievoM").html(msg).css("display", "block");
                });
    });



//    SALDO

    $("#bSaldo").click(function ()
    {
        nascondiTuttoTranne("#saldo");
        $.ajax(
                {
                    method: "POST",
                    url: "getSaldo"
                })
                .done(function (msg)
                {
                    $("#saldo").html(msg);
                });
    });



//    MOVIMENTI

    $("#bMovimenti").click(function ()
    {
        nascondiTuttoTranne("#movimenti");

        $.ajax(
                {
                    method: "POST",
                    url: "getMovimenti"
                })
                .done(function (msg)
                {
                    $("#mov").html(msg);
                });

    });


//    DEPOSITO
    
    $("#bDeposito").click(function ()
    {
        nascondiTuttoTranne("#deposito");
        
    });

    $("#deposita").click(function ()
    {

        $.ajax(
                {
                    method: "POST",
                    url: "getDeposito",
                    data:
                            {
                                danaroDeposito: $("#danaroDeposito").val()
                            }
                })
                .done(function (msg)
                {
                    $("#danaroDeposito").val("");
                    $("#depositoM").html(msg).css("display", "block");
                });
    });

//    PRIMO AVVIO
    
    if ($(location).attr('hash') != "")
    {
        nascondiTuttoTranne($(location).attr('hash'));
    } else
    {
        nascondiTuttoTranne("#home");
    }

    if (getUrlParameter("page") != false)
    {
        nascondiTuttoTranne("#" + getUrlParameter("page"));
    }

    $.ajax(
            {
                method: "POST",
                url: "getSedi"
            })
            .done(function (msg)
            {
                $("#sede").append(msg);
            });



});