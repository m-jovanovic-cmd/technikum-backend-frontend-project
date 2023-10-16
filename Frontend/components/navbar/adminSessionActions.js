renderAdminActions();

function renderAdminActions() {
    const token = sessionStorage.getItem('token');

    $.ajax({
        url: "http://localhost:8080/isadmin",
        type: "GET",
        dataType: "json",
        headers: { "Authorization": token },
        contentType: "application/json",
        data: {},
        success: success => {
            if (success) {
                const actionContainer = $("#adminSessionActions");
                actionContainer.empty();

                const action = $(
                    `<ul class="navbar-nav ps-1 d-flex col">
                        <li class="nav-item active">
                            <a href="./adduserpanel.html" class="nav-link">ADD USER</a>
                        </li>
                        <li class="nav-item active">
                            <a href="./adminUploadProduct.html" class="nav-link">ADD PRODUCT</a>
                        </li>               
                    </ul>`
                );

                actionContainer.append(action);
            }
        },
        error: error => {
            console.log(error);
        }
    });
}
