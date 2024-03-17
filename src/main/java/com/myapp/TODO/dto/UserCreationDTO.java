package com.myapp.TODO.dto;

import com.myapp.TODO.validation.MinMaxAge;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCreationDTO {
    @NotNull(message = "First name cannot be null")
    @Size(min = 2, max = 30, message = "First name must be between 2 and 30 characters")
    private String firstName;
    @NotNull(message = "Last name cannot be null")
    @Size(min = 2, max = 30, message = "Last name must be between 2 and 30 characters")
    private String lastName;
    @NotNull(message = "Birthday cannot be null")
    @MinMaxAge
    private Date birthday;

    @NotNull(message = "Email cannot be null")
    @Size(min = 5, max = 50, message = "Email must be between 5 and 50 characters")
    @Email
    private String email;
    @NotNull(message = "Password cannot be null")
    @Size(min = 8, max = 30, message = "Password must be between 8 and 30 characters")

    private String password;

    @NotNull(message = "Role cannot be null")

    private String role;
}