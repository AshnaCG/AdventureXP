import {requireRole, logOut, showByRole} from "./auth.js";

requireRole(["EMPLOYEE", "ADMIN"]);
showByRole();

document.querySelector("#username").textContent = sessionStorage.getItem("username");
document.querySelector("#logout").addEventListener("click", logOut);



//Equipment overview


async function loadEquipmentOverview() {
    const tableBody = document.querySelector("#equipment-table tbody");

    try {
        const response = await fetch("/adventure/equipment/overview");
        if (!response.ok) {
            const errorElement = document.querySelector("#equipment-error");
            errorElement.textContent = "Udstyret kunne ikke hentes. Prøv igen senere.";
            errorElement.hidden = false;
            return;
        }
        const rows = await response.json();

        tableBody.replaceChildren();

        for (const row of rows){
            const tr = document.createElement("tr");

            const values = [row.activity, row.equipment, row.total, row.available, row.broken];
            for (const value of values) {
                const td = document.createElement("td");
                td.textContent = value;
                tr.appendChild(td);
            }

            tableBody.appendChild(tr);
        }
    } catch (error) {
        console.error(error);
        const errorElement = document.querySelector("#equipment-error");
        errorElement.textContent = "Kunne ikke kontakte serveren.";
        errorElement.hidden = false;
    }
}

loadEquipmentOverview();


//Calendar


const scheduleSection = document.body.querySelector("#schedule-section")

const calendarContainer = scheduleSection.querySelector("#calendar")

const schedulePlan = scheduleSection.querySelector("#calendar-controls")

const weekPlanBtn = schedulePlan.querySelector("#week-view-btn")

const monthPlanBtn = schedulePlan.querySelector("#month-view-btn")

let currentView = "week";

const shifts = [];

let chosenDate = new Date();

async function loadSchedule() {

    const date = chosenDate.toISOString()
        .split("T")[0];

    let url;

    if (currentView === "week") {
        url = `/Adventure/schedule/week?date=${date}`;
    } else {
        url = `/Adventure/schedule/month?date=${date}`;
    }

    const response = await fetch(url);
    const schedule = await response.json()

    return schedule;

}



const openingHour = 10;
const closingHour = 22;

function getMonday(chosenDate) {

    const date = new Date(chosenDate);

    const day = date.getDay();

    const difference = day === 0
        ? -6
        : 1 - day;

    date.setDate(
        date.getDate() + difference
    );

    return date;
}


function getWeek(chosenDate) {

    const monday = getMonday(chosenDate);

    const week = [];

    for (let i = 0; i < 7; i++) {

        const date = new Date(monday);

        date.setDate(
            monday.getDate() + i
        );

        week.push(date);
    }

    return week;
}

function createTimeColumn(){
    const timeColumn = document.createElement("div");

    const headerSpace = document.createElement("div");
    headerSpace.classList.add("day-header-space");

    timeColumn.appendChild(headerSpace);

    timeColumn.classList.add("time-column");

    for(let hour = openingHour; hour <= closingHour; hour++){
        const timeLabel = document.createElement("div");
        timeLabel.classList.add("time-label");
        timeLabel.textContent = hour + ":00";   
        timeColumn.appendChild(timeLabel);
  }
return timeColumn;  
}

function renderWeek(chosenDate, schedule) {


    const week = getWeek(chosenDate);

    for (const date of week) {

        const dayColumn = document.createElement("div");

        dayColumn.classList.add("day-column");

        const dayHeader = document.createElement("h3");

        dayHeader.textContent = date.toLocaleDateString("da-DK",
             { weekday: "long", day: "numeric", month: "long" });
             dayColumn.appendChild(dayHeader);


             for (let hour = openingHour; hour <= closingHour; hour++){
                const hourSlot = document.createElement("div");

                hourSlot.classList.add("hour-slot");
                dayColumn.appendChild(hourSlot);


             }

             calendarContainer.appendChild(dayColumn);
        }
}

function renderMonth(chosenDate, schedule) {
}

async function renderCalendar(chosenDate){

     const schedule = await loadSchedule();

    calendarContainer.replaceChildren();
    if (currentView === "week"){

        const timeColumn = createTimeColumn();

        calendarContainer.appendChild(timeColumn);

        renderWeek(chosenDate, schedule);
     } else{
            renderMonth(chosenDate, schedule);
        }
    }

weekPlanBtn.addEventListener("click", function(){
    currentView = "week";
    renderCalendar(chosenDate);
});

monthPlanBtn.addEventListener("click", function(){
    currentView = "month";
    renderCalendar(chosenDate);
});

renderCalendar(chosenDate);