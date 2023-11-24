package com.DaniC.TennisApp.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity @Getter @Setter @NoArgsConstructor @ToString @EqualsAndHashCode
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 30)
    private String username;
    @Column(length = 50, nullable = false)
    private String email;
    @Column(length = 15)
    private String password;
    @Column(length = 11)
    private String telephone;






}
