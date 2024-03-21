package org.alisonnguyen.flyercapstone.service;

import org.alisonnguyen.flyercapstone.model.Calendar;
import org.alisonnguyen.flyercapstone.model.User;
import org.alisonnguyen.flyercapstone.repository.CalendarRepository;
import org.alisonnguyen.flyercapstone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
public class CalendarServiceImpl  implements CalendarService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CalendarRepository calendarRepository;
    @Transactional
    public void create(User user){
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void saveCalendar(Calendar calendar){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findUserByUserName(username);

        if (user != null) {
            user.getUserCalendars().add(calendar);
            userRepository.save(user);
        }
        calendarRepository.save(calendar);
    }

    @Override
    @Transactional
    public void deleteCalendar(long id) {
        calendarRepository.deleteById(id);
    }

    public List<Calendar> getAllCalendars() {return (List<Calendar>) calendarRepository.findAll();}

    public Calendar findCalendarByName(String name){
        return calendarRepository.findCalendarByName(name);
    }

    public Calendar findCalendarById(Long id){
        return calendarRepository.findCalendarById(id);
    }

}
