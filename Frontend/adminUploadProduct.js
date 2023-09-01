const uploadImage = (event) => {
    event.preventDefault();
    console.log(el);
};

function uploadProductData(event) {
    event.preventDefault();

    const product = {
        "name": $("#productname").val(),
        "description": $("#description").val(),
        "price": $("#price").val(),
        "quantity": $("#quantity").val(),
        "type": $("#type").val(),
        "imageUrl": $("#imageUrl").val(),
        "taxId": $("#taxId").val(),
    }

    console.log(product);

    $.ajax({
        url: "http://localhost:8080/api/products",
        type: "POST",
        cors: true,
        headers: { "Authorization": sessionStorage.getItem("token") },
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
                    const input = $("#" + err.field + "Input");
                    input.addClass("input-error");
    
                    const parent = input.parent();
                    parent.append(`<p class="error-message">${err.defaultMessage}</p>`);
                }
            }
        }
    });
};

// DEPRECIATED TEMPLATE
$("#createProductButton").on("click", e =>{

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
});

$("#upload-button").on("click", e => {

    // Klasse?
    const prodData = {
        "name": $("#produktname").val(),
        "produkttyp": $("#produkttyp").val(),
        "beschreibung": $("#produktbeschreibung").val(),
    }

    const fileData = $("#upload-button").val();

    $.ajax({
        url: "http://localhost:8080/product/upload",
        type: "POST",
        cors: true,
        //contentType: "application/json",
        data: {data1: JSON.stringify(prodData), data2: fileData},
        success: console.log,
        error: console.error
    });
});