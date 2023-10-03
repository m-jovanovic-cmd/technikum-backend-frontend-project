
const token = sessionStorage.getItem("token");

$.ajax({
    url: "http://localhost:8080/isadmin",
    type: "GET",
    cors: true,
    headers: { "Authorization": token },
    contentType: "application/json",
    data: {},
    success: success => {
        console.log(success);
    },
    error: error => {
        console.log(error);
        location.replace('/Frontend/index.html');
    }
});

var form = `<div class="container d-flex justify-content-center">
    <div class="border p-5 rounded">
    <div class="mt-3">
        <h2 class="px-0 mx-0">User:innen Verwaltung
        </h2>
    </div>
    <div class="row">
        <div class="mb-3 col-12 col-md-3">
            <label for="isAdmin" class="form-label">isAdmin</label>
                <select class="form-control" id="admin" name="admin">
                    <option value="true">True</option>
                    <option value="false">False</option>
                </select>
        </div>
    <div class="mb-3 col-12 col-md-3">
        <label for="lastname" class="form-label">Nachname</label>
        <input type="text" class="form-control" id="lastname" name="lastname" placeholder="Nachname" required>
        
    </div>
    <div class="mb-3 col-12 col-md-3">
        <label for="firstname" class="form-label">Vorname</label>
        <input type="text" class="form-control" id="firstname" name="firstname" placeholder="Vorname" required>
    </div>

        <div class="mb-3 col-12 col-md-3">
            <label for="email" class="form-label">Email-Addresse</label>
            <input type="email" class="form-control" id="email" name="email" placeholder="name@example.com">
        </div>
      
        <div class="mb-3 col-12 col-md-3">
            <label for="gender" class="form-label">Gender</label>
                <select class="form-select" id="gender" name="gender">
                    <option value="W">Weiblich</option>
                    <option value="M">Männlich</option>
                    <option value="Anderes">Anderes</option>
                    <option value="KA">Keine Angabe</option>
                </select>
        </div>

        <div class="mb-3 col-12 col-md-3">
            <label for="location" class="form-label">Wohnort</label>
            <input type="text" class="form-control" id="location" name="location" placeholder="Wohnort">
        </div>
        <div class="mb-3 col-12 col-md-3">
            <label for="password" class="form-label">Passwort</label>
            <input type="text" class="form-control" id="password" name="password"
                placeholder="Passwort">
                <span class="password-toggle" onclick="createUsertogglePasswordVisibility()">Hide</span>
        </div>
        <div class="mb-3 col-12 col-md-3">
            <label for="postcode" class="form-label">PLZ</label>
            <input type="text" class="form-control" id="postcode" name="postcode"
                placeholder="Postleitzahl" oninput="validateInput(this)">
        </div>


        <div class="mb-3 col-12 col-md-3">
            <label for="role" class="form-label">Rolle</label>
                <select class="form-control" id="role" name="role" disabled>
                    <option value="Customer">Customer</option>
                    <option value="Admin">Admin</option>
                </select>
        </div>
        <div class="mb-3 col-12 col-md-3">
            <label for="status" class="form-label">Status</label>
                <select class="form-control" id="status" name="status" >
                    <option value="active">Active</option>
                    <option value="inactive">Inactive</option>
                </select>
        </div>
        <div class="mb-3 col-12 col-md-3">
            <label for="street" class="form-label">Straße</label>
            <input type="text" class="form-control" id="street" name="street" placeholder="Beispielstraße">
        </div>
        <div class="mb-3 col-12 col-md-3">
            <label for="streetnumber" class="form-label">Top</label>
            <input type="text" class="form-control" id="streetnumber" name="streetnumber" placeholder="Top">
        </div>
        <div class="mb-3 col-12 col-md-3">
            <label for="username" class="form-label">Username</label>
            <input type="text" class="form-control" id="username" name="username" placeholder="Username">

        </div>
    </div>
    <button type="button" class="btn btn-primary mt-3" id="saveButton">Save</button>
    </div>
</div>`
document.getElementById("form").innerHTML = form;

// Get a reference to the isAdmin and role select elements
const isAdminSelect = document.getElementById('admin');
const roleSelect = document.getElementById('role');
//automatically sets role to admin or customer depending what you choose, field from Rolle is diabled, so only isAdmin can be changed
// Add an event listener to the isAdmin select element
isAdminSelect.addEventListener('change', function () {
    // Check if isAdmin is true
    if (isAdminSelect.value === 'true') {
        // If true, set the role to 'admin'
        roleSelect.value = 'Admin';
    } else {
        // If false, set the role to 'customer'
        roleSelect.value = 'Customer';
    }
});


