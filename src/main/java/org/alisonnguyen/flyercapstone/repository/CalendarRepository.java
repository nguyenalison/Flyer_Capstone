package org.alisonnguyen.flyercapstone.repository;

import org.alisonnguyen.flyercapstone.model.Calendar;
import org.alisonnguyen.flyercapstone.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {
    void deleteById(@Param("id") Long id);
    List<Calendar> findAll();
    Calendar findCalendarByName(String name);
}
