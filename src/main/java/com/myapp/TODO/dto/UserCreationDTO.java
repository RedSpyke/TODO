package com.myapp.TODO.dto;

import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCreationDTO {

    private String firstName;
    private String lastName;
    private Date birthday;
    private String email;
    private String password;
    private String role;
}