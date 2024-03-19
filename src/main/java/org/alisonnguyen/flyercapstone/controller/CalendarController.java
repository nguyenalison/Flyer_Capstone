package org.alisonnguyen.flyercapstone.controller;

import lombok.extern.slf4j.Slf4j;
import org.alisonnguyen.flyercapstone.model.Calendar;
import org.alisonnguyen.flyercapstone.repository.CalendarRepository;
import org.alisonnguyen.flyercapstone.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
public class CalendarController {

    private final CalendarService calendarService;

    @Autowired
    private CalendarRepository calendarRepository;

    @Autowired
    public CalendarController(CalendarService calendarService) {
        this.calendarService = calendarService;
    }


    @PostMapping("/addcalendar")
    public String createCalendar(@RequestParam("calendarName") String calendarName) {
        Calendar calendar = new Calendar();
        calendar.setName(calendarName);
        calendarService.saveCalendar(calendar);
        return "dashboard"; // Redirect to the home page or any other page after creating the calendar
    }
    @GetMapping("/calendarlist")
    @ResponseBody
    public List<Calendar> getAllCalendars() {
        return calendarService.getAllCalendars();
    }
//
//
//    @DeleteMapping("/deletebyid/{id}")
//    public void deleteCalendarById(@PathVariable("id") long id) {
//        System.out.println("BEFORE DELETE BY ID");
//        calendarRepository.deleteById(id);
//        System.out.println("AFTER DELETE BY ID");
//    }
}