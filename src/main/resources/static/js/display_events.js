function showAllEvents() {
    fetch('/events')
        .then(response => {
            if (!response.ok) {
                throw new Error('Failed to fetch events');
            }
            return response.json();
        })
        .then(events => {
            let eventTable = document.getElementById('eventTable');
            eventTable.innerHTML = ''; // Clear previous content
            events.forEach(event => {
                let row = eventTable.insertRow();
                let titleCell = row.insertCell(0);
                let locationCell = row.insertCell(1);
                let dayCell = row.insertCell(2);
                let startTimeCell = row.insertCell(3);
                let endTimeCell = row.insertCell(4);
                let notesCell = row.insertCell(5);
                let calendarNameCell = row.insertCell(6);

                titleCell.textContent = event.title;
                locationCell.textContent = event.location;
                dayCell.textContent = event.day;
                startTimeCell.textContent = event.start_time;
                endTimeCell.textContent = event.end_time;
                console.log(startTimeCell)
                notesCell.textContent = event.notes;
                calendarNameCell.textContent = event.calendar_name;
            });
        })
        .catch(error => {
            console.error('Error fetching or processing events:', error);
            // You can display an error message or handle the error in another way
        });
}

document.getElementById('eventTableBody').addEventListener('click', async function(event) {
    if (event.target.classList.contains('delete-btn')) {
        const eventId = event.target.getAttribute('data-event-id');
        try {
            let response = await fetch(`/events/${eventId}`, {
                method: 'DELETE'
            });
            if (response.ok) {
                event.target.closest('tr').remove();
                console.log('Event deleted successfully');
            } else {
                console.error('Failed to delete event');
            }
        } catch (error) {
            console.error('Error deleting event:', error);
        }
    }
});