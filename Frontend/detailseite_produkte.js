

$(document).ready(function () {
    var params = {};
    window.location.search
        .replace(/[?&]+([^=&]+)=([^&]*)/gi, function (str, key, value) {
            params[key] = value;
        }
        );
    productId = params["id"]
    //document.write(productId);
    fetchProductData(productId)
        .then((product) => {
            const productContent = displayOneProduct(product);
            // Append 'productContent' to your page's DOM where you want to display it.
            $('#productContainer').append(productContent);
        })
        .catch((error) => {
            console.error("An error occurred:", error);
        });

});
function workWithId(productId) {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/api/products/{" + productId + "}",
        cors: true,
        headers: {},
        success: (product) => {
            resolve(product)
        },
        error: console.error
    });
}



function displayOneProduct(product) {

    const content = $(`
        <div class="col-lg-4 col-md-6 col-sm-12">
            <div class="card border border-3">
                <img class="card-img-top p-2" src="${product.imageUrl}" alt="Ein Bild von ${product.name}">
                <div class="card-body">
                    <h4 class="card-title text-center">${product.name}</h4>
                    <p class="card-text">${product.description}</p>
                    <p>Type: ${product.type}<br>Verfügbar: ${product.quantity}<br>Preis: ${product.price}€</p>
                    <div class="d-flex justify-content-between">
                        <a href="./detailseite_produkte.html?id=${product.id}" class="btn btn-light" role="button">Details</a>
                        <button type="button" class="btn btn-secondary">in Warenkorb</button>
                    </div>
                </div>
            </div>
        </div>`
    );

    return content;
};




