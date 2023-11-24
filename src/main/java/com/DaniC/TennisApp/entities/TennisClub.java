package com.DaniC.TennisApp.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity @Getter @Setter @NoArgsConstructor @EqualsAndHashCode @ToString
public class TennisClub {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    @Column(nullable = false, length = 25)
    private String clubName;
    @Column(nullable = false, length = 25)
    private String address;
    @Column()
    private String telephone;
    @Column(nullable = false)
    private String email;
    private int numberOfCourts;


    public TennisClub(String clubName, String address, String telephone, String email) {
        this.clubName = clubName;
        this.address = address;
        this.telephone = telephone;
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass () != o.getClass ()) return false;
        TennisClub that = (TennisClub) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash ( id );
    }
}
