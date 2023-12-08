package com.DaniC.TennisApp.services;

import com.DaniC.TennisApp.entities.Booking;
import com.DaniC.TennisApp.entities.Court;
import com.DaniC.TennisApp.entities.User;
import com.DaniC.TennisApp.enums.Status;
import com.DaniC.TennisApp.exception.OwnerException;
import com.DaniC.TennisApp.exception.ResourceNotFoundException;
import com.DaniC.TennisApp.payload.request.BookingRequest;
import com.DaniC.TennisApp.repositories.BookingRepository;
import com.DaniC.TennisApp.repositories.CourtRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final CourtRepository courtRepository;

    public ResponseEntity<?> createBooking(UserDetails userDetails, BookingRequest bookingRequest) {
        User user = (User) userDetails;

        Optional<Court> court = courtRepository.findById ( bookingRequest.getCourtId () );

        if (court.get ().getStatus ().equals ( Status.Free )) {
            LocalDateTime startBook = bookingRequest.getStartBook ();
            LocalDateTime endBook = bookingRequest.getEndBook ();

            // Verifica che non ci siano prenotazioni nello stesso lasso di tempo
            boolean isOverlapping = bookingRepository.existsByCourtAndEndBookGreaterThanEqualAndStartBookLessThanEqual (
                    court.get (), startBook, endBook );

            if (!isOverlapping) {
                // Calcola la durata della prenotazione in minuti
                long durationInMinutes = Duration.between ( startBook, endBook ).toMinutes ();

                // Verifica che la durata sia valida (un' ora o multipli di mezz'ora)
                if (durationInMinutes % 30 == 0 && durationInMinutes >= 30) {
                    Booking booking = new Booking ( user, court.get (), startBook, endBook );
                    booking.getCourt ().setStatus ( Status.Booked );
                    bookingRepository.save ( booking );

                    return ResponseEntity.ok ( "Prenotazione effettuata con successo!" );

                } else {
                    return ResponseEntity.badRequest ().body ( "La durata della prenotazione non è valida." );
                }
            } else {
                return ResponseEntity.badRequest ().body ( "Campo non trovato." );
            }
        }
        return new ResponseEntity<> ( "Booking ceated", HttpStatus.CREATED );
    }


    @Transactional
    public ResponseEntity<?> updateBooking(UserDetails userDetails, int id, LocalDateTime newStartBook,LocalDateTime newEndBook){

        User user = (User) userDetails;
        isOwner ( userDetails, user );
        Booking booking = findBookingById ( id );
        booking.setStartBook ( newStartBook );
        booking.setEndBook ( newEndBook );

        bookingRepository.save ( booking );

        return ResponseEntity.ok ( "Booking uptated" );

    }

    public ResponseEntity<?> deleteBooking(int id) {
        Booking booking = findBookingById ( id );
        bookingRepository.delete ( booking );
        return new ResponseEntity<> ( "Booking deleted", HttpStatus.OK );
    }



    protected Booking findBookingById(int id){
        return bookingRepository.findById ( id ).orElseThrow (()-> new ResourceNotFoundException ( "Booking","id",id ) );
    }

    protected void isOwner(UserDetails userDetails, User user) {
        if (!user.equals((User) userDetails))
            throw new OwnerException();
    }



}
