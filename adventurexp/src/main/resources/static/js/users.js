import {requireRole} from "./auth.js";
import {fetchUsers, deleteUser, updateUser, createUser} from "./userApi.js";

const userError = document.querySelector("#user-error");
const userSuccess = document.querySelector("#user-success");
const userTableBody = document.querySelector("#user-table-body");

const userForm = document.querySelector("#user-form");
const userIdInput = document.querySelector("#user-id");
const usernameInput = document.querySelector("#user-username");
const passwordInput = document.querySelector("#user-password");
const roleSelect = document.querySelector("#user-role");
const formSubmit = document.querySelector("#form-submit");
const formCancel = document.querySelector("#form-cancel");

function showError (message) {
    userSuccess.hidden=true;
    userError.textContent=message;
    userError.hidden=false;
}

function showSucces (message) {
    userError.hidden=true;
    userSuccess.textContent=message;
    userSuccess.hidden=false;
}

function clearMessage () {
    userSuccess.hidden=true;
    userSuccess.textContent="";

    userError.hidden=true;
    userError.textContent="";
}

function createUserRow (user) {
    const row = document.createElement("tr");
    row.setAttribute("data-id", user.id);

    const usernameCell = document.createElement("td");
    usernameCell.textContent=user.username;

    const roleCell = document.createElement("td");
    roleCell.textContent=user.role;

    const actionCell = document.createElement("td");

    const editButton = document.createElement("button");
    editButton.type="button";
    editButton.textContent="Rediger";
    editButton.addEventListener("click", () => handleEdit(user));

    const deleteButton = document.createElement("button");
    deleteButton.type="button";
    deleteButton.textContent="Slet";
    deleteButton.addEventListener("click", () => handleDelete(user));

    actionCell.append(editButton, deleteButton);

    row.append(usernameCell, roleCell, actionCell);

    return row;
}

function handleEdit(user) {
    clearMessage();
    userIdInput.value = user.id;
    usernameInput.value = user.username;
    passwordInput.value = "";
    roleSelect.value = user.role;
    formSubmit.textContent = "Opdater";
    formCancel.hidden = false;
}

function resetForm () {
    userForm.reset();
    userIdInput.value = "";
    formSubmit.textContent = "Gem";
    formCancel.hidden = true;
}

async function handleDelete (user) {
    clearMessage();
    if (!confirm(`Er du sikker på, at du vil slette brugeren "${user.username}"?`)) {
        return;
    }
    try {
        await deleteUser(user.id);

        if (userIdInput.value === String(user.id)){
            resetForm();
        }
        showSucces("Brugeren er slettet");
        loadUsers();
    } catch (error) {
        console.error(error);
        showError("Brugeren blev ikke slettet");
    }
}

function handleCancel () {
    clearMessage();
    resetForm();
}

async function loadUsers () {
    try {
        const users = await fetchUsers();
        renderUsers(users);
    } catch (error) {
        console.error(error);
        showError("Kunne ikke hente users");
    }
}

function renderUsers (users) {
    userTableBody.replaceChildren();
    for (const user of users) {
        userTableBody.append(createUserRow(user));
    }
}



async function handleSubmit (event) {
    event.preventDefault();
    clearMessage();

    const id = userIdInput.value;
    const user =
        {
            username : usernameInput.value,
            password : passwordInput.value,
            role : roleSelect.value,
        };

    if (!id && user.password === "") {
        showError("Adgangskode er påkrævet ved oprettelse");
        return;
    }
    try {
        if (id) {
            await updateUser(id, user);
            showSucces("Brugeren er opdateret");
        } else {
            await createUser(user);
            showSucces("Brugeren er oprettet");
        }
        resetForm();
        await loadUsers();
    } catch (error) {
        console.error(error);
        showError("Brugeren kunne ikke gemmes");
    }
}

function init () {
    userForm.addEventListener("submit", handleSubmit);
    formCancel.addEventListener("click", handleCancel);
    loadUsers();
}


init();
