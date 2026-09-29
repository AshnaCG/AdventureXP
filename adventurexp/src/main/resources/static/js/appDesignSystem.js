const activities = [
    "Gokart",
    "Minigolf",
    "Paintball",
    "Sumo Wrestling"
];

const activitiesList =
    document.getElementById("activities");

activities.forEach((activity) => {
    const card = document.createElement("div");
    card.classList.add("activity-card");
    card.textContent = activity;
    activitiesList.appendChild(card);
});