package com.DaniC.TennisApp.controllers;

import com.DaniC.TennisApp.payload.request.BookingRequest;
import com.DaniC.TennisApp.services.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/booking")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PreAuthorize ( "hasRole('ROLE_USER')" )
    @PostMapping()
    public ResponseEntity<?> addBooking(@AuthenticationPrincipal UserDetails userDetails,
                                        @RequestBody BookingRequest bookingRequest){
        return bookingService.createBooking ( userDetails,bookingRequest );
    }

    @PreAuthorize ( "hasRole('ROLE_USER')" )
    @PutMapping ("/mod-book/{id}")
    public ResponseEntity<?> updateBooking( @AuthenticationPrincipal UserDetails userDetails,
                                            @PathVariable int id,
                                            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime newStartBook,
                                            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime newEndBook) {
        return bookingService.updateBooking (userDetails,id,newStartBook,newEndBook );
    }

    @PreAuthorize ( "hasRole('ROLE_USER')" )
    @DeleteMapping("/del/{id}")
    public ResponseEntity<?> deleteBooking(@PathVariable int id){
        return bookingService.deleteBooking(id);
    }
}
