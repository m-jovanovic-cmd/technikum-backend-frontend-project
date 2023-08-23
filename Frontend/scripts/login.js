$("#loginButton").on("click", _e => {
    $.post({
        url: "http://localhost:8080/login",
        contentType: "application/json",
        data: JSON.stringify({
            username: $("#usernameInput").val(),
            password: $("#passwordInput").val()
        }),
        success: function(data) {
            sessionStorage.setItem("token", data);
            location.reload();
        },
        error: function(error) {
            console.error(error);
        }
    });
});