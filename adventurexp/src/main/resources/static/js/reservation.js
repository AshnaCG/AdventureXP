async function loadActivites() {
    const response = await fetch("/adventure/activity");
    const activites = await response.json();

    const select = document.querySelector("select");

    activites.forEach(activity => {
        const option = document.createElement("option");
        option.value = activity.name.toUpperCase();
        option.textContent = activity.name;

        select.appendChild(option);
    });
}

loadActivites();

async function createReservation(reservation) {
    const response = await fetch("/adventure/booking/online", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(reservation),
    });
    return response;
}

const reservationsForm = document.querySelector("#reservationsForm");

const message = document.createElement("p");
message.hidden = true;
reservationsForm.append(message);

reservationsForm.addEventListener("submit", async (event) => {
    event.preventDefault();

    const formData = new FormData(reservationsForm);

    const reservation = {
        activity: formData.get("activity"),
        startTime: formData.get("dateTime"),
        customerName: `${formData.get("firstName")} ${formData.get("lastName")}`,
        customerEmail: formData.get("email"),
        customerPhoneNumber: formData.get("phoneNumber"),
        participants: Number(formData.get("participants")),
    };
    console.log("Sender:", reservation);

    try {
        const response = await createReservation(reservation);

        if (response.ok) {
            const booking = await response.json();
            message.textContent = `Tak! Dit reservationsnummer er ${booking.id}.`;
            message.hidden = false;
            reservationsForm.reset();
        } else {
            message.textContent = "Reservationen kunne ikke gennemføres. Prøv igen";
            message.hidden = false;
            console.log("Status fra server: ", response.status);
        }
    } catch (error) {
        // server svarede ikke
        message.textContent = "Kunne ikke kontakte serveren";
        message.hidden = false;
        console.log("Kunne ikke kontakte serveren:", error);
    }
});