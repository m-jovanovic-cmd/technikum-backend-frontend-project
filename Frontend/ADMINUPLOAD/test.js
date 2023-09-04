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
            <input type="password" class="form-control" id="Password" name="Password"
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
    row.append(`<td><button type="button" class="btn btn-warning mt-3" onclick="sendPutRequest(${user.id})">Edit</button></td>`);
    row.append(`<td><button type="button" class="btn btn-danger mt-3" onclick="sendDeleteRequest(${user.id})">Delete</button></td>`);

    return row; // Return the created row element
}
function sendPutRequest(userId, updatedUserData) {
    const url = `http://localhost:8080/api/users/${userId}`;

    // Create a PUT request with the URL and request body
    fetch(url, {
        method: 'PUT', // Use the PUT HTTP method
        headers: {
            'Content-Type': 'application/json', // Specify the content type
        },
        body: JSON.stringify(updatedUserData), // Convert the data to JSON format
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json(); // Parse the response as JSON
        })
        .then(data => {
            console.log('User updated:', data);
            // Handle the successful response here
        })
        .catch(error => {
            console.error('There was a problem with the fetch operation:', error);
            // Handle errors here
        });
}

// Create an empty user object
const user = {};

// Add a click event listener to the "Save" button
$("#saveButton").on("click", e => {
    // Assign form input values to the user object properties
    user.id = $("#userId").val();
    user.admin = $("#isAdmin").val();
    user.email = $("#mail").val();
    user.firstName = $("#firstName").val();
    user.gender = $("#gender").val();
    user.lastname = $("#lastName").val();
    user.location = $("#location").val();
    user.password = $("#password").val();
    user.postcode = $("#postcode").val();
    user.role = $("#role").val();
    user.street = $("#street").val();
    user.streetnumber = $("#streetnumber").val();
    user.username = $("#username").val();

    // Send the user data to the server
    saveUser(user);
});

// Function to send the user data to the server
function saveUser(user) {
    $.ajax({
        url: "http://localhost:8080/api/users",
        type: "POST",
        contentType: "application/json",
        data: JSON.stringify(user),
        success: (createdUser) => {
            // Display the created user or perform other actions here
            console.log("User created:", createdUser);
        }
    });
}

