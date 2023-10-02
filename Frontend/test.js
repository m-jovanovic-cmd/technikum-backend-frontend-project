const token = sessionStorage.getItem("token");

$.ajax({
    url: "http://localhost:8080/isadmin",
    type: "GET",
    cors: true,
    headers: { "Authorization": token },
    contentType: "application/json",
    data: {},
    success: success => {
        console.log(success);
    },
    error: error => {
        console.log(error);
        location.replace('/Frontend/index.html');
    }
});

$.get({
    url: "http://localhost:8080/api/taxes",
    cors: true,
    headers: {},
    success: (taxes) => {
        displayAllTaxOptions(taxes)
    },
    error: console.error
});

function displayAllTaxOptions(taxes) {
    console.log(taxes);
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

    // const token = sessionStorage.getItem("token");

    // init values for validation
    var errorCount = 0;
    let errorMessage = 'Bitte gültigen Wert eintragen.';

    // frontend validation, fields + image
    for (let key in product) {
        if (product.hasOwnProperty(key) && product[key] === '') {
            errorCount++;
            let field = $('#' + key)
            displayError(field, errorMessage)
        }
    };
    if (!fileInput || fileInput.value == '') {
        displayError($('#imageUpload'), errorMessage)
    };

    if (errorCount > 0) return;

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
            console.log(typeof (response));
            product = { ...product, imageUrl: response };
            console.log(product)
            uploadProductWithUrl(product, token);
        },
        error: function (error) {
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
            $('select').prop('selectedIndex', 0);
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

function displayError(input, message = '') {
    input.addClass("input-error");
    const parent = input.parent();
    parent.append(`<p class="error-message">${message}</p>`);
};

function createUser(newUser, authToken) {
    let errorMessage = 'Bitte gültigen Wert eintragen.';
    var errorCount = 0;
    // remove Validation error-messages
    $(".input-error").removeClass("input-error");
    $(".error-message").remove();
    /* Checking form function */
    for (let key in newUser) {
        if (newUser.hasOwnProperty(key) && newUser[key] === '') {
            errorCount++;
            let field = $('#' + key)
            displayError(field, errorMessage)
        }
    };
    console.log(authToken)
    $.ajax({
        url: "http://localhost:8080/api/users",
        type: "POST",
        cors: true,
        contentType: "application/json",
        headers: { "Authorization": authToken },
        data: JSON.stringify(newUser),
        success: (response) => {
            // Display the created user or perform other actions here
            console.log("User created:", response);
            location.reload(true);
            window.alert("User created successfully!");

        },
        error: (error) => {
            console.log(newUser)
            // Handle errors here if needed
            console.error("Error:", error);
        }
    });
}