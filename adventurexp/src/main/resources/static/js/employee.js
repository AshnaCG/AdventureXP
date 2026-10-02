document.querySelector("#username").textContent = sessionStorage.getItem("username");
document.querySelector("#logout").addEventListener("click", logOut);

requireLogin();

function logOut() {
    sessionStorage.clear();
    window.location.href = "login.html";
}

function requireLogin() {
    if (sessionStorage.getItem("role") === null) {
        window.location.href = "login.html";
    }
}