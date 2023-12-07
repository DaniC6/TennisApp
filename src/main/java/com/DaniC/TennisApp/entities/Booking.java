package com.DaniC.TennisApp.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity @Getter @Setter @NoArgsConstructor @ToString @EqualsAndHashCode
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "court_id")
    private Court court;

    private LocalDateTime startBook;
    private LocalDateTime endBook;

    public Booking(User user, Court court, LocalDateTime startBook, LocalDateTime endBook) {
        this.user = user;
        this.court = court;
        this.startBook = startBook;
        this.endBook = endBook;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass () != o.getClass ()) return false;
        Booking booking = (Booking) o;
        return id == booking.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash ( id );
    }
}
