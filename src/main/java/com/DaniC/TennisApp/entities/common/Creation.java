package com.DaniC.TennisApp.entities.common;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public class Creation implements Serializable {

    @CreationTimestamp//la data viene scritta automaicamente ,
    // non sono io in costruzione dell'oggetto a farlo,
    // funziona solamente usando i metodi di Hibernate
    @Column(updatable = false)
    private LocalDateTime createdAt;

}
