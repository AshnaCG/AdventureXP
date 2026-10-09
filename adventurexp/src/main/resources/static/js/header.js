import { logOut } from "./auth.js";

const username = sessionStorage.getItem("username");
const role = username ? sessionStorage.getItem("role") : "GUEST";

for (const link of document.querySelectorAll(".site-header [data-roles]")) {
    const roles = link.dataset.roles.split(" ");
    link.hidden = !roles.includes(role);
}

if (username) {
    document.querySelector("#header-username").textContent = username;
    document.querySelector("#header-logout").addEventListener("click", logOut);
    document.querySelector(".user-info").hidden = false;
}

for (const link of document.querySelectorAll(".main-nav a")) {
    if (link.href === location.href) {
        link.classList.add("active");
    }
}