const token = sessionStorage.getItem("token");

const form = $("#productForm");

$.ajax({
    url: "http://localhost:8080/isadmin",
    type: "GET",
    cors: true,
    headers: { "Authorization": token },
    contentType: "application/json",
    data: {},
    success: success => {
    },
    error: error => {
        console.log(error);
        location.replace('/Frontend/index.html');
    }
});

function getAndDrawTaxes() {
    $.get({
        url: "http://localhost:8080/api/taxes",
        cors: true,
        headers: { },
        success: (taxes) => { 
            displayAllTaxOptions(taxes) 
        },
        error: console.error
    });
}

getAndDrawTaxes();

$.ajax({
    url: "http://localhost:8080/api/products",
    type: "GET",
    cors: true,
    headers: { "Authorization": token },
    contentType: "application/json",
    data: {},
    success: (products) => {
        console.log(products);
        displayProductsInTable(products);
    },
})

function displayAllTaxOptions(taxes) {
    const taxSelectElement = $("#taxId");
    let taxOptions = ``;
    taxes.map((tax) => {
        taxOptions += `<option value=${tax.id}>${tax.name} - ${tax.factor * 100}%</option>`
    })
    taxSelectElement.append(taxOptions);
}

function uploadProductData(event) {
    event.preventDefault();

    // remove Validation error-messages
    $(".input-error").removeClass("input-error");
    $(".error-message").remove();

    // get all data needed for the requests
    let product = {
        "name": $("#name").val(),
        "description": $("#description").val(),
        "price": $("#price").val(),
        "quantity": $("#quantity").val(),
        "type": $("#type").val(),
        "taxId": $("#taxId").val(),
    };
    
    // get the file data
    const fileInput = document.getElementById("imageUpload");
    const fileData = new FormData();
    fileData.append("file", fileInput.files[0]);

    // init values for validation
    var errorCount = 0;
    let errorMessage = 'Bitte gültigen Wert eintragen.';

    // frontend validation, fields + image
    for (let key in product) {
        if (product.hasOwnProperty(key) && product[key] === '') {
            errorCount++;
            let field = $('#' + key);
            displayError(field, errorMessage);
        }
    };
    if (!fileInput || fileInput.value == '') {
        displayError( $('#imageUpload'), errorMessage);
    };
    if (product.name.length < 4) {
        errorCount++;
        displayError( $('#name'), 'Name muss mind. 4 Zeichen lang sein.');
    }

    if(errorCount > 0) return;

    // requests
    $.ajax({
        url: "http://localhost:8080/api/products/imageUpload",
        type: "POST",
        cors: true,
        processData: false,
        contentType: false,
        headers: { "Authorization": token },
        data: fileData,
        success: (response) => {
            product = { ...product, imageUrl: response };
            uploadProductWithUrl(product, token);
        },
        error: function(error) {
            console.error("Error:", error)
        },
    });

    return false;
};

function uploadProductWithUrl(product, token) {
    $.ajax({
        url: "http://localhost:8080/api/products",
        type: "POST",
        cors: true,
        headers: { "Authorization": token },
        contentType: "application/json",
        data: JSON.stringify(product),
        success: success => {
            $("input").val("");
            $("textarea").val("");
            $('select').prop('selectedIndex',0);
            handleSuccess("Produkterstellung");
        },
        error: error => {
            console.log(error);
            if (error.status === 400) {
                for (let err of error.responseJSON.errors) {
                    let field = $("#" + err.field);
                    displayError(field, err.defaultMessage)
                }
            }
        }
    });
};


function displayProductsInTable(products) {
    const editTable = $("#editProductTable");
    let content = ``;
    for(product of products) {
        content = 
        `<tr>
            <td>${product.id}</td>
            <td>${product.name}</td>
            <td>${product.description}</td>
            <td>${product.imageUrl}</td>
            <td>${product.price}</td>
            <td>${product.quantity}</td>
            <td>${product.type}</td>
            <td>${product.tax.name}</td>
            <td><button class="btn btn-primary" type="button" onclick="editProduct(${product.id})">Bearbeiten</button></td>
        </tr>`;
        editTable.append(content);
    }
}

async function editProduct(id) {
    const product = await getProductById(id);
    console.log(product);

    const saveEditButtonContent = `
        <button id="editSaveButton" class="btn btn-success" type="button" onclick="saveEditedProduct('${product.imageUrl}')">Speichern</button>
    `

    $("#name").val(product.name);
    $("#description").val(product.description);
    $("#price").val(product.price);
    $("#quantity").val(product.quantity);
    $("#type").val(product.type);
    $("#taxId").val(product.tax.id);
    $("#productId").val(product.id);
    $("#productId").parent().parent().removeClass("d-none");
    $("#uploadButton").replaceWith(saveEditButtonContent);
    $("#currentImageUrl").empty().append(`<p>Current Picture: ${product.imageUrl}</p>`);
    form.removeAttr('onsubmit');
};

async function saveEditedProduct(currentImageUrl) {
    let imageUrl = $("#imageUpload").val();
    imageUrl = imageUrl === "" ? currentImageUrl : await getNewImageUrl();
    console.log(imageUrl);

    let product = {
        "id": $("#productId").val(),
        "name": $("#name").val(),
        "description": $("#description").val(),
        "price": $("#price").val(),
        "quantity": $("#quantity").val(),
        "type": $("#type").val(),
        "taxId": $("#taxId").val(),
        "imageUrl": imageUrl,
    };

    console.log(product);

    $.ajax({
        url: "http://localhost:8080/api/products/update",
        type: "PUT",
        cors: true,
        headers: { "Authorization": token },
        contentType: "application/json",
        data: JSON.stringify(product),
        success: success => {
            $("input").val("");
            $("textarea").val("");
            $('select').prop('selectedIndex',0);
            handleSuccess("Seite wird gleich neugeladen: Produktbearbeitung");
            setTimeout(() => {
                location.reload();
            }, 3000)
        },
        error: error => {
            console.log(error);
        }
    });
}

async function getProductById(id) {
    try {
        const response = await fetch("http://localhost:8080/api/products/details/" + id, {
            method: 'GET',
            headers: {
              'Authorization': token,
              'Content-Type': 'application/json'
            },
        });
    
        if (response.ok) {
            const data = await response.json();
            return data;
        } else {
            console.log("Error:", response.statusText);
            return null;
        }
    } catch (error) {
        console.error("Fetch Error:", error);
        return null;
    }  
};

async function getNewImageUrl() {
    const fileInput = document.getElementById("imageUpload");
    const fileData = new FormData();
    fileData.append("file", fileInput.files[0]);

    console.log(fileData);
    let result;

    try {
        result = await $.ajax({
            url: "http://localhost:8080/api/products/imageUpload",
            type: "POST",
            cors: true,
            processData: false,
            contentType: false,
            headers: { "Authorization": token },
            data: fileData,
        });

        return result;
    } catch(error) {
        console.log(error);
    }
}

function displayError(input, message= '') {
    input.addClass("input-error");
    const parent = input.parent();
    parent.append(`<p class="error-message">${message}</p>`);
};