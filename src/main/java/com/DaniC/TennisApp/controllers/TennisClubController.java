package com.DaniC.TennisApp.controllers;

import com.DaniC.TennisApp.payload.request.TennisClubRequest;
import com.DaniC.TennisApp.services.TennisClubService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tennisclub")
@RequiredArgsConstructor
public class TennisClubController {

    private final TennisClubService tennisClubService;

    @PostMapping("/newclub")
    public ResponseEntity<?> addTennisClub(@RequestBody TennisClubRequest tennisClubRequest){
        return tennisClubService.addTennisClub(tennisClubRequest);
    }

}
