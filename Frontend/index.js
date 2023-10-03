/*$("#createProductButton").on("click", e =>{

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
});*/

const token = sessionStorage.getItem('token');

console.log(token)

if(!token) {
    const kundenregistrierungscontainer = $('#kundenregistrierungContainer');
    const content = `<p>Wir bei Cloud-IA haben uns dem Verkauf der freiesten Sache der Welt verschrieben: Wolken</p>
    <br>
    <p>Hier kommen Sie zur Kundenregistrierung:</p>
    <a href="./kundenregistrierung.html">Kundenregistrierung</a>
    <br><br><br>`

    kundenregistrierungscontainer.append(content);
}