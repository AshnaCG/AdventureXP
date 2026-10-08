const BASE_URL = "/adventure/activity";

async function request(url, options = {}) {
    const response = await fetch(url, options);

    if (!response.ok) {
        const error = new Error(`Request failed: ${response.status}`);
        error.status = response.status;
        throw error;
    }

    const text = await response.text();
    if (text == "") {
        return null;
    }
    return JSON.parse(text);
}

export function fetchActivities() {
    return request(BASE_URL);
}

export function createActivity(activity) {
    return request(BASE_URL, {
        method: "POST",
        headers: { "Content-Type": "application/json"},
        body: JSON.stringify(activity),
    });
}

export function updateActivity(id, updatedActivity) {
    return request(`${BASE_URL}/id/${id}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json"},
        body: JSON.stringify(updatedActivity),
    });
}

export async function deleteActivity(id) {
    await request(`${BASE_URL}/del/${id}`, {
        method: "DELETE"
    });
    return true;
}
