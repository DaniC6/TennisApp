package com.DaniC.TennisApp.controllers;

import com.DaniC.TennisApp.payload.request.CourtRequest;
import com.DaniC.TennisApp.services.CourtService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @PreAuthorize ( "hasRole('ROLE_ADMIN')" )
    @PutMapping ("/updt-court/{id}")
    public ResponseEntity<?> putCourt(@PathVariable int id, @RequestBody CourtRequest courtRequest){
        return courtService.putCourt ( id,courtRequest );
    }

    @PreAuthorize ( "hasRole('ROLE_ADMIN')" )
    @DeleteMapping("/del/{id}")
    public ResponseEntity<?> deleteCourt(@PathVariable int id){
        return courtService.deleteCourt(id);
    }


}
