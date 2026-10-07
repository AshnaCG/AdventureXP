async function cancelReservation(id) {
    const response = await fetch(`/adventure/reservation/${id}/cancel`,{
        method: 'PUT',
    })
    return response;
}

const cancelForm = document.querySelector('#cancelForm');

const message = document.createElement("p");
message.hidden = true;
cancelForm.append(message);

cancelForm.addEventListener("submit", async (event) => {
    event.preventDefault();
    const formData = new FormData(cancelForm);
    const id = formData.get('reservationId');

    try {
        const response = await cancelReservation(id);

        if (response.ok) {
            message.textContent = "Din reservation er blevet aflyst.";
        } else if (response.status === 400) {
            message.textContent = "Reservationen kan ikke aflyses, da der er under 24 timer til. Du modtager et girokort på det fulde beløb.";
        } else if (response.status === 404) {
            message.textContent = "Reservationen findes ikke"
        } else {
            message.textContent = "Indlæsningen fejlede. Prøv igen"
        }
            message.hidden = false;
    } catch (error) {
            message.textContent = "Serveren fejlede"
            message.hidden = false;
    }

});