$("#createProductButton").on("click", e =>{

    const product = {
        "name": $("#nameInput").val(),
        "description": $("#descriptionInput").val(),
        "price": $("#priceInput").val(),
        "imageUrl": $("#imageUrlInput").val(),
        "quantity": $("#quantityInput").val(),
        "type": $("#typeInput").val()
    }

    $.ajax({
        url: "http://localhost:8080/products",
        type: "POST",
        cors: true,
        contentType: "application/json",
        data: JSON.stringify(product),
        success: console.log,
        error: console.error
    });
});