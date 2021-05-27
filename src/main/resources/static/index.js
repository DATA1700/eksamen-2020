function regMelding() {
    const melding = {
        fornavn : $("#fornavn").val(),
        etternavn : $("#etternavn").val(),
        klubb: $("#klubb").val(),
        epost : $("#e-post").val(),
        passord : $("#passord").val()
    };
    $.post("/lagre", melding, function(){
        $("#feil").html("Utøver lagret!");
    })
        .fail(function(jqXHR) {
            const json = $.parseJSON(jqXHR.responseText);
            $("#feil").html(json.message);
        });

}

