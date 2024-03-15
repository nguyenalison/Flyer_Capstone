package org.alisonnguyen.flyercapstone.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import jakarta.persistence.*;

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

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    public Calendar(String name) {
        this.name = name;
    }
}