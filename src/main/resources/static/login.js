function login(){
    const bruker = {
        epost : $("#epost").val(),
        passord : $("#passord").val()
    };
    if(validerOK()){
        const url = "/login";
        $.get( url, bruker, function(innlogget) {
            if(innlogget){
                window.location.href = 'index.html';
            }
            else{
                $("#loggetInn").html("Feil e-post eller passord");
            }
        })
            .fail(function(jqXHR) {
                //const json = $.parseJSON(jqXHR.responseText);
                $("#feil").html(jqXHR.message);
            });
    }
}