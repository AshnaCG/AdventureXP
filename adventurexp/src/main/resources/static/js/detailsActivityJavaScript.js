async function getActityDetail() {
    const params = new URLSearchParams(window.location.search);
    const id = params.get('id');

    const response = await fetch(`/adventure/activity/${id}`);
    const a = await response.json();

    const container = document.getElementById('aktivitet-detalje');
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
            <strong>${a.ageLimit} år</strong>
        </div>
        <div class="fakta-linje">
            <span>Højdegrænse</span>
            <strong>${a.heightLimit} cm</strong>
        </div>
        
        <h4>UDSTYR</h4>
        <ul>
            ${a.equipment.split(', ').map(e => `<li>${e}</li>`).join('')}
        </ul>
        
        <button class="book-knap">BOOK</button>
       </div>
    </div>
`;
}

document.addEventListener('DOMContentLoaded', getActityDetail);