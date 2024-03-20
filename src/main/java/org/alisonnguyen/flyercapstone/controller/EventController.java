package org.alisonnguyen.flyercapstone.controller;

import lombok.extern.slf4j.Slf4j;
import org.alisonnguyen.flyercapstone.model.Event;
import org.alisonnguyen.flyercapstone.repository.CalendarRepository;
import org.alisonnguyen.flyercapstone.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
public class EventController {
    @Autowired
    public EventService eventService;

    @Autowired
    public EventController eventController;

    public CalendarRepository calendarRepository;

    @GetMapping("/event-form")
    public String getEventForPage()
    {
        log.info("event form page displayed");
        return "event-form";
    }

    @GetMapping("/dashboard")
    public String getDashboard()
    {
        log.info("Dashboard displayed");
        return "dashboard";
    }

    @RequestMapping(value = "/event-form", method = RequestMethod.POST)
    public String createEvent(@ModelAttribute Event event){
//        calendarRepository.findCalendarByName(event.getCalendar_name());
        eventService.saveEvent(event);
        return "/dashboard";
    }
}
