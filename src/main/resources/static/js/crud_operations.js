function showPopup(className) {
    document.getElementById(className).style.display = 'block';
    let blur = document.getElementById('blur');
    blur.classList.toggle('blur');

    // Add event listener to close popup when clicking outside
    document.addEventListener('click', closePopupOutside);
}

let calendarsVisible = false;

function deleteCalendar(calendarId, row) {
    fetch(`/dashboard/${calendarId}`, {
        method: 'DELETE'
    })
        .then(response => {
            if (response.ok) {
                // Remove the row from the table if the deletion was successful
                row.remove();
                console.log('Calendar deleted successfully');
            } else {
                console.error('Failed to delete calendar');
            }
        })
        .catch(error => {
            console.error('Error deleting calendar:', error);
        });
}

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

                    // Apply the CSS class to the nameCell
                    nameCell.classList.add('calendar-row-border');

                    let editLink = document.createElement('a');
                    editLink.setAttribute('href', `/edit-calendar-name`);
                    editLink.textContent = 'edit';

                    nameCell.appendChild(createEditIcon(editLink));
                    descriptionCell.textContent = calendar.description;

                    let deleteCell = row.insertCell(2);
                    deleteCell.appendChild(createDeleteIcon(calendar.id, row));
                });
            });
        calendarsVisible = true;
        console.log("show all clicked!! ");
    }
}

function createDeleteIcon(calendarId, row) {
    let deleteIcon = document.createElement('span');
    deleteIcon.innerHTML = '&#10006;';
    deleteIcon.className = 'delete-icon';
    deleteIcon.addEventListener('click', () => {
        fetch(`/deleteCalendar/${calendarId}`, {
            method: 'DELETE'
        })
            .then(response => {
                if (response.ok) {
                    // Calendar deleted successfully, remove the row from the table
                    row.remove();
                } else {
                    console.error('Failed to delete calendar');
                }
            })
            .catch(error => {
                console.error('Error deleting calendar:', error);
            });
    });
    return deleteIcon;
}

function createEditIcon(editLink) {
    let editIcon = document.createElement('span');
    editIcon.innerHTML = '&#9998;'; // Edit icon
    editIcon.className = 'edit-icon';
    editIcon.appendChild(editLink);
    return editIcon;
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


