package com.DaniC.TennisApp.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity @Getter @Setter @NoArgsConstructor @ToString @EqualsAndHashCode
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    @Column(length = 30)
    private String username;
    @Column(length = 50, nullable = false)
    private String email;
    @Column(length = 15)
    private String password;
    @Column(length = 11)
    private String telephone;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass () != o.getClass ()) return false;
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash ( id );
    }
}
