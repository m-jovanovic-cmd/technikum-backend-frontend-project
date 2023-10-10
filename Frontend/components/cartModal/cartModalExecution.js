
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
function createCart(newCart) {
    $.ajax({
        url: "http://localhost:8080/api/carts",
        type: "POST",
        cors: true,
        contentType: "application/json",
        data: JSON.stringify(newCart),
        success: (response) => {
            // Display the created user or perform other actions here
            console.log("Cart created:", response);
            window.alert("Cart created successfully!");

        },
        error: (error) => {
            console.log(newCart)
            // Handle errors here if needed
            console.error("Error:", error);
        }
    });
}
//Check if cart already exists then update, otherwise create new cart
//When delete the cart?
function displayAllProductsInCart(products, cart) {
    const tableBody = $("#table tbody");
    for (let i = 0; i < products.length; i++) {
        const cartDisplay = createProductDisplayForCart(product[i], cart);
        tableBody.append(cartDisplay);
    };
};

function createProductDisplayForCart(product, cart) {
    const content = $(`
        <tr class="cart-row">
            <td class="w-25">
                <img src="${product.imageUrl}" class="img-fluid img-thumbnail"
                    alt="Sheep">
            </td>
            <td>${product.name}</td>
            <td class="cart-price cart-column">${product.price}€</td>
            <td class="qty">
                <input type="number" class="form-control input-quantity" id="input1" value=${cart.amount}>
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
};

/*
//////////////////////////
// G E T  R E Q U E S T //
//////////////////////////
//TO-DO
// make button get id from url
//make call to BE to get the product
//display the product on the cartModal wiht help from js (now it is hardcoded)
//make remove button work
//

//make here an array and add the product,to be displayed later
const arraywithcloudobjects = [];
// => get the product into the card
// => save the info and call it from everywhere (in an array?)

//this approach only work on detailseite, how to do it on the produkte side?
function getCurrentId() {
    const url = window.location.href;
    urlObject = new URL(url);
    return urlObject.searchParams.get('id');
};

//activated after pressing the button on the detailspage
$.get({
    id: getCurrentId(),
    url: "http://localhost:8080/api/products/details/" + id,
    cors: true,
    headers: {},
    success: (product) => {
        console.log(product);

        //adding the object to the array
        arraywithcloudobjects.push(product)

        //createProductCartModalDisplay(products) => do this in an extra function 
        //(activated when clicking the right button)
    },
    error: console.error
});

//click event on right button (already exists)
//display there all products with the help in a loop get the objects out
//how to display 2 identical products? => couting how many ids, then do price * amout of ids counted in loop
//pass with a link like in test.js (row.append(`<td><button type="button" id="sendDeleteRequest" class="btn btn-danger mt-3" onclick="sendDeleteRequest(${user.id})">Delete</button></td>`);)


*/ 