// window.location.href= "/dashboard";
let today = new Date();
console.log(today.getDate())
let this_month = today.getMonth();
let this_year = today.getFullYear();

// display month
const month_list = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December']
document.getElementById("this-month").textContent = month_list[this_month];
document.getElementById("this-year").textContent = this_year;

// display days of week
let week_list = ["sun", "mon","tues","wed","thurs","fri","sat"];

for(let i = 0; i <7; i++){
    let day_element = document.createElement("th");
    let new_day = document. createTextNode(week_list[i]);
    day_element.appendChild(new_day);
    day_element.setAttribute("scope", "days-of-week");
    const element = document.getElementById("days-of-week");
    element.appendChild(day_element);
}


isLeapYear = (year) => {
    return (year % 4 === 0 && year % 100 !== 0 && year % 400 !== 0) || (year % 100 === 0 && year % 400 ===0)
}

getFebDays = (year) => {
    return isLeapYear(year) ? 29 : 28
}

generateCalendar = (month, year) => {

    let num_days_month = [31, getFebDays(year), 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
    let first_day_of_month= new Date(year, month, 1).getDay()+1;
    let is_first_week = true;
    let past_first_day = false
    let counter = 0;
    let insertValue = ""

    let calendar_body  = document.getElementById("calendar-body");
    calendar_body.innerHTML = "";
    for(let w = 1; w <=6; w++){


        if(w > 1){
            is_first_week = false;
        }

        let calendar_row_ele = document.createElement("tr");
        calendar_row_ele.setAttribute("id", "calendar-row"+w)
        let parent_element = document.getElementById("calendar-body");
        parent_element.appendChild(calendar_row_ele);

        // console.log("================week"+ w +"===================")

        for(let d = 1; d <= 7; d++){

            // console.log("this month " + this_month + "\nnumdays: " + num_days_month[this_month] + "\ncounter :" +counter)
            if(d == first_day_of_month && is_first_week && !past_first_day){

                // insertValue = counter;
                past_first_day = true;
                is_first_week = false;
            }
            else if(d != first_day_of_month && is_first_week && !past_first_day){
                insertValue = " ";
                counter = 0;
            }
            if(!is_first_week && past_first_day){
                counter += 1;
                insertValue = counter;
            }


            let day_number_element = document.createElement("td");
            let day_number = document. createTextNode(insertValue);
            day_number_element.appendChild(day_number);
            if (new Date(this_year, this_month, insertValue).toDateString() === today.toDateString()) {
                day_number_element.classList.add("highlighted");
            }
            const parent_element = document.getElementById("calendar-row"+w);
            parent_element.appendChild(day_number_element);

            if(counter === num_days_month[this_month]){
                break
            }
        }
        if(counter === num_days_month[this_month]){
            break
        }
    }
}

generateCalendar(this_month, this_year)
selectWeek();

// Event listener for the left arrow icon
// Add event listeners for left and right arrows
document.querySelector(".bi-arrow-left-circle-fill").addEventListener("click", () => {
    if (this_month === 0) {
        this_month = 11;
        this_year--;
    } else {
        this_month--;
    }
    document.getElementById("this-month").textContent = month_list[this_month];
    document.getElementById("this-year").textContent = this_year;
    generateCalendar(this_month, this_year);
    selectWeek();
});


// when the user wants to move the calendar forward
document.querySelector(".bi-arrow-right-circle-fill").addEventListener("click", () => {
    if (this_month === 11) {
        this_month = 0;
        this_year++;
    } else {
        this_month++;
    }
    document.getElementById("this-month").textContent = month_list[this_month];
    document.getElementById("this-year").textContent = this_year;
    generateCalendar(this_month, this_year);
    selectWeek();
});


// when the user clicks on a week row
function selectWeek() {
    document.querySelectorAll('#calendar-body td'). forEach(day =>{
        day.addEventListener('click', ()=> {
            document.querySelectorAll('#calendar-body tr').forEach(row => {
                row.classList.remove('highlighted-row');
            });
            day.parentElement.classList.add('highlighted-row');
        })
    })
}
function generateHourButtons() {
    let hoursContainer = document.getElementById("hours-container");
    hoursContainer.innerHTML = ""; // Clear existing buttons

    for (let i = 0; i < 24; i++) {
        let button = document.createElement("button");
        button.textContent = `${i}:00`;
        button.classList.add("hour-button");
        hoursContainer.appendChild(button);
    }
}

// generateHourButtons()

function showPopup(className) {
    document.getElementById(className).style.display = 'block';
    let blur = document.getElementById('blur');
    blur.classList.toggle('blur');

    // Add event listener to close popup when clicking outside
    document.addEventListener('click', closePopupOutside);
}
let calendarsVisible = false;
function showAllCalendars() {
    if (calendarsVisible) {
        hideCalendars();
    } else {
        fetch('/calendarlist')
            .then(response => response.json())
            .then(calendars => {
                let calendarTable = document.getElementById('calendarTable');
                calendarTable.innerHTML = ''; // Clear previous content
                calendars.forEach(calendar => {
                    let row = calendarTable.insertRow();
                    let nameCell = row.insertCell(0);
                    let descriptionCell = row.insertCell(1);
                    nameCell.textContent = calendar.name;
                    nameCell.id = `calendar-${calendar.id}`;
                    descriptionCell.textContent = calendar.description;
                    let deleteCell = row.insertCell(2); // Add cell for delete icon

                    // Create a delete icon
                    let deleteIcon = document.createElement('span');
                    deleteIcon.innerHTML = '&#10006;';
                    deleteIcon.className = 'delete-icon';
                    deleteIcon.addEventListener('click', () => deleteCalendar(calendar.id));
                    nameCell.appendChild(deleteIcon);
                });
            });
        // showPopup('showAllCalendars');
        calendarsVisible = true;
        console.log("show all clicked!! ")
    }
}
function deleteCalendar(calendarId) {
    fetch(`/deletebyid/${calendarId}`, {
        method: 'DELETE'
    })
        .then(() => {
            // Remove the calendar from the DOM
            let row = document.getElementById(`calendar-${calendarId}`);
            if (row) {
                row.remove();
                console.log("deleting item: " + `${calendarId}`);
            }
        })
        .catch(error => console.error('Error deleting calendar:', error));
}

function hideCalendars() {
    let calendarTable = document.getElementById('calendarTable');
    calendarTable.innerHTML = ''; // Clear the table content
    calendarsVisible = false;
}

function closePopupOutside(event) {
    if (!document.getElementById('popup').contains(event.target) && !document.getElementById('blur').contains(event.target)) {
        document.getElementById('popup').style.display = 'none';
        let blur = document.getElementById('blur');
        blur.classList.remove('blur');

        // Remove event listener after closing popup
        document.removeEventListener('click', closePopupOutside);
    }
}
function submitForm() {
    var calendarName = document.getElementById('calendarName').value;
    if (calendarName.trim() === '') {
        alert('Please enter a calendar name');
        return false;
    }
    console.log("Entered calendar name: " + calendarName);
    return true;
}

