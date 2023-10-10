function sendRequest() {
    var payloadData = parseJwt(token)
    var userId = payloadData.id

    $.ajax({
        url: `http://localhost:8080/api/carts/get${userId}`,
        type: "GET",
        cors: true,
        contentType: "application/json",
        success: (response) => {
            console.log(response)
            //cartID = cart.id

        },
        error: function (xhr, status, error) {
            console.log("Status: " + status);
            console.log("Error: " + error);
            console.log(xhr.responseText);
        }
    });
    //wenn cart with user existiert dann update die kart mit put
    //daran noch arbeiten, dass sich menge und amount vergrößern
    if (response == 200) {
        $.ajax({
            url: `http://localhost:8080/api/carts/update${userId}`,
            type: "PUT",
            cors: true,
            contentType: "application/json",
            success: (response) => {
                // After successful deletion, reload the list of users
                console.log("Cart erfolgreich updated:" + response)
            },
            error: function (xhr, status, error) {
                console.log("Status: " + status);
                console.log("Error: " + error);
                console.log(xhr.responseText);
            }
        });

        //sonst kreiere eine neue Cart mit Post
    } else {

        //beim erstellen ist der amount zuerst immer 1, amount gehört sowieso in zwischentabelle
        var amount = 1
        var user_id = userId

        //get product id from url http://127.0.0.1:5501/Frontend/detailseite_produkte.html?id=2
        var currentURL = window.location.href;
        console.log(currentURL);
        var lastChar = currentURL.charAt(inputString.length - 1);
        var product_id = parseInt(lastChar);

        const newCart = {
            "total": "",
            "amount": amount,
            "orderstatus": "",
            "user_id": user_id,
            "product_id": product_id,

        }
        $.ajax({
            url: `http://localhost:8080/api/cart`,
            type: "POST",
            cors: true,
            contentType: "application/json",
            data: JSON.stringify(newCart),
            success: (success) => {
                console.log("Cart erfolgreich angelegt:" + success)
            },
            error: function (xhr, status, error) {
                console.log("Status: " + status);
                console.log("Error: " + error);
                console.log(xhr.responseText);
            }
        });

    }
};