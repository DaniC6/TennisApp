package com.DaniC.TennisApp.services;

import com.DaniC.TennisApp.entities.TennisClub;
import com.DaniC.TennisApp.payload.request.TennisClubRequest;
import com.DaniC.TennisApp.repositories.TennisClubRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TennisClubService {

    private final TennisClubRepository tennisClubRepository;

    public ResponseEntity<?> addTennisClub(TennisClubRequest tennisClubRequest) {
        TennisClub tennisClub = new TennisClub ( tennisClubRequest.getClubName (),
                tennisClubRequest.getEmail (),
                tennisClubRequest.getAddress (),
                tennisClubRequest.getTelephone () );

        tennisClubRepository.save (tennisClub);
        return new ResponseEntity<> ( "Club created", HttpStatus.CREATED );


    }
}
