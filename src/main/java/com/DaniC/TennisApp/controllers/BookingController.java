package com.DaniC.TennisApp.controllers;

import com.DaniC.TennisApp.payload.request.BookingRequest;
import com.DaniC.TennisApp.services.BookingService;
import lombok.RequiredArgsConstructor;
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
                                            @RequestParam LocalDateTime newStartBook,
                                            @RequestParam LocalDateTime newEndBook){
        return bookingService.updateBooking (userDetails,id,newStartBook,newEndBook );
    }
}
