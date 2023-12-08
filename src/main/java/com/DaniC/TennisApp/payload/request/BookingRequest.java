package com.DaniC.TennisApp.payload.request;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BookingRequest {

    private int courtId;
    private LocalDateTime startBook;
    private LocalDateTime endBook;

}
