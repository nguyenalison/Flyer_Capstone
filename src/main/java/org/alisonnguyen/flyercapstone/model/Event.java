package org.alisonnguyen.flyercapstone.model;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@ToString
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String location;
    private String day;
    private String start_time;
    private String end_time;
    private String notes;
    private String calendar_name;


    @ManyToOne
    @JoinColumn(name = "calendar_id")
    private Calendar calendar;

    public Event(String title, String location, String start_time, String end_time, String notes, String calendar_name) {
        this.title = title;
        this.location = location;
        this.start_time = start_time;
        this.end_time = end_time;
        this.notes = notes;
        this.calendar_name = calendar_name;
    }
}
