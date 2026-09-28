import { requireRole, logOut, showByRole } from "./auth.js";

requireRole(["EMPLOYEE", "ADMIN"]);
showByRole();

document.querySelector("#username").textContent = sessionStorage.getItem("username");
document.querySelector("#logout").addEventListener("click", logOut);