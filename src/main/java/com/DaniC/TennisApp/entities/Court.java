package com.DaniC.TennisApp.entities;

import com.DaniC.TennisApp.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity @Getter @Setter @NoArgsConstructor @ToString @EqualsAndHashCode
public class Court {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    @Column(length = 25, nullable = false)
    private String courtName;
    @Enumerated(EnumType.STRING)
    private Status status;



    public Court(String courtName) {
        this.courtName = courtName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass () != o.getClass ()) return false;
        Court court = (Court) o;
        return id == court.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash ( id );
    }
}
