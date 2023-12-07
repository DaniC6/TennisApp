package com.DaniC.TennisApp.services;

import com.DaniC.TennisApp.entities.Court;
import com.DaniC.TennisApp.enums.Status;
import com.DaniC.TennisApp.exception.UniqueConstraintViolationException;
import com.DaniC.TennisApp.payload.request.CourtRequest;
import com.DaniC.TennisApp.repositories.CourtRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourtService {

    private final CourtRepository courtRepository;


    public ResponseEntity<?> addCourt(CourtRequest courtRequest) {
        existByCourtName ( courtRequest.getCourtName () );
        Court court = new Court (courtRequest.getCourtName ());
        court.setStatus ( Status.Free);
        courtRepository.save ( court );
        return new ResponseEntity<> ( court, HttpStatus.CREATED ) ;
    }

    protected void existByCourtName(String courtName){
        if(courtRepository.existsByCourtName(courtName))
            throw new UniqueConstraintViolationException ( "court", "Court",courtName );
    }
}
