package org.alisonnguyen.flyercapstone.controller;

import org.alisonnguyen.flyercapstone.model.Calendar;
import org.alisonnguyen.flyercapstone.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalendarController {

    private final CalendarService calendarService;

    @Autowired
    public CalendarController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }

    @PostMapping("/calendars")
    public String createCalendar(@RequestParam("calendarName") String calendarName) {
        Calendar calendar = new Calendar();
        calendar.setName(calendarName);
        calendarService.saveCalendar(calendar);
        return "/dashboard"; // Redirect to the home page or any other page after creating the calendar
    }
}