const BASE_URL = "/adventure/users";

export async function fetchUsers () {
    try {
        const response = await fetch(BASE_URL);
        if (!response.ok) {
            throw new Error(`Failed to fetch users: ${response.status}`);
        }
        return await response.json();
    } catch (error) {
        console.error(error);
        return [];
    }
}

export async function createUser(user) {
    try {
        const response = await fetch(BASE_URL, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(user),
        });
         if (!response.ok) {
             throw new Error(`Failed to create user: ${response.status}`);
         }
         return await response.json();
    } catch (error) {
        console.error(error);
    }
}

export async function updateUser(id, updatedUser) {
    try {
        const response = await fetch(`${BASE_URL}/${id}`, {
            method: "PUT",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(updatedUser),
        });
        if (!response.ok) {
            throw new Error(`Failed to update user: ${response.status}`);
        }
        return await response.json();
    } catch (error) {
        console.error(error);
    }
}

export async function deleteUser(id) {
    try {
        const response = await fetch(`${BASE_URL}/${id}`, {
            method: "DELETE",
        });
        if (!response.ok) {
            throw new Error(`Failed to delete user: ${response.status}`);
        }
        return true;
    } catch (error) {
        console.error(error);
        return false;
    }
}