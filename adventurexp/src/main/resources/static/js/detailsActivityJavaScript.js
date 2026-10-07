document.addEventListener("DOMContentLoaded", getActivityDetail);

async function getActivityDetail() {
    const id = new URLSearchParams(window.location.search).get("id");

    if (!id) {
        const errorElement = document.querySelector("#activity-error");
        errorElement.textContent = "Ingen aktivitet valgt.";
        errorElement.hidden = false;
        return;
    }

    try {
        const response = await fetch(`/adventure/activity/${id}`);
        if (!response.ok) {
            const errorElement = document.querySelector("#activity-error");
            errorElement.textContent = "Aktiviteten blev ikke fundet.";
            errorElement.hidden = false;
            return;
        }
        const activity = await response.json();
        displayActivity(activity);
    } catch (error) {
        console.log(error);
        const errorElement = document.querySelector("#activity-error");
        errorElement.textContent = "Kunne ikke kontakte serveren.";
        errorElement.hidden = false;
    }
}

function displayActivity(activity) {
    const image = document.querySelector("#activity-image");
    image.src = activity.imageURL;
    image.alt = activity.name;

    document.querySelector("#activity-name").textContent = activity.name;
    document.querySelector("#activity-description").textContent = activity.description;
    document.querySelector("#activity-duration").textContent = `${activity.durationMinutes} min`;
    document.querySelector("#activity-age").textContent = activity.minAge > 0 ? `${activity.minAge} år` : "Ingen";
    document.querySelector("#activity-height").textContent = activity.minHeight > 0 ? `${activity.minHeight} cm` : "Ingen";

    const list = document.querySelector("#activity-equipment");
    const equipment = activity.equipmentTypes || [];

    if (equipment.length === 0) {
        const li = document.createElement("li");
        li.textContent = "Intet udstyr nødvendigt";
        list.appendChild(li);
    } else {
        for (const item of equipment) {
            const li = document.createElement("li");
            li.textContent = item;
            list.appendChild(li);
        }
    }
    document.querySelector("#activity-detail").hidden = false;
    createAgeForm(activity);
}

function createAgeForm(activity) {
    const container = document.querySelector("#activity-age-form");
    const form = document.createElement("form");
    const input = document.createElement("input");
    input.type = "number";
    input.min = "0";
    input.value = activity.minAge;

    const label = document.createElement("label");
    label.textContent = "Ny Aldersgrænse";
    label.htmlFor = "new-age";

    input.id = "new-age";

    const button = document.createElement("button");
    button.type = "submit";
    button.textContent = "opdater aldersgrænse";

    form.appendChild(label);
    form.appendChild(input);
    form.appendChild(button);

    container.appendChild(form);

    form.addEventListener("submit", function (event) {
        event.preventDefault();

        const newAge = Number(input.value);

        updateAge(activity, newAge)
    });
    async function updateAge(activity, newAge) {
        const id = new URLSearchParams(window.location.search).get("id");

        try {

            const updatedActivity = {
                ...activity,
                minAge: newAge
            };

            const response = await fetch(`/adventure/activity/id/${name}`, {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(updatedActivity)
            });

            if (!response.ok) {
                console.log("kunne ikke opdatere aldersgrænsen");
                return;
            }

            const updated = await response.json();

            document.querySelector("#activity-age").textContent =
                updated.minAge > 0
                    ? `${updated.minAge} år`
                    : "Ingen";

        } catch (error) {
            console.log(error);
        }
    }

}