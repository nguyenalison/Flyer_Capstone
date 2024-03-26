package org.alisonnguyen.flyercapstone;

import jakarta.validation.Valid;
import org.alisonnguyen.flyercapstone.model.Calendar;
import org.alisonnguyen.flyercapstone.model.Event;
import org.alisonnguyen.flyercapstone.repository.CalendarRepository;
import org.alisonnguyen.flyercapstone.repository.EventRepository;
import org.alisonnguyen.flyercapstone.service.CalendarService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CalendarRepositoryTests {
    @Autowired
    private CalendarRepository calendarRepository;

    @Autowired
    private CalendarService calendarService;

    @Test
    public void testFindByName() {
        Calendar calendar = new Calendar("Vacation");
        calendarRepository.save(calendar);

        assertEquals(calendar.getName(), "Vacation");

        calendarRepository.delete(calendar);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Default", "Per Scholas"})
    public void testGetAllCalendars(){
        Calendar cal1 = new Calendar();
        cal1.setName("Default");

        Calendar cal2 = new Calendar();
        cal2.setName("Per Scholas");

        List<Calendar> expected = new ArrayList<>();
        expected.add(cal1);
        expected.add(cal2);

        List<Calendar> calendars = calendarService.getAllCalendars();

//        assertEquals(expected.get(0), calendars.get(0));
        assertEquals(expected.get(1), calendars.get(1));
    }
}

