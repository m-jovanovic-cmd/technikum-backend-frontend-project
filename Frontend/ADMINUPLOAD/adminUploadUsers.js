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


