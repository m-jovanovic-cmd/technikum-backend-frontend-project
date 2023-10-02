renderAdminActions();

function renderAdminActions() {
    const token = sessionStorage.getItem('token');

    $.ajax({
        url: "http://localhost:8080/isadmin",
        type: "GET",
        cors: true,
        headers: { "Authorization": token },
        contentType: "application/json",
        data: {},
        success: success => {
            if(success) {
                const actionContainer = $("#adminSessionActions");
                actionContainer.empty();
        
                const action = $(
                    `<a href="./adminUploadProduct.html" class="nav-link">
                        ADMIN
                    </a>`);
                actionContainer.append(action);
            }
        },
        error: error => {
            console.log(error);
        }
    });
}