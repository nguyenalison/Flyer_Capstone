package org.alisonnguyen.flyercapstone.controller;

import lombok.extern.slf4j.Slf4j;
import org.alisonnguyen.flyercapstone.model.Calendar;
import org.alisonnguyen.flyercapstone.model.Event;
import org.alisonnguyen.flyercapstone.repository.CalendarRepository;
import org.alisonnguyen.flyercapstone.service.CalendarService;
import org.alisonnguyen.flyercapstone.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@Slf4j
public class EventController {
    @Autowired
    public EventService eventService;

    @Autowired
    public EventController eventController;

    @Autowired
    private final CalendarService calendarService;

    @Autowired
    public CalendarRepository calendarRepository;

    public EventController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

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
    public String createEvent(@ModelAttribute Event event, @RequestParam("calendarId") Long calendarId){
        Calendar calendar = calendarRepository.findCalendarById(calendarId);
        event.setCalendar(calendar);
        event.setCalendar_name(calendar.getName());
        eventService.saveEvent(event);
        log.info("creating new event: ");
        return "dashboard";
    }

    @GetMapping("/events")
    public String getAllEvents(Model model)
    {
        log.info("display all events");
        List<Event> events = eventService.getAllEvents();
        model.addAttribute("events", events);
        return "events";
    }

    @GetMapping("/delete/{id}")
    public String deleteEvent(@PathVariable(name="id") Long id){
        log.info("entering delete event with id: " + id);
        eventService.deleteEvent(id);

        return "redirect:/dashboard";
    }

    @GetMapping("/edit/{id}")
    public String editEvent(@PathVariable(name="id") Long id, Model model){
        Event event = eventService.findEventById(id);
        model.addAttribute("event", event);
        log.info("PRINTING EVENT: " + event.getTitle() + event.getId());
        log.info("entering edit event with id: " + id);
        eventService.deleteEvent(id);
        return "edit-event-form";
    }

}
