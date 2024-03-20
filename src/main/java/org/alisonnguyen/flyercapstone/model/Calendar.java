package org.alisonnguyen.flyercapstone.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@ToString
@Entity
public class Calendar {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @OneToMany(targetEntity = Event.class, cascade = {CascadeType.ALL})
    private List<Event> calendarEvent = new ArrayList<>();
    public Calendar(String name) {
        this.name = name;
    }
}