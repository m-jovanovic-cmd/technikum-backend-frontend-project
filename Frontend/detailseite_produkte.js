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
const token = sessionStorage.getItem('token');
function displayProduct(product) {
    if (!token) {
        const content = $(`
        <div class="content d-flex justify-content-center" id="productContainer">
            <div class="col-lg-6 col-md-8 col-sm-12">
                <div class="row">                   
                    <div class="card border border-3 ">
                            <img class="card-img-top p-2" src="${product.imageUrl}" alt="Ein Bild von ${product.name}">
                                <div class="card-body">
                                    <h1 class="card-title text-center">${product.name}</h4>
                                    <p class="card-text">${product.description}</p>
                                    <p>Type: ${product.type}</p>
                                    <p>Verfügbar: ${product.quantity}</p>
                                    <p>Preis: ${product.price}€</p>
                                    <div class="d-flex justify-content-between">
                                    <a href="./produkte.html" class="btn btn-light" role="button">Zurück</a>
                                    </div>
                                </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>`
        );
        return content;
    } else {
        const content = $(`
        <div class="content d-flex justify-content-center" id="productContainer">
            <div class="col-lg-6 col-md-8 col-sm-12">
                <div class="row">                   
                    <div class="card border border-3 ">
                            <img class="card-img-top p-2" src="${product.imageUrl}" alt="Ein Bild von ${product.name}">
                                <div class="card-body">
                                    <h1 class="card-title text-center">${product.name}</h4>
                                    <p class="card-text">${product.description}</p>
                                    <p>Type: ${product.type}</p>
                                    <p>Verfügbar: ${product.quantity}</p>
                                    <p>Preis: ${product.price}€</p>
                                    <div class="d-flex justify-content-between">
                                    <a href="./produkte.html" class="btn btn-light" role="button">Zurück</a>
                                    <button type="button" class="btn btn-secondary">in Warenkorb</button>
                                    </div>
                                </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>`
        );
        return content;
    }

};



function parseJwt(token) {
    var base64Url = token.split('.')[1];
    var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    var jsonPayload = decodeURIComponent(window.atob(base64).split('').map(function (c) {
        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));

    return JSON.parse(jsonPayload);
}

var payloadData = parseJwt(token);

console.log(payloadData);
console.log(payloadData.id);      // 1
console.log(payloadData.sub);     // "t"
console.log(payloadData.admin);   // true
console.log(payloadData.exp);     // 1696929729


