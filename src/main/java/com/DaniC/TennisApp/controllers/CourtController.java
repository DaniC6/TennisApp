package com.DaniC.TennisApp.controllers;

import com.DaniC.TennisApp.payload.request.CourtRequest;
import com.DaniC.TennisApp.services.CourtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/court")
@RequiredArgsConstructor
public class CourtController {

    private final CourtService courtService;

    @PreAuthorize ( "hasRole('ROLE_ADMIN')" )
    @PostMapping
    public ResponseEntity<?> addCourt(@RequestBody CourtRequest courtRequest){
        return courtService.addCourt(courtRequest);
    }


}
