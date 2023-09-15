//////////////////////////
// G E T  R E Q U E S T //
//////////////////////////

//make here an array and add the product,to be displayed later
const arraywithcloudobjects = [];
// => get the product into the card
// => save the info and call it from everywhere (in an array?)

//this approach only work on detailseite, how to do it on the produkte side?
function getCurrentId() {
    const url = window.location.href;
    urlObject = new URL(url);
    return urlObject.searchParams.get('id');
};

//activated after pressing the button on the detailspage
$.get({
    id: getCurrentId(),
    url: "http://localhost:8080/api/products/details/" + id,
    cors: true,
    headers: {},
    success: (product) => {
        console.log(product);

        //adding the object to the array
        arraywithcloudobjects.push(product)

        //createProductCartModalDisplay(products) => do this in an extra function 
        //(activated when clicking the right button)
    },
    error: console.error
});

//click event on right button (already exists)
//display there all products with the help in a loop get the objects out
//how to display 2 identical products? => couting how many ids, then do price * amout of ids counted in loop
//pass with a link like in test.js (row.append(`<td><button type="button" id="sendDeleteRequest" class="btn btn-danger mt-3" onclick="sendDeleteRequest(${user.id})">Delete</button></td>`);)

function createProductCartModalDisplay(product) {

    const content = $(`
    <tr>
    <td class="w-25">
        <img src="./images/products/CharmingClaude.jpg${product.imageUrl}" class="img-fluid img-thumbnail"
            alt="Sheep">
    </td>
    <td>Charming Claude${product.name}</td>
    <td>10000€ ${product.price}</td>
    <td class="qty"><input type="text" class="form-control" id="input1" value="2">
    </td>
    <td>10000€${product.price}</td>
    <td>

        <a href="#" class="btn btn-danger btn-sm remove-item text-white"
            data-item-id="1">Remove</a>
        <i class="fa fa-times"></i>
        </a>
    </td>
</tr>`
    );

    return content;
};

//TO-DO
// make button get id from url
//make call to BE to get the product
//display the product on the cartModal wiht help from js (now it is hardcoded)
//make remove button work
//