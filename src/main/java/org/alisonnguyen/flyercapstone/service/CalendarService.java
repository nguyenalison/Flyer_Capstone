package org.alisonnguyen.flyercapstone.service;

import org.alisonnguyen.flyercapstone.model.Calendar;
import org.alisonnguyen.flyercapstone.model.User;

import java.util.List;
import java.util.Set;

public interface CalendarService {
    public void create(User user);
    public void saveCalendar(Calendar calendar);
    public void deleteCalendar(long id);
    public List<Calendar> getAllCalendars();
}
