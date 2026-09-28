async function getActivityDetail() {
    const params = new URLSearchParams(window.location.search);
    const id = params.get('id');
    const container = document.getElementById('aktivitet-detalje');

    const response = await fetch(`/adventure/activity/${id}`);
    if (!response.ok) {
        container.innerHTML = `<p>Aktiviteten blev ikke fundet.</p>`;
        return;
    }
    const a = await response.json();

    const udstyr = a.equipmentTypes.length > 0
        ? a.equipmentTypes.map(e => `<li>${e}</li>`).join('')
        : '<li>Intet udstyr nødvendigt</li>';

    container.innerHTML = `
    <img src="${a.imageURL}" alt="${a.name}" class="detalje-billede">

    <div class="detalje-indhold">
        <div class="detalje-tekst">
            <span class="label">AKTIVITET</span>
            <h1>${a.name}</h1>
            <p>${a.description}</p>
        </div>

        <div class="fakta-boks">
            <h3>FAKTA</h3>
            <div class="fakta-linje">
                <span>Varighed</span>
                <strong>${a.durationMinutes} min</strong>
            </div>
            <div class="fakta-linje">
                <span>Aldersgrænse</span>
                <strong>${a.minAge > 0 ? a.minAge + ' år' : 'Ingen'}</strong>
            </div>
            <div class="fakta-linje">
                <span>Højdegrænse</span>
                <strong>${a.minHeight > 0 ? a.minHeight + ' cm' : 'Ingen'}</strong>
            </div>

            <h4>UDSTYR</h4>
            <ul>
                ${udstyr}
            </ul>

            <button class="book-knap">BOOK</button>
        </div>
    </div>
    `;
}

document.addEventListener('DOMContentLoaded', getActivityDetail);