var token = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwic3ViIjoidCIsImFkbWluIjp0cnVlLCJleHAiOjE2OTY5Mjk3Mjl9.YryzmiFbJrj0TyJiLzkFYDN_fIO1V1UKRyryf9RRvKw";

function parseJwt(token) {
    var base64Url = token.split('.')[1];
    var base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
    var jsonPayload = decodeURIComponent(window.atob(base64).split('').map(function (c) {
        return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
    }).join(''));

    return JSON.parse(jsonPayload);
}

var payloadData = parseJwt(token);

console.log(payloadData);
console.log(payloadData.id);      // 1
console.log(payloadData.sub);     // "t"
console.log(payloadData.admin);   // true
console.log(payloadData.exp);     // 1696929729
