package org.alisonnguyen.flyercapstone.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.alisonnguyen.flyercapstone.controller.dto.ModifyCalendarsDTO;
import org.alisonnguyen.flyercapstone.model.Calendar;
import org.alisonnguyen.flyercapstone.repository.CalendarRepository;
import org.alisonnguyen.flyercapstone.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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

    @RequestMapping(value = "/saveCalendar", method = RequestMethod.POST)
    public String createCalendar(@RequestParam("calendarName") String calendarName) {

        Calendar calendar = new Calendar();
        calendar.setName(calendarName);
        calendarService.saveCalendar(calendar);
        log.info("adding calendaer");
        return "redirect:/dashboard"; // Redirect to the home page or any other page after creating the calendar
    }

    @GetMapping("/calendarlist")
    @ResponseBody
    public List<Calendar> getAllCalendars() {
        return calendarService.getAllCalendars();
    }


    @RequestMapping(value = "/deleteCalendar/{id}", method = RequestMethod.DELETE)
    public String deleteCalendar(@PathVariable("id") long id) {
        try {
            calendarService.deleteCalendar(id);
            log.info("Calendar deleted successfully");
            return "dashboard";
        } catch (Exception e) {
            log.info("Failed to delete calendar");
            return "dashboard";
        }
    }

    @GetMapping("/edit-calendar-name")
    public String getEditCalendarName()
    {
        log.info("Edit calendar name displayed");
        return "edit-calendar-name";
    }

    // Update the calendar name
    @RequestMapping(value = "/edit-calendar-name", method = RequestMethod.PUT)
    public String updateCalendarName(@PathVariable("id") long id, @RequestParam("name") String newName) {
        Calendar calendar = calendarRepository.findCalendarById(id);
        String oldName = calendar.getName();
        if (calendar != null) {
            calendar.setName(newName);
            calendarRepository.save(calendar);
        }

        log.info("Updated calendarName from " + oldName + " to " + calendar.getName())
        ;
        return "dashboard";
    }
}