//show/hide password
function createUsertogglePasswordVisibility() {
    const passwordInput = document.getElementById("password");
    const passwordToggle = document.querySelector(".password-toggle");

    // Toggle the input's type between "password" and "text"
    passwordInput.type = passwordInput.type === "password" ? "text" : "password";

    // Change the text of the toggle button
    passwordToggle.textContent = passwordToggle.textContent === "Show" ? "Hide" : "Show";

    // Add or remove a class to style the button differently when clicked
    passwordToggle.classList.toggle("clicked");
}

//validate postcode
function validateInput(inputField) {
    // Remove any non-numeric characters
    inputField.value = inputField.value.replace(/[^0-9]/g, '');

    // Limit the input to exactly four digits
    if (inputField.value.length > 4) {
        inputField.value = inputField.value.slice(0, 4);
    }
}


// Trigger the change event to set the initial value
isAdminSelect.dispatchEvent(new Event('change'));

function displayError(input, message = '') {
    input.addClass("input-error");
    const parent = input.parent();
    parent.append(`<p class="error-message">${message}</p>`);
};

///////////////////////////
// G E T  R E Q U E S T //
/////////////////////////

$.get({
    url: "http://localhost:8080/api/users",
    cors: true,
    headers: {},
    success: (users) => {
        displayAllUsers(users);
        console.log(users)
    },
    error: console.error
});


function displayAllUsers(users) {
    const tableBody = $("#table tbody");
    //tableBody.empty(); // Clear the existing table rows
    for (let i = 0; i < users.length; i++) {
        const userDisplay = createUserDisplay(users[i]);
        tableBody.append(userDisplay);
    };
};
const authToken = sessionStorage.getItem("token");
console.log(authToken)
function createUserDisplay(user, authToken) {
    authToken = sessionStorage.getItem("token");
    console.log(authToken)
    // Create a new row element
    const row = $("<tr>");
    // Append table cells for each property you want to display
    row.append(`<td scope="row">${user.id}</td>`);
    row.append(`<td>${user.admin}</td>`);
    row.append(`<td>${user.email}</td>`);
    row.append(`<td>${user.firstname}</td>`);
    row.append(`<td>${user.gender}</td>`);
    row.append(`<td>${user.lastname}</td>`);
    row.append(`<td>${user.location}</td>`);
    //row.append(`<td>${user.password}</td>`);
    row.append(`<td>${user.postcode}</td>`);
    row.append(`<td>${user.role}</td>`);
    row.append(`<td>${user.street}</td>`);
    row.append(`<td>${user.streetnumber}</td>`);
    row.append(`<td>${user.username}</td>`);
    row.append(`<td> <button type="button" id="updatebuttonputrequest" class="btn btn-warning mt-3" onclick="updatebuttonputrequest(${user.id}, '${authToken}')">Edit</button></td>`);
    row.append(`<td><button type="button" id="sendDeleteRequest" class="btn btn-danger mt-3" onclick="sendDeleteRequest(${user.id}, '${authToken}')">Delete</button></td>`);


    return row; // Return the created row element
}

/////////////////////////////////
//  D E L E T E  R E Q U E S T //
/////////////////////////////////


//$("#sendDeleteRequest").on("click", () => {
//    const userId = $(this).attr(`${user.id}`); // Replace with your actual attribute name
//    console.log(sessionStorage.getItem("token"))
//    sendDeleteRequest(userId, token);
//});

function sendDeleteRequest(userId, authToken) {
    console.log(authToken)
    //var token = sessionStorage.getItem("token");
    if (confirm("Are you sure you want to delete this record?")) {
        $.ajax({
            url: `http://localhost:8080/api/users/${userId}`,
            type: "DELETE",
            cors: true,
            headers: { "Authorization": authToken },
            contentType: "application/json",
            success: (response) => {
                // After successful deletion, reload the list of users
                location.reload(true);
            },
            error: function (xhr, status, error) {
                console.log("Status: " + status);
                console.log("Error: " + error);
                console.log(xhr.responseText);
            }
        });
    }
}

////////////////////
// POST REQEUEST //
///////////////////
function createUser(newUser, authToken) {
    let errorMessage = 'Bitte gültigen Wert eintragen.';
    var errorCount = 0;
    // remove Validation error-messages
    $(".input-error").removeClass("input-error");
    $(".error-message").remove();
    /* Checking form function */
    for (let key in newUser) {
        if (newUser.hasOwnProperty(key) && newUser[key] === '') {
            errorCount++;
            let field = $('#' + key)
            displayError(field, errorMessage)
        }
    };
    console.log(authToken)
    $.ajax({
        url: "http://localhost:8080/api/users",
        type: "POST",
        cors: true,
        contentType: "application/json",
        headers: { "Authorization": authToken },
        data: JSON.stringify(newUser),
        success: (response) => {
            // Display the created user or perform other actions here
            console.log("User created:", response);
            location.reload(true);
            window.alert("User created successfully!");

        },
        error: (error) => {
            console.log(newUser)
            // Handle errors here if needed
            console.error("Error:", error);
        }
    });
}


