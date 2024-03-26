package org.alisonnguyen.flyercapstone.repository;

import org.alisonnguyen.flyercapstone.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    Event findEventById(Long id);
    @Query("SELECT e FROM Event e WHERE e.calendar_name = :calendar")
    List<Event> findByCalendarQuery(@Param("calendar") String calendar);

    @Query("SELECT e FROM Event e WHERE e.start_time >= :startDateTime AND e.end_time <= :endDateTime")
    List<Event> findEventsWithinTimeRange(@Param("startDateTime") LocalDateTime startDateTime, @Param("endDateTime") LocalDateTime endDateTime);

}
