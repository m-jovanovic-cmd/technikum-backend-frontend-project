function uploadProductData(event) {
    event.preventDefault();

    // remove Validation error-messages
    $(".input-error").removeClass("input-error");
    $(".error-message").remove();

    // get all data needed for the requests
    const product = {
        "name": $("#name").val(),
        "description": $("#description").val(),
        "price": $("#price").val(),
        "quantity": $("#quantity").val(),
        "type": $("#type").val(),
        "imageUrl": $("#imageUrl").val(),
        "taxId": $("#taxId").val(),
    }

    // get the file data
    const fileInput = document.getElementById("imageUpload");
    const fileData = new FormData();
    fileData.append("file", fileInput.files[0]);

    console.log(fileData);

    const token = sessionStorage.getItem("token");

    // init values for validation
    var errorCount = 0;
    let errorMessage = 'Bitte gültigen Wert eintragen.';

    // frontend validation
    for (let key in product) {
        if (product.hasOwnProperty(key) && product[key] === '') {
            errorCount++;
            let field = $('#' + key)
            displayError(field, errorMessage)
        }
    }

    // if(errorCount > 0) return;

    console.log(product);

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
            console.log(response);
            //createProduct(product);
        },
        error: function(error) {
            console.error("Error:", error)
        },
    });

    return false;

    /*$.ajax({
        url: "http://localhost:8080/api/products",
        type: "POST",
        cors: true,
        headers: { "Authorization": token },
        contentType: "application/json",
        data: JSON.stringify(product),
        success: success => {
            console.log('success');
            $("input").val("");
            console.log($("input"));
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
    });*/
};

function displayError(input, message= '') {
    input.addClass("input-error");
    const parent = input.parent();
    parent.append(`<p class="error-message">${message}</p>`);
}

// DEPRECIATED TEMPLATE
/*$("#createProductButton").on("click", e =>{

    $(".input-error").removeClass("input-error");
    $(".error-message").remove();

    const product = {
        "name": $("#nameInput").val(),
        "description": $("#descriptionInput").val(),
        "imageUrl": $("#imageUrlInput").val(),
        "price": $("#priceInput").val(),
        "quantity": $("#quantityInput").val(),
        "type": $("#typeInput").val(),
        "taxId": $("#taxIdInput").val()
    }
});*/