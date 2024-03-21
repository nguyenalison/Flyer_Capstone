
document.getElementById('new-event-form').addEventListener('submit', async function(event) {
    event.preventDefault(); // Prevent the form from submitting

    let formData = new FormData(event.target);
    // Append the selected calendar ID and name to the form data
    formData.append('calendarId', document.getElementById('selectedCalendarId').value);
    formData.append('calendarName', document.getElementById('selectedCalendarName').value);

    // Submit the form data
    try {
        let response = await fetch('/event-form', {
            method: 'POST',
            body: formData
        });
        if (response.ok) {
            console.log('Event created successfully');
            // Optionally, redirect to dashboard or perform other actions
        } else {
            console.error('Failed to create event');
        }
    } catch (error) {
        console.error('Error creating event:', error);
    }
});


async function displayCalendars() {
    let response = await fetch('/calendarlist');
    let calendars = await response.json();

    let calendarSelect = document.getElementById('calendar-select');
    calendarSelect.innerHTML = ''; // Clear previous content
    calendars.forEach(calendar => {
        let option = document.createElement('option');
        option.value = calendar.id;
        option.textContent = calendar.name;
        calendarSelect.appendChild(option);
    });

    // Add event listener to select element
    calendarSelect.addEventListener('change', function() {
        let selectedOption = calendarSelect.options[calendarSelect.selectedIndex];
        selectCalendar(selectedOption.value, selectedOption.textContent);
        console.log("changing calendar: ")
    });
}
displayCalendars();


function selectCalendar(calendarId) {
    document.getElementById('selectedCalendarId').value = calendarId;
    // document.getElementById('selectedCalendarName').value = calendarId.;

    console.log("id: " + calendarId);
}