package org.alisonnguyen.flyercapstone.service;

import org.alisonnguyen.flyercapstone.model.Event;

import java.util.List;

public interface EventService {
    public void saveEvent(Event event);
    public List<Event> getAllEvents();
    public void deleteEvent(long id);

    public Event findEventById(Long id);
}
