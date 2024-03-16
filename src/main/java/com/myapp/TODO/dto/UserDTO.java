package com.myapp.TODO.dto;

import lombok.*;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    private UUID id;
    private String firstName;
    private String lastName;
    private Date birthday;
    private String email;
    private String role;
    private Date createdAt;
    private Date updatedAt;
    private Set<TaskDTO> tasks;
}