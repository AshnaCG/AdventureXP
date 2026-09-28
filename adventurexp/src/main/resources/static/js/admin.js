import { requireRole, logOut } from "./auth.js";

requireRole(["ADMIN"]);

document.querySelector("#username").textContent = sessionStorage.getItem("username");
document.querySelector("#logout").addEventListener("click", logOut);