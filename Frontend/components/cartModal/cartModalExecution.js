
//get info out of admin
//make cart modal only visible if you logged in
//make buttons add to cart only visible if logged in 

const authToken = sessionStorage.getItem("token");
console.log(authToken)
//tutorial: https://www.youtube.com/watch?v=YeFzkC2awTM&t=23s&ab_channel=WebDevSimplified
if (document.readyState == "loading") {
    document.addEventListener("DOMContentLoaded", ready)
} else {
    ready()
}

function ready() {
    var removeCartItemButtons = document.getElementsByClassName("btn-danger");
    //console.log(removeCartItemButtons);

    for (var i = 0; i < removeCartItemButtons.length; i++) {
        var button = removeCartItemButtons[i];
        button.addEventListener("click", removeCartItem)

    }
    var quantityInputs = document.getElementsByClassName('form-control input-quantity')
    for (var i = 0; i < quantityInputs.length; i++) {
        var input = quantityInputs[i]
        input.addEventListener('change', quantityChanged)
    }
}

function quantityChanged(event) {
    var input = event.target
    if (isNaN(input.value) || input.value <= 0) {
        input.value = 1
    }
    updateCartTotal()
}

function removeCartItem(event) {
    //console.log("clicked");
    var buttonClicked = event.target;
    // Navigate to the parent element (in this case, the <td> element)
    var parentElement = buttonClicked.parentElement;
    // Navigate to the parent row (in this case, the <tr> element)
    var rowElement = parentElement.parentElement;
    // Remove the entire row
    rowElement.remove();
    updateCartTotal()
}

// cart-quantity-input = "form-control input-quantity"
//cart-items = modal-body
//cart-row = cart-row

function updateCartTotal() {
    var cartItemContainer = document.getElementsByClassName('cart-modal-body')[0]
    console.log(cartItemContainer)
    var cartRows = cartItemContainer.getElementsByClassName('cart-row')
    var total = 0
    for (var i = 0; i < cartRows.length; i++) {
        var cartRow = cartRows[i]
        var priceElement = cartRow.getElementsByClassName('cart-price')[0]
        var quantityElement = cartRow.getElementsByClassName('input-quantity')[0]
        var price = parseFloat(priceElement.innerText.replace('€', ''))
        var quantity = quantityElement.value
        console.log(price * quantity)
        total = total + (price * quantity)
    }

    total = Math.round(total * 100) / 100
    //document.getElementsByClassName('cart-total')[0].innerText = '€' + total
    var cartTotalElement = document.getElementById('cart-total');
    cartTotalElement.innerText = '€' + total.toFixed(2);
}
// Call the function initially and whenever the cart changes
updateCartTotal();

/*
Mit Token User extrahieren
mithilfe von Configure Spring Security
*/

function getUserDataFromToken() {
    try {
        // Verify and decode the token
        const decoded = jwt.verify(token, secretKey);

        // Access user information from the decoded token
        const userId = decoded.userId;
        const username = decoded.username;
        // Add more properties as needed

        console.log('User ID:', userId);
        console.log('Username:', username);
    } catch (error) {
        // Handle token verification errors here
        console.error('Token verification failed:', error.message);
    }

}

/*
$.get({
    url: "http://localhost:8080/api/validateToken",
    headers: { "Authorization": authToken },
    data: {},
    success: (data) => {
        console.log(data)
    },
    error: console.error
});
*/


///////////////////////////
// G E T  R E Q U E S T //
/////////////////////////
function getCartTest(userId) {
    $.ajax({
        url: `http://localhost:8080/api/carts`,
        type: "GET",
        cors: true,
        headers: {},
        success: (carts) => {
            console.log(carts)
            if (carts.userId === userId) {
                id == carts.id
                $.ajax({
                    url: `http://localhost:8080/api/carts/get${id}`,
                    cors: true,
                    headers: {},
                    success: (users) => {
                        displayAllProductsInCart(products, cart);
                        console.log(products, cart)
                    },
                    error: console.error
                });
            }

        },
        error: console.error
    });

}
////////////////////
// POST REQEUEST //
///////////////////



//Check if cart already exists then update, otherwise create new cart
//When delete the cart?
function displayAllProductsInCart(products, cart) {
    const tableBody = $("#table tbody");
    for (let i = 0; i < products.length; i++) {
        const cartDisplay = createProductDisplayForCart(cart);
        tableBody.append(cartDisplay);
    };
};
function parseJwt(token) {
    // Step 1: Split the token into its three parts: header, payload, and signature
    var base64Url = token.split('.')[1];

    // Step 2: Replace characters that are not URL-safe
    var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');

    // Step 3: Decode the base64-encoded payload
    var jsonPayload = decodeURIComponent(
        //Base64 is a binary-to-text encoding scheme
        window.atob(base64)  // Step 4: Decode the base64 to binary
            .split('')
            .map(function (c) {
                // Step 5: Convert binary to hexadecimal representation
                return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
            })
            .join('')
    );

    // Step 6: Parse the JSON payload into a JavaScript object
    return JSON.parse(jsonPayload);
}

var payloadData = parseJwt(token);

function getAllPositionsFromOneUser(userId) {
    $.ajax({
        url: `http://localhost:8080/api/positions/${userId}`,
        type: "GET",
        cors: true,
        headers: {},
        success: (positions) => {
            console.log(positions)
            return positions
        },
        error: console.error
    });
}



function createProductDisplayForCart(cart) {
    var currentURL = window.location.href;
    var userId = parseInt(payloadData.id);
    var cartId = cart.id

    var listWithPositions = getAllPositionsFromOneUser(userId)

    console.log(listWithPositions)

    for (position in positions) {
        var product = position.productId

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

        return content;
    }

};

/*
<tr class="cart-row">
                            <td class="w-25">
                                <img src="./images/products/CharmingClaude.jpg" class="img-fluid img-thumbnail"
                                    alt="Sheep">
                            </td>
                            <td>Charming Claude</td>
                            <td class="cart-price cart-column">4.5€</td>
                            <td class="qty">
                                <input type="number" class="form-control input-quantity" id="input1" value="3">
                            </td>
                            <td>
                                <button class="btn btn-danger btn-sm remove-item text-white"
                                    type="button">Entfernen</button>
                                <i class="fa fa-times"></i>
                            </td>
                        </tr>
                        <tr class="cart-row">
                            <td class="w-25">
                                <img src="./images/products/DropletDamian.jpg" class="img-fluid img-thumbnail"
                                    alt="Sheep">
                            </td>
                            <td>Droplet Damian</td>
                            <td class="cart-price cart-column">10.3€</td>
                            <td class="qty">
                                <input type="number" class="form-control input-quantity" id="input2" value="1">
                            </td>
                            <td>
                                <button class="btn btn-danger btn-sm remove-item text-white"
                                    type="button">Entfernen</button>
                                <i class="fa fa-times"></i>
                            </td>
                        </tr>
*/
