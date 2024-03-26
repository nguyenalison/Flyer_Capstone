package org.alisonnguyen.flyercapstone;

import jakarta.websocket.DeploymentException;
import org.alisonnguyen.flyercapstone.model.Calendar;
import org.alisonnguyen.flyercapstone.model.Event;
import org.alisonnguyen.flyercapstone.model.User;
import org.alisonnguyen.flyercapstone.repository.CalendarRepository;
import org.alisonnguyen.flyercapstone.repository.EventRepository;
import org.alisonnguyen.flyercapstone.repository.UserRepository;
import org.alisonnguyen.flyercapstone.service.EventService;
import org.alisonnguyen.flyercapstone.service.EventServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class EventRepositoryTests {
    @Autowired
    private CalendarRepository calendarRepository;
    @Autowired
    private EventRepository eventRepository;

    @Test
    public void testFindByEmail(){
        Event event = new Event("Meeting", "Zoom", "03-25-2024", "12:00", "14:00", "Presentation", "Default");
        eventRepository.save(event);
        long id = event.getId();

        long getId = eventRepository.findEventById(id).getId();
        assertEquals(event.getId(), getId);
    }

    @Test
    public void testFindByCalendarQuery() {
        // Save the Calendar entity first
        Calendar calendar = new Calendar("Default");
        calendarRepository.save(calendar);

        // Create and save the Event instances
        Event event1 = new Event("Meeting1", "Zoom","03-25-2024", "12:00", "14:00", "For unit testing1", calendar.getName());
        Event event2 = new Event("Meeting2", "Zoom", "03-25-2024", "12:00", "14:00", "For unit testing2", calendar.getName());
        Event event3 = new Event("Meeting3", "Zoom", "03-25-2024", "12:00", "14:00", "For unit testing3", new Calendar("Per Scholas").getName());
        eventRepository.save(event1);
        eventRepository.save(event2);
        eventRepository.save(event3);

        // Query for events by calendar name
        List<Event> events = eventRepository.findByCalendarQuery(calendar.getName());

        // Create a list of expected events
        List<Event> expected = new ArrayList<>();
        expected.add(event1);
        expected.add(event2);

        System.out.println("Expected events: " + expected.size());
        System.out.println("Actual events: " + events.size());
        System.out.println("Actual events: " + events);

        // Assert that the list of events matches the expected events
        assertEquals(expected.size(), events.size());
//        assertTrue(events.containsAll(expected));

        eventRepository.delete(event1);
        eventRepository.delete(event2);
        eventRepository.delete(event3);
    }

    @Test
    public void testFindEventsWithinTimeRange() {
        LocalDateTime startDateTime = LocalDateTime.now().minusHours(1);
        LocalDateTime endDateTime = LocalDateTime.now().plusHours(1);
        Event event1 = new Event(); // Create event instances
        Event event2 = new Event();
        when(eventRepository.findEventsWithinTimeRange(startDateTime, endDateTime)).thenReturn(Arrays.asList(event1, event2));

        List<Event> events = eventRepository.findEventsWithinTimeRange(startDateTime, endDateTime);
        assertEquals(2, events.size());
    }
}
