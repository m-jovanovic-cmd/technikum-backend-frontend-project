$("#register-button").on("click", e => {
    
    const regData = {
        "gender": $("#gender-registration").val(),
        "username": $("#username-registration").val(),
        "password": $("#password-registration").val(),
        "firstName": $("#firstName-registration").val(),
        "lastName": $("#lastName-registration").val(),
        "email": $("#email-registration").val(),
        "postcode": $("#postcode-registration").val(),
        "location": $("#location-registration").val(),
        "street": $("#street-registration").val(),
        "streetnumber": $("#streetNumber-registration").val()
    }

    // TODO: Validierung der Daten im Frontend

    $.ajax({
        url: "http://localhost:8080/api/users",
        type: "POST",
        cors: true,
        contentType: "application/json",
        data: JSON.stringify(regData),
        success: () => {
            location.replace('/Frontend/index.html')
        },
        error: console.error
    });
});