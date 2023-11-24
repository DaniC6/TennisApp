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

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "tennisclub_id", nullable = false)
    private TennisClub tennisClub;

    public Court(Status status, TennisClub tennisClub) {
        this.status = status;
        this.tennisClub = tennisClub;
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
