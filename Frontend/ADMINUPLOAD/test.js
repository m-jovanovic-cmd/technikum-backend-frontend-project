var form = `<div class="container d-flex justify-content-center">
    <div class="border p-5 rounded">
    <div class="mt-3">
        <h2 class="px-0 mx-0">User:innen Verwaltung
        </h2>
    </div>
    <div class="row">
        <div class="mb-3 col-12 col-md-3">
            <label for="userId" class="form-label">User ID</label>
            <input type="userId" class="form-control" id="userId" name="userId" placeholder="User ID">
        </div>

        <div class="mb-3 col-12 col-md-3">
            <label for="isAdmin" class="form-label">isAdmin</label>
            <input type="text" class="form-control" id="isAdmin" name="is Admin "
                placeholder="0 = nein, 1 = ja">
        </div>

        <div class="mb-3 col-12 col-md-3">
            <label for="mail" class="form-label">Email-Addresse</label>
            <input type="email" class="form-control" id="mail" name="mail" placeholder="name@example.com">
        </div>

        <div class="mb-3 col-12 col-md-3">
            <label for="firstName" class="form-label">Vorname</label>
            <input type="text" class="form-control" id="firstName" name="firstName" placeholder="Vorname">
        </div>

        <div class="mb-3 col-12 col-md-3">
            <label for="gender" class="form-label">Gender</label>
            <input type="text" class="form-control" id="gender" name="gender" placeholder="Gender">
        </div>
        <div class="mb-3 col-12 col-md-3">
            <label for="lastName" class="form-label">Nachname</label>
            <input type="text" class="form-control" id="lastName" name="lastName" placeholder="Nachname">
        </div>

        <div class="mb-3 col-12 col-md-3">
            <label for="location" class="form-label">Wohnort</label>
            <input type="text" class="form-control" id="location" name="location" placeholder="Wohnort">
        </div>
        <div class="mb-3 col-12 col-md-3">
            <label for="password" class="form-label">Passwort</label>
            <input type="text" class="form-control" id="password" name="password"
                placeholder="**********">
        </div>
        <div class="mb-3 col-12 col-md-3">
            <label for="postcode" class="form-label">PLZ</label>
            <input type="text" class="form-control" id="postcode" name="postcode"
                placeholder="Postleitzahl">

        </div>
        <div class="mb-3 col-12 col-md-3">
            <label for="role" class="form-label">Rolle</label>
            <input type="text" class="form-control" id="role" name="role" placeholder="Costumer, Admin">
        </div>
        <div class="mb-3 col-12 col-md-3">
            <label for="status" class="form-label">Status</label>
            <input type="text" class="form-control" id="status" name="status"
                placeholder="active, inactive">
        </div>
        <div class="mb-3 col-12 col-md-3">
            <label for="street" class="form-label">Straße</label>
            <input type="text" class="form-control" id="street" name="street" placeholder="Beispielstraße">
        </div>
        <div class="mb-3 col-12 col-md-3">
            <label for="streetnumber" class="form-label">Top</label>
            <input type="text" class="form-control" id="streetnumber" name="streetnumber">
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

function createUserDisplay(user) {
    // Create a new row element
    const row = $("<tr>");
    // Append table cells for each property you want to display
    row.append(`<td scope="row">${user.id}</td>`);
    row.append(`<td>${user.admin}</td>`);
    row.append(`<td>${user.email}</td>`);
    row.append(`<td>${user.firstName}</td>`);
    row.append(`<td>${user.gender}</td>`);
    row.append(`<td>${user.lastname}</td>`);
    row.append(`<td>${user.location}</td>`);
    row.append(`<td>${user.password}</td>`);
    row.append(`<td>${user.postcode}</td>`);
    row.append(`<td>${user.role}</td>`);
    row.append(`<td>${user.street}</td>`);
    row.append(`<td>${user.streetnumber}</td>`);
    row.append(`<td>${user.username}</td>`);
    row.append(`<td td > <button type="button" id="updatebuttonputrequest" class="btn btn-warning mt-3" onclick="updatebuttonputrequest(${user.id})">Edit</button></td>`);
    row.append(`<td><button type="button" id="sendDeleteRequest" class="btn btn-danger mt-3" onclick="sendDeleteRequest(${user.id})">Delete</button></td>`);

    return row; // Return the created row element
}


/////////////////////////////////
// D E L E T E  R E Q U E S T //
///////////////////////////////

$("#sendDeleteRequest").on("click", () => {
    const userId = $(this).attr(`${user.id}`); // Replace with your actual attribute name
    sendDeleteRequest(userId);
});
function sendDeleteRequest(userId) {
    if (confirm("Are you sure you want to delete this record?")) {
        $.ajax({
            url: `http://localhost:8080/api/users/delete/${userId}`,
            type: "DELETE",
            cors: true,
            contentType: "application/json",
            success: (response) => {
                // After successful deletion, reload the list of users
                location.reload(true);
            },
            error: (error) => {
                console.log(error);
            }
        });
    }
}

