var form = `<div class="container d-flex justify-content-center">
    <div class="border p-5 rounded">
    <div class="mt-3">
        <h2 class="px-0 mx-0">Create User:in
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
    <button type="submit" class="btn btn-primary mt-3" onclick="save()">Save</button>
</div>
</div>`
document.getElementById("form").innerHTML = form;

$.get({
    url: "http://localhost:8080/api/users",
    cors: true,
    headers: {},
    success: (users) => {
        displayAllUsers(users)
    },
    error: console.error
});

function createUserDisplay(user) {

    const content = $(`<table class="table" >
          <thead>
            <tr>
              <th scope="col">ID</th>
              <th scope="col">isAdmin</th>   
              <th scope="col">Email</th>
              <th scope="col">Vorname</th>
              <th scope="col">Gender</th>
              <th scope="col">Nachname</th>
              <th scope="col">Wohnort</th>
              <th scope="col">Passwort</th>
              <th scope="col">PLZ</th>
              <th scope="col">Role</th>
              <th scope="col">Straße</th>
              <th scope="col">Top</th>
              <th scope="col">Username</th>
              <th scope="col">Edit</th>
              <th scope="col">Delete</th>
            </tr>
          </thead>
          <tbody>
           `);
    return content;

};

function displayAllUsers(users) {
    for (let i = 0; i < users.length; i++) {
        createUserDisplay(users[i])
    };


    function createUserDisplay(user) {
        const content = $(table = table + `<tr>
    <td scope="row">${i + 1}</td>
    <td>${user.id}</td>
    <td>${details[i].nachname}</td>
    <td>${details[i].email}</td>
    <td><button type="button" class="btn btn-warning mt-3" onclick="edit(${i})">Edit</button>
    </td>
    <td><button type="button" class="btn btn-danger mt-3" onclick="deleteData(${i})">Delete</button></td>
    </tr>
</tbody>
  `
        )
    };
    table = table +

        `</table>;
    document.getElementById("table").innerHTML = table`
}