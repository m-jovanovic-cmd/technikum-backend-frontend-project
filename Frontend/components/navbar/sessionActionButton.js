renderSessionButton();

function renderSessionButton() {
    const token = sessionStorage.getItem('token');

    const buttonContainer = $("#sessionActionButton");
    const cartButtonContainer = $("#cartButtonContainer");
    buttonContainer.empty();

    let button;

    if (!token) {
        button = $(
            `<div class="test">
                <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#loginModal">
                    Login
                </button>
            </div>`);
        buttonContainer.append(button);
    } else {
        button = $(
            `<div class="test">
                <button type="button" class="btn btn-primary" onclick="logout(event)">
                    Logout
                </button>
            </div>`
        );
        buttonContainer.append(button);
        cartButton = $(
            `<div>
                <button onClick="getCart()" id="cartButton" type="button" class="btn btn-primary me-2" data-bs-toggle="modal" data-bs-target="#cartModal">
                        Warenkorb
                </button>
            </div>`
        );
        cartButtonContainer.append(cartButton);
    }
};

function logout(event) {
    event.preventDefault();
    sessionStorage.removeItem('token');
    location.reload();
}