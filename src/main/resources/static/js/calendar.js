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

function increaseCalendarSize(percentage) {
    let calendar = document.querySelector('.calendar');

    let currentFontSize = parseInt(window.getComputedStyle(calendar).fontSize);
    let newFontSize = currentFontSize + currentFontSize * (percentage / 100);

    // Increase the font size of the calendar and its children
    calendar.style.fontSize = newFontSize + 'px';

    let calendarChildren = calendar.querySelectorAll('*');
    calendarChildren.forEach(child => {
        let childFontSize = parseInt(window.getComputedStyle(child).fontSize);
        let newChildFontSize = childFontSize + childFontSize * (percentage / 100);
        child.style.fontSize = newChildFontSize + 'px';
    });

    // Increase other dimensions of the calendar if needed
    // calendar.style.width = newWidth + 'px';
    // calendar.style.height = newHeight + 'px';

    // Increase the size of the buttons under the calendar
    let buttons = document.querySelectorAll('.create-cal, .create-event, .calendar-list, .display-all-events');
    buttons.forEach(button => {
        let buttonFontSize = parseInt(window.getComputedStyle(button).fontSize);
        let newButtonFontSize = buttonFontSize + buttonFontSize * (percentage / 100);
        button.style.fontSize = (newButtonFontSize) + 'px';
        button.style.height = parseInt(window.getComputedStyle(button).height) + parseInt(window.getComputedStyle(button).height) * ((percentage+30) / 100) + 'px';
        button.style.width = parseInt(window.getComputedStyle(button).width) + parseInt(window.getComputedStyle(button).width) * ((percentage+30) / 100) + 'px';
    });
}

// Example usage: Increase calendar size by 20%
increaseCalendarSize(20);
