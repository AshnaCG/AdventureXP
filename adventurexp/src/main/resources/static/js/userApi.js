const BASE_URL = "/adventure/users";

async function request(url, options = {}) {
    const response = await fetch(url, options);

    if (!response.ok) {
        const error = new Error(`Request failed: ${response.status}`);
        error.status = response.status;
        throw error;
    }

    if (response.status === 204) {
        return null;
    }
    return response.json();
}

export function fetchUsers() {
    return request(BASE_URL);
}

export function createUser(user) {
    return request(BASE_URL, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(user),
    });
}

export function updateUser(id, updatedUser) {
    return request(`${BASE_URL}/${id}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(updatedUser),
    });
}

export async function deleteUser(id) {
    await request(`${BASE_URL}/${id}`, { method: "DELETE" });
    return true;
}