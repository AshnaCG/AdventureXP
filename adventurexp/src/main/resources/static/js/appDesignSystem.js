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

let selectedActivity = "";
document.querySelectorAll(".activity-card").forEach(activity => {
    activity.addEventListener("click", () => {
        selectedActivity = activity.textContent;
        console.log(selectedActivity);

        document.querySelectorAll(".activity-card").forEach(a => {
            a.classList.remove("selected");
        });

        activity.classList.add("selected");
    });
});

document.getElementById("confirm").addEventListener("click", () => {
    const date = document.getElementById("date").value;
    const time = document.getElementById("time").value;

    if (!selectedActivity) {
        alert("Please choose an activity");
        return;
    }
    if (!date) {
        alert("Please choose a date");
        return;
    }
    if (!time) {
        alert("Please choose the time");
        return;
    }

    alert("Reservation saved");

});