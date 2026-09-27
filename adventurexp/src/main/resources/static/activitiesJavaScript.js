async function getActivities() {
    const response = await fetch('/adventure/activity');
    const activities = await response.json();

    const container = document.getElementById('aktivitet-liste');
    container.innerHTML = '';

    activities.forEach(a => {
        const card = document.createElement('div');
        card.className = 'aktivitet-kort';
        card.innerHTML = `
            <img src="${a.imageURL}" alt="${a.name}">
            <div class="kort-info">
            <h3>${a.name}</h3>
            <span class="badge">${a.ageLimit}+ år</span>
            <p>${a.durationMinutes} min · <a href="detalje.html?id=${a.name}">Se mere →</a></p>
            </div>
        `;
        container.appendChild(card);
    });
}

document.addEventListener('DOMContentLoaded', getActivities);