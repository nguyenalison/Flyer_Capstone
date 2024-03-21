package org.alisonnguyen.flyercapstone.repository;

import org.alisonnguyen.flyercapstone.model.Calendar;
import org.alisonnguyen.flyercapstone.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByCalendar(Calendar calendar);

}
