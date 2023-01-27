$("register-button").on('click', function(e){
    e.preventDefault();

    $.ajax({
        type: "POST",
        url: "localhost:8080/user/create",
        data: $('#email-registrierung').val(),
        dataType: "json",
        success: function(data) {
            alert("data");
        },
        error: function(data) {
            alert("error");
        }
    })
})