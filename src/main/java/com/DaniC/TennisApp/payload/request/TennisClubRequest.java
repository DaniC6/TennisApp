package com.DaniC.TennisApp.payload.request;

import jakarta.validation.constraints.Max;
import lombok.Getter;

@Getter
public class TennisClubRequest {

    private String clubName;
    @Max ( 25 )
    private String address;
    @Max ( 25 )
    private String email;
    private String telephone;
}
