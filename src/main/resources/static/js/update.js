document.getElementById('edit-calendar-form').addEventListener('submit', async function(event) {
    event.preventDefault(); // Prevent the form from submitting

    let formData = new FormData(event.target);

    try {
        let response = await fetch('/edit-calendar-name', {
            method: 'PUT',
            body: formData
        });
        if (response.ok) {
            console.log('Calendar name updated successfully');
            // Optionally, redirect to dashboard or perform other actions
        } else {
            console.error('Failed to update calendar name');
        }
    } catch (error) {
        console.error('Error updating calendar name:', error);
    }
});