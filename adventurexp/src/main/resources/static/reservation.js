
/*
  sessionStorage er en lille "hukommelse" i browseren, der holder, så længe fanen er åben.
  login.js gemte rollen dér. Er brugeren ikke RESERVATION eller ADMIN,
  sender vi dem tilbage til login.
  includes() tjekker, om en værdi findes i arrayet. */

const role = sessionStorage.getItem("role");
if (!["RESERVATION", "ADMIN"].includes(role)) {
    window.location.href = "login.html";
}

/*
 addEventListener("change", ...) kører funktionen, hver gang brugeren vælger noget nyt.
 event.target er det element, der blev ændret (her <select id="type">).
 ? : er en kort if/else: betingelse ? værdi-hvis-sand : værdi-hvis-falsk */
document.getElementById("type").addEventListener("change", (event) => {

    const participants = document.getElementById("participants");
    participants.min = event.target.value === "WHOLE_CENTER" ? 40 : 1;
});

const form = document.getElementById("reservation-form");
const besked = document.getElementById("besked");

form.addEventListener("submit", async (event) => {
    // Stop browseren i at genindlæse siden (standardopførsel for formularer)
    event.preventDefault();

    /*
     * FormData er en indbygget browser-klasse, der samler alle felter i formularen.
     * Object.fromEntries laver den om til et almindeligt objekt:
     *   { type: "WHOLE_CENTER", participants: "45", customerName: "..." }
     * Nøglerne kommer fra name-attributterne i HTML'en.
     */
    const booking = Object.fromEntries(new FormData(form));

    // Inputfelter giver altid tekst, så vi laver antal om til et rigtigt tal
    booking.participants = Number(booking.participants);

    // Tom aktivitet ("") skal sendes som null, ellers kan Spring ikke læse den som enum
    if (booking.activity === "") {
        booking.activity = null;
    }

    const response = await fetch("/adventure/booking/manual", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(booking)     // objekt -> JSON-tekst
    });

    if (response.ok) {
        besked.textContent = "Reservationen er oprettet";
        form.reset();
    } else {
        besked.textContent = "Reservationen blev afvist. Tjek antal personer og aktivitet";
    }
    besked.hidden = false;
});