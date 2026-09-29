document.addEventListener("DOMContentLoaded", getActivityDetail);

async function getActivityDetail() {
    const id = new URLSearchParams(window.location.search).get("id");

    if(!id){
        const errorElement = document.querySelector("#activity-error");
        errorElement.textContent = "Ingen aktivitet valgt.";
        errorElement.hidden = false;
        return;
    }

    try {
        const response = await fetch(`/adventure/activity/${id}`);
        if(!response.ok){
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

function displayActivity(activity){
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

    if(equipment.length === 0){
        const li = document.createElement("li");
        li.textContent = "Intet udstyr nødvendigt";
        list.appendChild(li);
    } else {
        for (const item of equipment){
            const li = document.createElement("li");
            li.textContent = item;
            list.appendChild(li);
        }
    }
    document.querySelector("#activity-detail").hidden = false;
}