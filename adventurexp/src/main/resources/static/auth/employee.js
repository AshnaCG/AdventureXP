import { requireRole, logOut, showByRole } from "./auth.js";

requireRole(["EMPLOYEE", "ADMIN"]);
showByRole();

document.querySelector("#username").textContent = sessionStorage.getItem("username");
document.querySelector("#logout").addEventListener("click", logOut);

async function loadEquipmentOverview() {
    const response = await fetch("/adventure/equipment/overview");
    const rows = await response.json();

    document.querySelector("#udstyr-tabel tbody").innerHTML = rows.map(r => `
        <tr>
            <td>${r.activity}</td>
            <td>${r.equipment}</td>
            <td>${r.total}</td>
            <td>${r.available}</td>
            <td>${r.broken}</td>
        </tr>`).join("");
}

loadEquipmentOverview();