package org.alisonnguyen.flyercapstone.service;

import lombok.Setter;
import org.alisonnguyen.flyercapstone.model.Calendar;
import org.alisonnguyen.flyercapstone.model.Event;
import org.alisonnguyen.flyercapstone.repository.CalendarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EventServiceImpl implements EventService{
    @Autowired
    private CalendarRepository calendarRepository;

   @Override
    @Transactional
    public void saveEvent(Event event){

   }
}
