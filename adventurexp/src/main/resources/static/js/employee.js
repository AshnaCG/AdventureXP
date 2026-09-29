import { requireRole, logOut, showByRole } from "./auth.js";

requireRole(["EMPLOYEE", "ADMIN"]);
showByRole();

document.querySelector("#username").textContent = sessionStorage.getItem("username");
document.querySelector("#logout").addEventListener("click", logOut);

const ACTIVITY_URL = "/adventure/activity";

const form = document.querySelector("#aktivitet-form");
const formTitle = document.querySelector("#aktivitet-form-titel");
const saveButton = document.querySelector("#aktivitet-gem");
const cancelButton = document.querySelector("#aktivitet-annuller");
const message = document.querySelector("#aktivitet-besked");

const fields = {
    id: document.querySelector("#aktivitet-id"),
    name: document.querySelector("#aktivitet-navn"),
    description: document.querySelector("#aktivitet-beskrivelse"),
    imageURL: document.querySelector("#aktivitet-billede"),
    durationMinutes: document.querySelector("#aktivitet-varighed"),
    minAge: document.querySelector("#aktivitet-alder"),
    minHeight: document.querySelector("#aktivitet-hoejde"),
};

// ---------- Aktiviteter ----------

async function loadActivities() {
    const response = await fetch(ACTIVITY_URL);
    const activities = await response.json();

    const tbody = document.querySelector("#aktivitet-tabel tbody");
    tbody.replaceChildren();

    for (const a of activities) {
        const row = document.createElement("tr");
        for (const value of [a.name, a.durationMinutes, a.minAge, a.minHeight]) {
            const cell = document.createElement("td");
            cell.textContent = value;
            row.appendChild(cell);
        }

        const actions = document.createElement("td");

        const editButton = document.createElement("button");
        editButton.textContent = "Rediger";
        editButton.addEventListener("click", () => startEdit(a));

        const deleteButton = document.createElement("button");
        deleteButton.textContent = "Slet";
        deleteButton.addEventListener("click", () => deleteActivity(a));

        actions.append(editButton, " ", deleteButton);
        row.appendChild(actions);
        tbody.appendChild(row);
    }
}

function startEdit(activity) {
    fields.id.value = activity.id;
    fields.name.value = activity.name;
    fields.description.value = activity.description ?? "";
    fields.imageURL.value = activity.imageURL ?? "";
    fields.durationMinutes.value = activity.durationMinutes;
    fields.minAge.value = activity.minAge;
    fields.minHeight.value = activity.minHeight;

    formTitle.textContent = `Rediger aktivitet: ${activity.name}`;
    saveButton.textContent = "Gem ændringer";
    cancelButton.hidden = false;
    message.textContent = "";
    fields.name.focus();
}

function resetForm() {
    form.reset();
    fields.id.value = "";
    formTitle.textContent = "Opret aktivitet";
    saveButton.textContent = "Opret";
    cancelButton.hidden = true;
}

async function saveActivity(event) {
    event.preventDefault();

    const id = fields.id.value;
    const activity = {
        name: fields.name.value.trim(),
        description: fields.description.value,
        imageURL: fields.imageURL.value.trim(),
        durationMinutes: Number(fields.durationMinutes.value),
        minAge: Number(fields.minAge.value),
        minHeight: Number(fields.minHeight.value),
    };

    const response = await fetch(id ? `${ACTIVITY_URL}/${id}` : ACTIVITY_URL, {
        method: id ? "PUT" : "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(activity),
    });

    if (!response.ok) {
        message.textContent = await errorText(response);
        return;
    }

    message.textContent = id ? `"${activity.name}" er opdateret.` : `"${activity.name}" er oprettet.`;
    resetForm();
    await loadActivities();
    await loadEquipmentOverview();
}

async function deleteActivity(activity) {
    if (!confirm(`Er du sikker på, at du vil slette "${activity.name}"?`)) {
        return;
    }

    const response = await fetch(`${ACTIVITY_URL}/${activity.id}`, { method: "DELETE" });
    if (!response.ok) {
        message.textContent = await errorText(response);
        return;
    }

    if (fields.id.value === String(activity.id)) {
        resetForm();
    }
    message.textContent = `"${activity.name}" er slettet.`;
    await loadActivities();
    await loadEquipmentOverview();
}

async function errorText(response) {
    try {
        const body = await response.json();
        return body.message || `Noget gik galt (${response.status})`;
    } catch {
        return `Noget gik galt (${response.status})`;
    }
}

form.addEventListener("submit", saveActivity);
cancelButton.addEventListener("click", () => {
    resetForm();
    message.textContent = "";
});

// ---------- Udstyr ----------

async function loadEquipmentOverview() {
    const response = await fetch("/adventure/equipment/overview");
    const rows = await response.json();

    const tbody = document.querySelector("#udstyr-tabel tbody");
    tbody.replaceChildren();

    for (const r of rows) {
        const row = document.createElement("tr");
        for (const value of [r.activity, r.equipment, r.total, r.available, r.broken]) {
            const cell = document.createElement("td");
            cell.textContent = value;
            row.appendChild(cell);
        }
        tbody.appendChild(row);
    }
}

loadActivities();
loadEquipmentOverview();
