


const role = sessionStorage.getItem("role");
if (!["RESERVATION", "ADMIN"].includes(role)) {
    window.location.href = "login.html";
}


document.getElementById("type").addEventListener("change", (event) => {

    const participants = document.getElementById("participants");
    participants.min = event.target.value === "WHOLE_CENTER" ? 40 : 1;
});
async function loadPrices() {

    const activities = await (await fetch("/adventure/prices/activities")).json();
    const activitySelect = document.getElementById("activity");
    activities.forEach(p => {
        activitySelect.innerHTML +=
            `<option value="${p.activity.name.toUpperCase()}">${p.activity.name} - ${p.pricePerPerson} kr./person</option>`;
    });

    // Pakker: value er pakkens id, fordi det er det, backend skal bruge
    const packages = await (await fetch("/adventure/prices/packages")).json();
    const packageSelect = document.getElementById("bookingPackage");
    packages.forEach(p => {
        packageSelect.innerHTML +=
            `<option value="${p.id}">${p.name} (${p.contents}) - ${p.pricePerPerson} kr./person</option>`;
    });
}

const form = document.getElementById("reservation-form");
const besked = document.getElementById("besked");

form.addEventListener("submit", async (event) => {
    // Stop browseren i at genindlæse siden (standardopførsel for formularer)
    event.preventDefault();


    const booking = Object.fromEntries(new FormData(form));

    // Vi laver antal om til tal
    booking.participants = Number(booking.participants);

    if (booking.activity === "") {
        booking.activity = null;
    }
    booking.bookingPackage = booking.bookingPackage === ""
        ? null
        : { id: Number(booking.bookingPackage) };

    const response = await fetch("/adventure/booking/manual", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(booking)     // objekt -> JSON-tekst
    });

    if (response.ok) {
        besked.textContent = "Reservationen er oprettet";
        form.reset();
        loadBookings();
    } else {
        const fejl = await response.json();
        besked.textContent = "Reservationen blev afvist: " + fejl.message;

    }
    besked.hidden = false;
});

async function loadBookings() {
    const bookings = await (await fetch("/adventure/booking")).json();

    document.getElementById("booking-liste").innerHTML = bookings.map(b => `
        <tr>
            <td>${new Date(b.startTime).toLocaleString("da-DK")}</td>
            <td>${b.customerName}</td>
            <td>${b.participants}</td>
            <td>${b.packageContents ?? b.activity?.name ?? "-"}</td>
            <td>${b.totalPrice.toLocaleString("da-DK")} kr.</td>
        </tr>
    `).join("");
}

loadPrices();
loadBookings();
