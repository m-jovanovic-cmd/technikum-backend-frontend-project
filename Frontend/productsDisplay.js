//https://www.youtube.com/watch?v=wxz5vJ1BWrc&ab_channel=JamesQQuick
let products = [];

const productsList = document.getElementById('products');
const searchBar = document.getElementById('searchBar');

// Add event listener to the search bar
searchBar.addEventListener('keyup', (e) => {
    const searchString = e.target.value.toLowerCase();
    console.log(searchString);
    //if searchStr is H -> h
    //if searchStr is h -> h
    //convert name to lowercase and then compare
    //convert Type to lowercase and then compare

    // Filter products array inside the event listener
    const filteredProducts = products.filter(product => {
        return product.name.toLowerCase().includes(searchString) || product.type.toLowerCase().includes(searchString);
    });

    //console.log(filteredProducts);
    displayAllProducts(filteredProducts)
});

// Make an API request to fetch products
$.get({
    url: "http://localhost:8080/api/products",
    cors: true,
    headers: {},
    success: (data) => {
        // Populate the products array with the fetched data
        products = data;

        // Display all products initially
        displayAllProducts(products);
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
                        <a href="./detailseite_produkte.html?id=${product.id}" class="btn btn-light" role="button">Details</a>
                        <button type="button" class="btn btn-secondary">in Warenkorb</button>
                    </div>
                </div>
            </div>
        </div>`
    );

    return content;
};