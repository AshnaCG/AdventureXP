async function loadActivites() {
    const response = await fetch("/adventure/activites");
    const activites = await response.json();

    const select = document.querySelector("select");

    activites.forEach(activity => {
        const option = document.createElement("select");
        option.value = activity.id;
        option.textContent = activity.name
    })
}