$("#register-button").on("click", e =>{

    //regData ist eine Instanz eines Registration Objekts, siehe model Registration.java
    const regData = {
        //"anrede": $("#anrede-registrierung").val(),
        "vorname": $("#vorname-registrierung").val(),
        "nachname": $("#nachname-registrierung").val(),
        "eMail": $("#email-registrierung").val(),
        "plz": $("#plz-registrierung").val(),
        "ort": $("#ort-registrierung").val(),
        "strasse": $("#strasse-registrierung").val(),
        "hausnummer": $("#hausnummer-registrierung").val()
    }

    $.ajax({
        url: "http://localhost:8080/registration/register",
        type: "POST",
        cors: true,
        contentType: "application/json",
        data: JSON.stringify(regData),
        success: console.log,
        error: console.error
    });
});