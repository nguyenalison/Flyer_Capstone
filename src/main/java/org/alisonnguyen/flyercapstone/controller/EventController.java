package org.alisonnguyen.flyercapstone.controller;

import lombok.extern.slf4j.Slf4j;
import org.alisonnguyen.flyercapstone.model.Calendar;
import org.alisonnguyen.flyercapstone.model.Event;
import org.alisonnguyen.flyercapstone.repository.CalendarRepository;
import org.alisonnguyen.flyercapstone.service.CalendarService;
import org.alisonnguyen.flyercapstone.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        return "dashboard.html";
    }


//    @PostMapping("/event-form")
//    public String createEvent(@ModelAttribute Event event, @RequestParam("calendarId") Long calendarId) {
////        // Set the selected calendarId for the event
////        event.setId(calendarId);
////
////        // Save the event
////        eventService.saveEvent(event);
////
////        // Redirect to the calendar page or wherever appropriate
//        Calendar calendar = calendarService.findCalendarById(calendarId);
//
//        Event event = new Event();
//
//        return "dashboard";
//    }

//    public String createEvent(@RequestParam("calendarId") Long calendarId,
//                              @RequestParam("title") String title,
//                              @RequestParam("location") String location,
//                              @RequestParam("startTime") String startTime,
//                              @RequestParam("endTime") String endTime,
//                              @RequestParam("notes") String notes) {
//        Calendar calendar = calendarService.findCalendarById(calendarId);
//        if (calendar == null) {
//            // Handle calendar not found
//        }
//
//        Event event = new Event();
//        event.setTitle(title);
//        event.setLocation(location);
//        event.setStart_time(startTime);
//        event.setEnd_time(endTime);
//        event.setNotes(notes);
//        event.setCalendar(calendar);
//
//        eventService.saveEvent(event);
//
//        return "dashboard";
//    }

//    @GetMapping("/event-form")
//    public String showEventForm(Model model) {
//        List<Calendar> calendars = calendarService.getAllCalendars();
//        model.addAttribute("calendars", calendars);
//        return "event-form";
//    }
}