// Add a click event listener to the "Save" button
$("#saveButton").on("click", e => {

    // Assign form input values to the user object properties
    const newUser = {
        "id": $("#userId").val(),
        "admin": $("#admin").val(),
        "email": $("#email").val(),
        "firstname": $("#firstname").val(),
        "gender": $("#gender").val(),
        "lastname": $("#lastname").val(),
        "location": $("#location").val(),
        "password": $("#password").val(),
        "postcode": $("#postcode").val(),
        "role": $("#role").val(),
        "status": $("#status").val(),
        "street": $("#street").val(),
        "streetnumber": $("#streetnumber").val(),
        "username": $("#username").val()
    }
    // Send the user data to the server
    console.log(newUser)
    createUser(newUser, authToken);

});

///////////////////////////
// P U T  R E Q U E S T //
/////////////////////////

function updatebuttonputrequest(userId) {
    userfromdatabase = getUser(userId)
}
function getUser(userId) {

    $.ajax({
        url: `http://localhost:8080/api/users/get${userId}`,
        type: "GET",
        cors: true,
        headers: {},
        success: (response) => {

            console.log(response.admin)
            //console.log("i got the user")
            populatedform = edit(response)
            return populatedform;
        },
        error: console.error
    });
}

function edit(user) {
    console.log(user)
    let editForm = `<div class="container d-flex justify-content-center">
    <div class="border p-5 rounded">
    <div class="mt-3">
        <h2 class="px-0 mx-0">User:innen Verwaltung
        </h2>
    </div>
    <div class="row">
        <div class="mb-3 col-12 col-md-3">
                <label for="userId" class="form-label">User ID</label>
            <input type="userId" value="${user.id}" class="form-control" id="newuserId" name="id" placeholder="User ID" disabled>
        </div>
        
        <div class="mb-3 col-12 col-md-3">
            <label for="isAdmin" class="form-label">isAdmin</label>
                <select class="form-select" id="admin" name="admin">
                    <option value="true" ${user.admin ? 'selected' : ''}>True</option>
                    <option value="false" ${!user.admin ? 'selected' : ''}>False</option>
                </select>
        </div>
        <div class="mb-3 col-12 col-md-3">
            <label for="email" class="form-label">Email-Addresse</label>
            <input type="email" value="${user.email}" class="form-control" id="newemail" name="email" placeholder="name@example.com">
        </div>

        <div class="mb-3 col-12 col-md-3">
            <label for="firstname" class="form-label">Vorname</label>
            <input type="text" value="${user.firstname}" class="form-control" id="newfirstname" name="newfirstname" placeholder="Vorname">
        </div>

        <div class="mb-3 col-12 col-md-3">
            <label for="gender" class="form-label">Gender</label>
                <select class="form-select" id="gender" name="gender">
                        <option value="W" ${user.gender === 'W' ? 'selected' : ''}>Weiblich</option>
                        <option value="M" ${user.gender === 'M' ? 'selected' : ''}>Männlich</option>
                        <option value="Anderes" ${user.gender === 'Anderes' ? 'selected' : ''}>Anderes</option>
                        <option value="KA" ${user.gender === 'Keine Angabe' ? 'selected' : ''}>Keine Angabe</option>
                </select>
        </div>
        <div class="mb-3 col-12 col-md-3">
            <label for="lastname" class="form-label">Nachname</label>
            <input type="text" value="${user.lastname}" class="form-control" id="newlastname" name="lastname" placeholder="Nachname">
        </div>

        <div class="mb-3 col-12 col-md-3">
            <label for="location" class="form-label">Wohnort</label>
            <input type="text" value="${user.location}" class="form-control" id="newlocation" name="location" placeholder="Wohnort">
        </div>
        <div class="mb-3 col-12 col-md-3">
            <label for="password" class="form-label">Passwort</label>
            <input type="password" value="${user.password}" class="form-control" id="newpassword" name="password">
            <span class="password-toggle" onclick="togglePasswordVisibility()">Show</span>
            </div>
        <div class="mb-3 col-12 col-md-3">
            <label for="postcode" class="form-label">PLZ</label>
            <input type="text" value="${user.postcode}" class="form-control" id="newpostcode" name="postcode"
                placeholder="Postleitzahl" oninput="validateInputUpdateUser(this)">
        </div>
        <div class="mb-3 col-12 col-md-3">
            <label for="role" class="form-label">Rolle</label>
             <input type="role" value="${user.role}" class="form-control" id="role" name="role" placeholder="Rolle" disabled>
        </div>

        <div class="mb-3 col-12 col-md-3">
            <label for="status" class="form-label">Status</label>
                <select class="form-control" id="status" name="status">
                    <option value="active" ${user.status === 'active' ? 'selected' : ''}>Active</option>
                    <option value="inactive" ${user.status === 'inactive' ? 'selected' : ''}>Inactive</option>
                </select>
            </div>        
        <div class="mb-3 col-12 col-md-3">
            <label for="street" class="form-label">Straße</label>
            <input type="text" value="${user.street}" class="form-control" id="newstreet" name="street" placeholder="Beispielstraße">
        </div>
        <div class="mb-3 col-12 col-md-3">
            <label for="streetnumber" class="form-label">Top</label>
            <input type="text" value="${user.streetnumber}" class="form-control" id="newstreetnumber" name="newstreetnumber" placeholder="Top">
        </div>
        <div class="mb-3 col-12 col-md-3">
            <label for="username" class="form-label">Username</label>
            <input type="text" value="${user.username}"
             class="form-control" id="newusername" name="newusername" placeholder="Username">
        </div>
    </div>
    <button type="button" id="sendUpdatedUser" class="btn btn-primary mt-3">Update</button>
    <a href="./adduserpanel.html" class="btn btn-light mt-3" role="button">New User</a>
    </div>
</div>`;

    $('.alert').alert()
    document.getElementById("form").innerHTML = editForm;
    //console.log(editForm)
    const isAdminSelect = document.getElementById('admin');
    const roleSelect = document.getElementById('role');
    isAdminSelect.addEventListener('change', function () {
        // Check if isAdmin is true
        if (isAdminSelect.value === 'true') {
            // If true, set the role to 'admin'
            roleSelect.value = 'Admin';
        } else {
            // If false, set the role to 'customer'
            roleSelect.value = 'Customer';
        }
    });

}

