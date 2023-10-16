$(document).ready(function () {
    const id = getCurrentId();
    const url = "http://localhost:8080/api/products/details/" + id;
    const container = $('#productContainer');

    $.ajax({
        type: "GET",
        url: url,
        cors: true,
        headers: {},
        success: (product) => {
            console.log(product);
            container.append(displayProduct(product));
        },
        error: () => {
            console.log('damn')
        }
    });
});

function getCurrentId() {
    const url = window.location.href;
    urlObject = new URL(url);
    return urlObject.searchParams.get('id');
};

function displayProduct(product) {
    const content = $(`
    <div class="content d-flex justify-content-center" id="productContainer">
        <div class="col-lg-6 col-md-8 col-sm-12">
            <div class="row">                   
                <div class="card border border-3 ">
                        <img class="card-img-top p-2" src="./${product.imageUrl}" alt="Ein Bild von ${product.name}">
                            <div class="card-body">
                                <h1 class="card-title text-center">${product.name}</h4>
                                <p class="card-text">${product.description}</p>
                                <p>Type: ${product.type}</p>
                                <p>Verfügbar: ${product.quantity}</p>
                                <p>Preis: ${product.price}€</p>
                                <button type="button" class="btn btn-secondary">in Warenkorb</button>
                            </div>
                    </div>
                </div>
            </div>
        </div>
    </div>`
    );
    return content;

};
