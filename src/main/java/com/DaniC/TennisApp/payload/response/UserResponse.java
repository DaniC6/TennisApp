package com.DaniC.TennisApp.payload.response;

import com.DaniC.TennisApp.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class UserResponse {

    private int id;
    private String username;
    private String email;
    private LocalDateTime createdAt;


    public static UserResponse fromUserDetailsToUserResponse(User user){
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getCreatedAt()
        );
    }
}
