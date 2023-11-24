package com.DaniC.TennisApp.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity @Getter @Setter @NoArgsConstructor @EqualsAndHashCode @ToString
public class TennisClub {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    @Column(nullable = false, length = 25)
    private String courtName;
    @Column(nullable = false, length = 25)
    private String address;
    @Column()
    private String telephone;
    private String email;
    private int numberOfCourts;

}
