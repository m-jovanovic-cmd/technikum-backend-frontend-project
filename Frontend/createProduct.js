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


    $.ajax({
        url: "http://localhost:8080/api/products",
        type: "POST",
        cors: true,
        contentType: "application/json",
        data: JSON.stringify(product),
        success: success => {
            $("input").val("");
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
});