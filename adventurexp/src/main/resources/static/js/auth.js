export function logOut() {
    sessionStorage.clear();
    window.location.href = "login.html";
}

export function requireRole(allowedRoles) {
    const role = sessionStorage.getItem("role");
    if (!allowedRoles.includes(role)) {
        window.location.href = "login.html";
    }
}

export function showByRole() {
    const role = sessionStorage.getItem("role");
    const elements = document.querySelectorAll("[data-role]");

    for (const element of elements) {
        if (element.getAttribute("data-role") === role) {
            element.hidden = false;
        } else {
            element.hidden = true;
        }
    }
}