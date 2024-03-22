package org.alisonnguyen.flyercapstone.service;

import org.alisonnguyen.flyercapstone.model.Calendar;
import org.alisonnguyen.flyercapstone.model.Event;
import org.alisonnguyen.flyercapstone.repository.CalendarRepository;
import org.alisonnguyen.flyercapstone.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EventServiceImpl implements EventService{
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private CalendarRepository calendarRepository;

    @Transactional
    public void saveEvent(Event event){

            eventRepository.save(event);

    }
    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public void deleteEvent(long id){
        eventRepository.deleteById(id);
    }

    public Event findEventById(Long id) { return eventRepository.findEventById(id);}
}