////////////////////
// POST REQEUEST //
//////////////////
function createUser(newUser) {
    $.ajax({
        url: "http://localhost:8080/api/users",
        type: "POST",
        cors: true,
        contentType: "application/json",
        data: JSON.stringify(newUser),
        success: (response) => {
            // Display the created user or perform other actions here
            console.log("User created:", response);
            location.reload(true);
        },
        error: (error) => {
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
        "admin": $("#isAdmin").val(),
        "email": $("#mail").val(),
        "firstName": $("#firstName").val(),
        "gender": $("#gender").val(),
        "lastName": $("#lastName").val(),
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
    createUser(newUser);

});

///////////////////////////
// P U T  R E Q U E S T //
/////////////////////////
//für form button id=sendUpdatedUser
//für update button id= updatebuttonputrequest
$("#updatebuttonputrequest").on("click", () => {
    const userId = $(this).attr(`${user.id}`);
    console.log(userId)
});

function updatebuttonputrequest(userId) {
    userfromdatabase = getUser(userId)
    //console.log(userfromdatabase)
    //edit(userfromdatabase)
}
function getUser(userId) {

    $.ajax({
        url: `http://localhost:8080/api/users/get${userId}`,
        type: "GET",
        cors: true,
        headers: {},
        success: (response) => {

            console.log(response)
            console.log("i got the user")
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
            <input type="userId" value="${user.id}" class="form-control" id="newuserId" name="id" placeholder="User ID">
        </div>

        <div class="mb-3 col-12 col-md-3">
            <label for="isAdmin" class="form-label">isAdmin</label>
            <input type="text" value="${user.admin}" class="form-control" id="newisAdmin" name="admin"
                placeholder="0 = nein, 1 = ja">
        </div>

        <div class="mb-3 col-12 col-md-3">
            <label for="mail" class="form-label">Email-Addresse</label>
            <input type="email" value="${user.email}" class="form-control" id="newmail" name="mail" placeholder="name@example.com">
        </div>

        <div class="mb-3 col-12 col-md-3">
            <label for="firstName" class="form-label">Vorname</label>
            <input type="text" value="${user.firstName}" class="form-control" id="newfirstName" name="firstName" placeholder="Vorname">
        </div>

        <div class="mb-3 col-12 col-md-3">
            <label for="gender" class="form-label">Gender</label>
            <input type="text" value="${user.gender}" class="form-control" id="newgender" name="gender" placeholder="Gender">
        </div>
        <div class="mb-3 col-12 col-md-3">
            <label for="lastName" class="form-label">Nachname</label>
            <input type="text" value="${user.lastname}" class="form-control" id="newlastName" name="lastName" placeholder="Nachname">
        </div>

        <div class="mb-3 col-12 col-md-3">
            <label for="location" class="form-label">Wohnort</label>
            <input type="text" value="${user.location}" class="form-control" id="newlocation" name="location" placeholder="Wohnort">
        </div>
        <div class="mb-3 col-12 col-md-3">
            <label for="password" class="form-label">Passwort</label>
            <input type="password" value="${user.password}" class="form-control" id="newpassword" name="password">
        </div>
        <div class="mb-3 col-12 col-md-3">
            <label for="postcode" class="form-label">PLZ</label>
            <input type="text" value="${user.postcode}" class="form-control" id="newpostcode" name="postcode"
                placeholder="Postleitzahl">

        </div>
        <div class="mb-3 col-12 col-md-3">
            <label for="role" class="form-label">Rolle</label>
            <input type="text" value="${user.role}" class="form-control" id="newrole" name="role" placeholder="Costumer, Admin">
        </div>
        <div class="mb-3 col-12 col-md-3">
            <label for="status" class="form-label">Status</label>
            <input type="text" value="${user.status}" class="form-control" id="newstatus" name="status"
                placeholder="active, inactive">
        </div>
        <div class="mb-3 col-12 col-md-3">
            <label for="street" class="form-label">Straße</label>
            <input type="text" value="${user.street}" class="form-control" id="newstreet" name="street" placeholder="Beispielstraße">
        </div>
        <div class="mb-3 col-12 col-md-3">
            <label for="streetnumber" class="form-label">Top</label>
            <input type="text" value="${user.streetnumber}" class="form-control" id="newstreetnumber" name="streetnumber">
        </div>
        <div class="mb-3 col-12 col-md-3">
            <label for="username" class="form-label">Username</label>
            <input type="text" value="${user.username}"
             class="form-control" id="newusername" name="newusername" placeholder="Username">

        </div>
    </div>
    <button type="button" id="sendUpdatedUser" class="btn btn-primary mt-3">Update</button>
    </div>
</div>`;

    document.getElementById("form").innerHTML = editForm;
    console.log('edit work');
    //console.log(editForm)
}

// Event listener for the "Update" button click
//streetnumber nukk
$(document).on("click", "#sendUpdatedUser", function (e) {
    console.log("Button clicked!");
    const user = {
        "id": $("#newuserId").val(),
        "admin": $("#newisAdmin").val(),
        "email": $("#newmail").val(),
        "firstName": $("#newfirstName").val(),
        "gender": $("#newgender").val(),
        "lastName": $("#newlastName").val(),
        "location": $("#newlocation").val(),
        "password": $("#newpassword").val(),
        "postcode": $("#newpostcode").val(),
        "role": $("#newrole").val(),
        "status": $("#newstatus").val(),
        "street": $("#newstreet").val(),
        "streetnumber": $("#newstreetnumber").val(),
        "username": $("#newusername").val()
    }
    console.log("new user: ", user);

    // Pass the user object as an argument to your desired function
    sendUpdatedUser(user);
});
//TO-DO id aus formular entfernen
// Function to update the user with the provided user object
function sendUpdatedUser(user) {
    console.log("sendUpdatedUser(user): ", user);
    // Your AJAX request to update the user with the user object
    $.ajax({
        url: `http://localhost:8080/api/users/update/${user.id}`,
        type: "PUT",
        contentType: "application/json",
        data: JSON.stringify(user),
        success: (response) => {
            console.log("User was updated");
        },
        error: (error) => {
            console.log("Error updating user:", error);
        }
    });
}