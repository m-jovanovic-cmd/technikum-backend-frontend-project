$.get({
    url: "http://localhost:8080/api/products",
    cors: true,
    headers: {},
    success: (products) => {
        displayAllProducts(products)
    },
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
    };
};

function createProductDisplay(product) {

    const content = $(`
        <div class="col-lg-4 col-md-6 col-sm-12">
            <div class="card border border-3">
                <img class="card-img-top p-2" src="${product.imageUrl}" alt="Ein Bild von ${product.name}">
                <div class="card-body">
                    <h4 class="card-title text-center">${product.name}</h4>
                    <p class="card-text">${product.description}</p>
                    <p>Type: ${product.type}<br>Verfügbar: ${product.quantity}<br>Preis: ${product.price}€</p>
                    <div class="d-flex justify-content-between">
                        <a href="#" class="btn btn-primary">Go somewhere</a>
                        <button type="button" class="btn btn-secondary">in Warenkorb</button>
                    </div>
                </div>
            </div>
        </div>`
    );

    return content;
};