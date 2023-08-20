$("#loginModalContainer").load("/Frontend/public/components/loginModal/loginModal.html");

$(document).on("submit", "#loginForm", function(event) {
    event.preventDefault();
    login();
});

function login() {
    const username = document.getElementById("usernameLogin").value;
    const password = document.getElementById("passwordLogin").value;
    console.log(username, password);
}