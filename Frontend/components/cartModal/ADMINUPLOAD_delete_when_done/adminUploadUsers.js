var form = `<div>
<div class="form-group mt-3">
  <label for="name">Vorname</label>
  <input type="text" class="form-control" id="vorname" aria-describedby="enterVorname" placeholder="Enter Vorname">
</div>
<div class="form-group mt-3">
  <label for="name">Nachname</label>
  <input type="text" class="form-control" id="nachname" aria-describedby="enterNachname" placeholder="Enter Nachname">
</div>
<div class="form-group mt-3">
  <label for="email">Email</label>
  <input type="email" class="form-control" id="email" placeholder="Enter user-email">
</div>
<button type="submit" class="btn btn-primary mt-3" onclick="save()">Save</button>
</div>`;

function table() {
  let table = `<table class="table" >
    <thead>
      <tr>
        <th scope="col">ID</th>
        <th scope="col">Vorname</th>
        <th scope="col">Nachname</th>
        <th scope="col">Email</th>
        <th scope="col">Edit</th>
        <th scope="col">Delete</th>
      </tr>
    </thead>
    <tbody>
     `;
  for (let i = 0; i < details.length; i++) {
    table = table + `<tr>
        <td scope="row">${i + 1}</td>
        <td>${details[i].vorname}</td>
        <td>${details[i].nachname}</td>
        <td>${details[i].email}</td>
        <td><button type="button" class="btn btn-warning mt-3" onclick="edit(${i})">Edit</button>
        </td>
        <td><button type="button" class="btn btn-danger mt-3" onclick="deleteData(${i})">Delete</button></td>
        </tr>
    </tbody>
      `
  };
  table = table + `
    
    </table>`;
  document.getElementById("table").innerHTML = table
};

document.getElementById("form").innerHTML = form;
details = [];
getData();
table();

function getData() {
  let Data = localStorage.getItem("details");
  if (Data) {
    details = JSON.parse(Data);
  } else {
    setData();
  };
};
function setData() {
  localStorage.setItem("details", JSON.stringify(details));
}

function save() {
  // Get the input values
  const vornameInput = document.getElementById("vorname");
  const nachnameInput = document.getElementById("nachname");
  const emailInput = document.getElementById("email");
  if (vornameInput.value == 0) {
    alert("Vorname ist Leer");
    return;
  }

  // Retrieve the values
  const vorname = vornameInput.value;
  const nachname = nachnameInput.value;
  const email = emailInput.value;

  // Create an object to represent the user data
  const userData = {
    vorname: vorname,
    nachname: nachname,
    email: email

  };

  // Push the user data object into the 'details' array
  details.push(userData);
  setData();

  // Log the updated 'details' array (for demonstration purposes)
  console.log(details);

  // You can perform additional actions here, such as sending the data to a server.

  // Optionally, clear the form fields after saving
  vornameInput.value = "";
  nachnameInput.value = "";
  emailInput.value = "";
  table();
}

//console.log(details.value)

function deleteData(index) {
  details.splice(index, 1);
  setData();
  table();

  console.log('delete work')
  console.log(index)

}

function edit(index) {
  let editForm = `<div>
<div class="form-group mt-3">
  <label for="name">Update Vorname</label>
  <input type="text" value="${details[index].vorname}" class="form-control" id="newVorname" aria-describedby="enterVorname" placeholder="Update Vorname">
</div>
<div class="form-group mt-3">
  <label for="name">Update Nachname</label>
  <input type="text" value="${details[index].nachname}" class="form-control" id="newNachname" aria-describedby="enterNachname" placeholder="Update Nachname">
</div>
<div class="form-group mt-3">
  <label for="email">Update Email</label>
  <input type="email" value="${details[index].email}" class="form-control" id="newEmail" placeholder="Update User-Email">
</div>
<button type="submit" class="btn btn-primary mt-3" onclick="update(${index})">Update</button>
</div>`;
  document.getElementById("form").innerHTML = editForm;
  console.log('edit work');
}

function update(index) {
  let newVorname = document.getElementById('newVorname');
  let newNachname = document.getElementById('newNachname');
  let newEmail = document.getElementById('newEmail');
  if (newVorname.value == 0) {
    alert("Vorname ist Leer");
    return;
  }
  details[index] = {
    vorname: newVorname.value,
    nachname: newNachname.value,
    email: newEmail.value,
  };
  setData();
  table();
  document.getElementById("form").innerHTML = form;

  console.log('update work')
  console.log(details)
}

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
  row.append(`<td td > <button type="button" id="sendPutRequest" class="btn btn-warning mt-3" onclick="sendPutRequest(${user.id})">Edit</button></td>`);
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
