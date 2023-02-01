$("#register-button").on("click", e => {


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










/* 
 *   Wenn Button geklickt wird (siehe Zeile 1) dann werden alle Dinge die in der runden Klammer von on sind :)
 *   const (wie final in Java)
 *   # > ID wie in CSS, . würde es für alle ID's ausführen
 *   .val() = value The . val() method is primarily used to get the values of form elements such as input , select and textarea
 *   ajax: wir geben URL, auf die es gemappt sind, type POST (gibt verschiedene request typen, get post, usw)
 *   contenType: was für eine Art Datei er erwarten kann (bei uns JSON), dass haben wir oben definiert bei const regData
 *   data ( wo ist data die zu erwarten ist), wir übergenen stringify ist eine Methode, de redData(String JSON) übergeben wird
 *   success: werfen in der console im browser log aus
 *   error: werfen in der console im browser error aus

 *  (.) zusammengefasst: wir haben dieses objekt generiert (regData), wir haben es als JSON format geschrieben,
    dann in ein echtes JSON objekt umgewandetl(in zeile data)
    und dann haben wir das ganze Objekt (JASON) auf die URl geschickt, als request-typ post
    landet beim controller (hier bei registration controller, kommt auf das mapping drauf an)

    (.) LG Jesstify, Mladen ist nicht deppert
*/
