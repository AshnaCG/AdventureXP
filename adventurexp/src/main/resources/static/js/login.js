async function loginUser(credentials) {
    const response = await fetch("/adventure/login", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(credentials)
    });
    console.log("Status fra server: ", response.status);
    return response;
}

const loginform = document.querySelector(".login-form");
const loginError = document.querySelector("#login-error");

loginform.addEventListener("submit", async (event) => {
    event.preventDefault();

    const formData = new FormData(loginform);
    const username = formData.get("username");
    const password = formData.get("password");
    const credentials = {username, password};

    try {
        const response = await loginUser(credentials);

        if (response.status === 401) {
            loginError.textContent = "Forkert brugernavn eller adgangskode";
            loginError.hidden = false;
            return;
        }
        if (!response.ok) {
            loginError.textContent = "Noget gik galt. Prøv igen";
            loginError.hidden = false;
            return;
        }

        const data = await response.json();
        
        loginError.hidden = true;

        sessionStorage.setItem("username", data.username);
        sessionStorage.setItem("role", data.role);

        if (data.role === "ADMIN") {
            window.location.href = "admin.html"
        }
        if (data.role === "EMPLOYEE") {
            window.location.href = "employee.html";
        }

    } catch (error) {
        loginError.textContent = "Kunne ikke kontakte serveren";
        loginError.hidden = false;
    }
});