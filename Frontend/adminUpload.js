$("#upload-button").on("click", e => {

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