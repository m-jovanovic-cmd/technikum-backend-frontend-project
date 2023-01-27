$("#createProductButton").on("click", e =>{

    const product = {
        "name": "postman",
        "description": "Produkttest Frontend",
        "price": 10.23,
        "quantity": 4,
        "type": "frontend"
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