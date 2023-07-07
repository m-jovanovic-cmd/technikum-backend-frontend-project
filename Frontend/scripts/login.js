$("#loginButton").on("click", _e => {
    $.post({
        url: "http://localhost:8080/login",
        contentType: "application/json",
        data: JSON.stringify({
            username: $("#usernameInput").val(),
            password: $("#passwordInput").val()
        }),
        success: data => sessionStorage.setItem("token", data),
        error: console.error,
    });
});