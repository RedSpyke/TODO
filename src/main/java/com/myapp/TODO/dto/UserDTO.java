package com.myapp.TODO.dto;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class UserDTO {

    private UUID id;
    private String firstName;
    private String lastName;
    private Date birthday;
    private String hashPassword;
    private String email;
    private String role;
    private Date createdAt;
    private Date updatedAt;
}