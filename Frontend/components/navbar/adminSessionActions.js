renderAdminActions();

function renderAdminActions() {
    const token = sessionStorage.getItem('token');

    $.ajax({
        url: "http://localhost:8080/isadmin",
        type: "GET",
        dataType: "json", // Specify the expected response data type
        headers: { "Authorization": token },
        contentType: "application/json",
        data: {},
        success: success => {
            if (success) {
                const actionContainer = $("#adminSessionActions");
                actionContainer.empty();

                const action = $(
                    `<div class="dropdown">
                        <a class="nav-link dropdown-toggle" type="button" id="adminDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            ADMIN
                        </a>
                        <div class="dropdown-menu" aria-labelledby="adminDropdown">
                            <a class="dropdown-item" href="./adduserpanel.html">Add User</a>
                            <a class="dropdown-item" href="./adminUploadProduct.html">Add Products</a>
                        </div>
                    </div>`
                );

                actionContainer.append(action);
            }
        },
        error: error => {
            console.log(error);
        }
    });
}

//const action = $(
//    `< a href = "./adduserpanel.html" class= "nav-link" >
//    ADMIN
//</>`);
//actionContainer.append(action)