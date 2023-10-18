const token = sessionStorage.getItem("token");

Veränderung -> Get Request -> zeichnen die Tables neu

getCart();

async function getCart() {
    const token = sessionStorage.getItem("token");
    const userId = await getUserId(token);

    if (userId) {
        try {
            const response = await fetch(`http://localhost:8080/api/positions/${userId}/${productId}`, {
                method: "POST",
                headers: {
                    "Authorization": token,
                    "Content-Type": "application/json"
                }
            });
        if (!response.ok) {
            console.log("Error: " + response.status);
        }
        } catch (error) {
            console.error("Error:", error);
        }
    }
}

async function getUserId(token) {
    try {
        const response = await fetch("http://localhost:8080/api/users/getUserId", {
            method: "GET",
            headers: {
                "Authorization": token,
                "Content-Type": "application/json"
            }
        });

        if (response.ok) {
            const data = await response.json();
            return data;
        } else {
            console.log("Error: " + response.status);
        }
    } catch (error) {
        console.error("Error:", error);
    }
}



const content = $(`
<tr class="cart-row">
    <td class="w-25">
        <img src="${product.imageUrl}" class="img-fluid img-thumbnail"
            alt="Sheep">
    </td>
    <td>${product.name}</td>
    <td class="cart-price cart-column">${product.price}€</td>
    <td class="qty">
        <input type="number" class="form-control input-quantity" id="input1" value=${position.amount}>
    </td>
    <td>
        <button class="btn btn-danger btn-sm remove-item text-white"
            type="button">Entfernen</button>
        <i class="fa fa-times"></i>
    </td>
</tr>
`
);


<h5>Total: <span class="price text-success">0.00€</span></h5>

function ready() {
    //console.log(removeCartItemButtons);
    var quantityInputs = document.getElementsByClassName('form-control input-quantity')
    for (var i = 0; i < quantityInputs.length; i++) {
        var input = quantityInputs[i]
        input.addEventListener('change', quantityChanged)
    }
}




//Check if cart already exists then update, otherwise create new cart
//When delete the cart?
function displayAllProductsInCart(products, cart) {
    const tableBody = $("#table tbody");
    for (let i = 0; i < products.length; i++) {
        const cartDisplay = createProductDisplayForCart(cart);
        tableBody.append(cartDisplay);
    };
};