//hide show password
function togglePasswordVisibility() {
    const passwordInput = document.getElementById("newpassword");
    const passwordToggle = document.querySelector(".password-toggle");

    // Toggle the input's type between "password" and "text"
    passwordInput.type = passwordInput.type === "password" ? "text" : "password";

    // Change the text of the toggle button
    passwordToggle.textContent = passwordToggle.textContent === "Show" ? "Hide" : "Show";

    // Add or remove a class to style the button differently when clicked
    passwordToggle.classList.toggle("clicked");
}

//validate postcode
function validateInputUpdateUser(inputField) {
    // Remove any non-numeric characters
    inputField.value = inputField.value.replace(/[^0-9]/g, '');

    // Limit the input to exactly four digits
    if (inputField.value.length > 4) {
        inputField.value = inputField.value.slice(0, 4);
    }
}

$(document).on("click", "#sendUpdatedUser", function (e) {
    console.log("Button clicked!");
    const user = {
        "id": $("#newuserId").val(),
        "admin": $("#admin").val(),
        "email": $("#newemail").val(),
        "firstname": $("#newfirstname").val(),
        "gender": $("#gender").val(),
        "lastname": $("#newlastname").val(),
        "location": $("#newlocation").val(),
        "password": $("#newpassword").val(),
        "postcode": $("#newpostcode").val(),
        "role": $("#role").val(),
        "status": $("#status").val(),
        "street": $("#newstreet").val(),
        "streetnumber": $("#newstreetnumber").val(),
        "username": $("#newusername").val()
    }
    console.log("new user: ", user);
    sendUpdatedUser(user);
});

function sendUpdatedUser(user) {
    console.log(user)
    let errorMessage = 'Bitte gültigen Wert eintragen.';
    var errorCount = 0;
    $(".input-error").removeClass("input-error");
    $(".error-message").remove();
    /* Checking form function */
    for (let key in user) {
        if (user.hasOwnProperty(key) && user[key] === '') {
            errorCount++;
            let field = $('#' + 'new' + key)
            displayError(field, errorMessage)
        }
    };
    console.log("sendUpdatedUser(user): ", user);
    $.ajax({
        url: `http://localhost:8080/api/users/update/${user.id}`,
        type: "PUT",
        headers: { "Authorization": authToken },
        contentType: "application/json",
        data: JSON.stringify(user),
        success: (response) => {
            if (confirm("User was successfully updated. Click OK to continue."))

                console.log("User was updated");
            location.reload(true);
        },
        error: (error) => {
            console.log("Error updating user:", error);
        }
    });
}

