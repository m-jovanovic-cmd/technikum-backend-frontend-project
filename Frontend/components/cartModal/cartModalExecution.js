function getCart() {
    $.ajax({
        url: "http://localhost:8080/api/carts/getCart",
        type: "GET",
        dataType: "json",
        headers: { "Authorization": sessionStorage.getItem('token')},
        contentType: "application/json",
        data: {},
        success: (data) => {
            let cart = data;
            displayCart(cart);
        },
        error: error => {
            console.log(error);
        }
    })
}

function displayCart(cart) {
    let productListContent = ``;
    let cartItemsContainer = $("#cartBodyContainer");
    let cartTotalContainer = $("#cartTotalContainer");
    cartTotalContainer.empty();
    cartItemsContainer.empty();
    cart.productList.forEach(product => {
        productListContent +=
            `<tr class="cart-row">
                <td>${product.name}</td>
                <td class="cart-price cart-column">
                    ${product.price} €
                </td>
                <td class="qty">
                    ${product.amount}
                </td>
                <td>
                    <button class="btn btn-danger btn-sm" onclick="removeItem(${product.id})" type="button">–</button>
                    <button class="btn btn-success btn-sm" onclick="addItem(${product.id})" type="button">+</button>
                </td>
            </tr>`
    });
    cartTotalContent = `<div>${cart.total.toFixed(2)} €</div>`;
    if(cart.productList.length === 0) {
        cartTotalContent = `<p>Der Warenkorb ist leer.</p>`
    }
    cartItemsContainer.append(productListContent);
    cartTotalContainer.append(cartTotalContent);
};

async function removeItem(productId) {
    const token = sessionStorage.getItem('token');
  
    try {
      const response = await fetch(`http://localhost:8080/api/positions/${productId}`, {
        method: 'PUT',
        headers: {
          'Authorization': token,
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({}),
      });
  
      if (response.ok) {
        getCart();
      } else {
        console.log("Error:", response.statusText);
      }
    } catch (error) {
      console.error("Fetch Error:", error);
    }
}

async function addItem(productId) {
    const token = sessionStorage.getItem('token');
  
    try {
      const response = await fetch(`http://localhost:8080/api/positions/${productId}`, {
        method: 'POST',
        headers: {
          'Authorization': token,
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({}),
      });
  
      if (response.ok) {
        getCart();
      } else {
        console.log("Error:", response.statusText);
      }
    } catch (error) {
      console.error("Fetch Error:", error);
    }
}



/* NOTES: Wieso Ging das nicht?
async function removeItem(productId) {
    $.ajax({
        url: "http://localhost:8080/api/positions/" + productId,
        type: "PUT",
        dataType: "json",
        headers: { "Authorization": sessionStorage.getItem('token')},
        contentType: "application/json",
        data: {},
        success: () => {
            console.log("BRO");
            getCart();
        },
        error: error => {
            console.log(error);
        }
    })
};
*/