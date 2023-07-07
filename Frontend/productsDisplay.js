$.get({
    url: "http://localhost:8080/api/products",
    cors: true,
    headers: { "Authorization": sessionStorage.getItem("token") },
    success: function(products) { displayAllProducts(products) },
    error: console.error
});

function displayAllProducts(products) {
    const productsContainer = $("#productsContainer");
    productsContainer.empty();

    let row;
    for (let i = 0; i < products.length; i++) {
        if (i % 3 === 0) {
            row = $(`<div class="row"></div>`);
            productsContainer.append(row);
        }
        row.append(createProductDisplay(products[i]));
    }
}

function createProductDisplay(product) {
    const div = $(`<div class="col-lg-4 col-md-6 col-sm-12 product"></div>`);
    const title = $(`<h2>${product.name}</h2><br>`);
    const img = $(`<img src="${product.imageUrl}" class="products_img" alt="Ein Bild von ${product.name}">`);
    const description = $(`<p>${product.description}</p>`);
    const quantityPrice = $(`<p>Type: ${product.type}<br>Verfügbar: ${product.quantity}<br>Preis: ${product.price}€</p>`);

    div.append(title);
    div.append(img);
    div.append(description);
    div.append(quantityPrice);

    return div;
}