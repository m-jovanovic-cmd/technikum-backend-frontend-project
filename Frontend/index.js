const token = sessionStorage.getItem('token');

if(!token) {
    const kundenregistrierungscontainer = $('#kundenregistrierungContainer');
    const content = (
        `<p>Wir bei Cloud-IA haben uns dem Verkauf der freiesten Sache der Welt verschrieben: Wolken</p>
        <br>
        <p>Hier kommen Sie zur Kundenregistrierung:</p>
        <a href="./kundenregistrierung.html">Kundenregistrierung</a>
        <br><br><br>`
    );

    kundenregistrierungscontainer.append(content);
}