import {requireRole, logOut, showByRole} from "./auth.js";

requireRole(["EMPLOYEE", "ADMIN"]);
showByRole();

document.querySelector("#username").textContent = sessionStorage.getItem("username");
document.querySelector("#logout").addEventListener("click", logOut);

async function loadEquipmentOverview() {
    const tableBody = document.querySelector("#udstyr-tabel tbody");

    try {
        const response = await fetch("/adventure/equipment/overview");
        if (!response.ok) {
            const errorElement = document.querySelector("#equipment-error");
            errorElement.textContent = "Udstyret kunne ikke hentes. Prøv igen senere.";
            errorElement.hidden = false;
            return;
        }
        const rows = await response.json();

        tableBody.innerHTML = "";

        for (const row of rows){
            const tr = document.createElement("tr");

            const values = [row.activity, row.equipment, row.total, row.available, row.broken];
            for (const value of values) {
                const td = document.createElement("td");
                td.textContent = value;
                tr.appendChild(td);
            }

            tableBody.appendChild(tr);
        }
    } catch (error) {
        console.error(error);
        const errorElement = document.querySelector("#equipment-error");
        errorElement.textContent = "Kunne ikke kontakte serveren.";
        errorElement.hidden = false;
    }
}

loadEquipmentOverview();