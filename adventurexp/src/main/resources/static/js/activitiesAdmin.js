import {requireRole, logOut, showByRole} from "./auth.js";
import {fetchActivities, createActivity, updateActivity, deleteActivity} from "./activityApi.js";

const activityError = document.querySelector("#activity-error");
const activitySucces = document.querySelector("#activity-success");
const activityTableBody = document.querySelector("#activity-table-body");

const activityForm = document.querySelector("#activity-form");
const formTitle = document.querySelector("#form-title");
const activityIdInput = document.querySelector("#activity-id");
const nameInput = document.querySelector("#activity-name");
const descriptionInput = document.querySelector("#activity-description");
const imageInput = document.querySelector("#activity-image");
const durationInput = document.querySelector("#activity-duration");
const minAgeInput = document.querySelector("#activity-min-age");
const minHeightInput = document.querySelector("#activity-min-height");
const formSubmit = document.querySelector("#form-submit");
const formCancel = document.querySelector("#form-cancel");

function showError (message) {
    activitySucces.hidden = true;
    activityError.textContent = message;
    activityError.hidden = false;
}

function showSuccess (message) {
    activityError.hidden = true;
    activitySucces.textContent = message;
    activitySucces.hidden = false;
}

function loadErrorMessage (error) {
    switch (error.status) {
        case 500:
            return "Der er sket en fejl på serveren. prøv igen senere";
        default:
            return "Aktiviteterne kunne ikke hentes"
    }
}

function saveErrorMessage (error) {
    switch (error.status) {
        case 400:
            return "Aktiviteten kunne ikke gemmes. Tjek at alle felter er udfyldt korrekt";
        case 404:
            return "Aktiviten findes ikke længere";
        case 500:
            return "Der er sket en fejl på serveren. prøv igen senere";
        default:
            return "Aktiviteten kunne ikke gemmes";
    }
}

function deleteErrorMessage (error) {
    switch (error.status) {
        case 404:
            return "Aktiviteten findes ikke længere";
        case 500:
            return "Der er sket en fejl på serveren. Prøv igen senere";
        default:
            return "Aktiviteten kunne ikke slettes";
    }
}

function clearMessage () {
    activitySucces.hidden = true;
    activitySucces.textContent = "";

    activityError.hidden = true;
    activityError.textContent = "";
}

function createActivityRow (activity) {
    const row = document.createElement("tr");
    row.setAttribute("data-id", activity.id);

    const nameCell = document.createElement("td");
    nameCell.textContent = activity.name;

    const durationCell = document.createElement("td");
    durationCell.textContent = `${activity.durationMinutes} min`;

    const ageCell = document.createElement("td");
    ageCell.textContent = `${activity.minAge} år`;

    const heightCell = document.createElement("td");
    heightCell.textContent = activity.minHeight > 0 ? `${activity.minHeight} cm` : "Ingen";

    const actionCell = document.createElement("td");

    const editButton = document.createElement("button");
    editButton.type = "button";
    editButton.className = "edit-button";
    editButton.textContent = "Rediger";
    editButton.addEventListener("click", () => handleEdit(activity));

    const deleteButton = document.createElement("button");
    deleteButton.type = "button";
    deleteButton.className = "delete-button";
    deleteButton.textContent = "Slet";
    deleteButton.addEventListener("click", () => handleDelete(activity));

    actionCell.append(editButton, deleteButton);

    row.append(nameCell, durationCell, ageCell, heightCell, actionCell);

    return row;
}

function handleEdit (activity) {
    clearMessage();
    activityIdInput.value = activity.id;
    nameInput.value = activity.name;
    descriptionInput.value = activity.description ?? "";
    imageInput.value = activity.imageURL ?? "";
    durationInput.value = activity.durationMinutes;
    minAgeInput.value = activity.minAge;
    minHeightInput.value = activity.minHeight;
    formTitle.textContent = `Rediger ${activity.name}`;
    formSubmit.textContent = "Opdater";
    formCancel.hidden = false;
}

function resetForm () {
    activityForm.reset();
    activityIdInput.value = "";
    formTitle.textContent = "Opret aktivitet";
    formSubmit.textContent = "Opret";
    formCancel.hidden = true;
}

async function handleDelete (activity) {
    clearMessage();
    if (!confirm('Er du sikker på, at du vil slette aktiviteten "${activity.name}"?')) {
        return;
    }
    try {
        await deleteActivity(activity.id);

        if (activityIdInput.value === String(activity.id)) {
            resetForm();
        }
        showSuccess("Aktiviteten er slettet");
        loadActivites();
    } catch (error) {
        console.error(error);
        showError(deleteErrorMessage(error));
    }
}

function handleCancel () {
    clearMessage();
    resetForm();
}

async function loadActivities () {
    try {
        const activities = await fetchActivities();
        renderActivities(activities);
    } catch (error) {
        console.error(error);
        showError(loadErrorMessage(error));
    }
}

function renderActivities (activities) {
    activityTableBody.replaceChildren();
    for (const activity of activities) {
        activityTableBody.append(createActivityRow(activity));
    }
}

async function handleSubmit (event) {
    event.preventDefault();
    clearMessage();

    const id = activityIdInput.value;
    const activity =
        {
            name : nameInput.value,
            description : descriptionInput.value,
            imageURL : imageInput.value,
            durationMinutes : Number(durationInput.value),
            minAge : Number(minAgeInput.value),
            minHeight : Number(minHeightInput.value),
        };

    try {
        if (id) {
            await updateActivity(id, activity);
            showSuccess("Aktiviteten er opdateret");
        } else {
            await createActivity(activity);
            showSuccess("Aktiviteten er oprettet");
        }
        resetForm();
        await loadActivities();
    } catch (error) {
        console.error(error);
        showError(saveErrorMessage(error));
    }
}

function init () {
    if (requireRole(["EMPLOYEE", "ADMIN"]) === false) {
        return;
    }
    showByRole();

    document.querySelector("#username").textContent = sessionStorage.getItem("username");
    document.querySelector("#logout").addEventListener("click", logOut);

    activityForm.addEventListener("submit", handleSubmit);
    formCancel.addEventListener("click", handleCancel);
    loadActivities();
}

init();
