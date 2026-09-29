document.addEventListener('DOMContentLoaded', getActivities);

async function getActivities() {
    try {
        const response = await fetch('/adventure/activity');
        if (!response.ok) {
            const errorElement = document.querySelector("#activities-error");
            errorElement.textContent = "Aktiviteterne kunne ikke hentes. Prøv igen senere.";
            errorElement.hidden = false;
            return;
        }
        const activities = await response.json();

        const container = document.querySelector("#aktivitet-liste");
        container.innerHTML = "";

        for (const activity of activities) {
            container.appendChild(createActivityCard(activity));
        }
    } catch (error) {
        console.error(error);
        const errorElement = document.querySelector("#activities-error");
        errorElement.textContent = "Kunne ikke kontakte serveren.";
        errorElement.hidden = false;
    }
}

function createActivityCard(activity) {
    const card = document.createElement("a");
    card.className = "aktivitet-kort";
    card.href = `detailsActivity.html?id=${activity.name}`;

    const image = document.createElement("img");
    image.src = activity.imageURL;
    image.alt = activity.name;

    const info = document.createElement("div");
    info.className = "kort-info";

    const title = document.createElement("h3");
    title.textContent = activity.name;

    const badge = document.createElement("span");
    badge.className = "badge";
    badge.textContent = `${activity.minAge}+ år`;

    const details = document.createElement("p");
    details.textContent = `${activity.durationMinutes} min · `;

    const seeMore = document.createElement("span");
    seeMore.className = "se-mere";
    seeMore.textContent = "se mere →";
    details.appendChild(seeMore);

    info.append(title, badge, details);
    card.append(image, info);

    return card;
}