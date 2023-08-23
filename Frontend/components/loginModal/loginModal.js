$("#loginModalContainer").load("/Frontend/components/loginModal/loginModal.html");

$(document).on("submit", "#loginForm", function(event) {
    event.preventDefault();
    login();
});

function login() {
    const username = $("#usernameLogin").val();
    const password = $("#passwordLogin").val();

    $.ajax({
        url: "http://localhost:8080/login",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify({
            username: username,
            password: password
        }),
        success: function(data) {
            sessionStorage.setItem("token", data);
            location.reload();
        },
        error: function(error) {
            console.error(error);
        }
    });
}