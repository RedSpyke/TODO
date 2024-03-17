package com.myapp.TODO.controller;

import com.myapp.TODO.dto.PasswordChangeRequest;
import com.myapp.TODO.dto.TaskDTO;
import com.myapp.TODO.dto.UserCreationDTO;
import com.myapp.TODO.dto.UserDTO;
import com.myapp.TODO.exception.InvalidPasswordException;
import com.myapp.TODO.exception.PasswordChangeFailedException;
import com.myapp.TODO.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable UUID id) {
        UserDTO userDTO = userService.getUserById(id);
        if(userDTO != null) {
            return ResponseEntity.ok(userDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody UserCreationDTO userCreationDTO, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<>(errorMap, HttpStatus.BAD_REQUEST);
        }

        try {
            UserDTO userDTO = userService.createUser(userCreationDTO);
            return ResponseEntity.ok(userDTO);
        } catch (InvalidPasswordException e) {
            return ResponseEntity.badRequest().body("Invalid password provided.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable UUID id, @RequestBody UserDTO userDTO) {
        UserDTO updatedUserDTO = userService.saveUser(id, userDTO);
        return ResponseEntity.ok(updatedUserDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    // get all tasks of a user
    @GetMapping("/{id}/tasks")
    public Set<TaskDTO> getAllTasks(@PathVariable UUID id) {
        return userService.getAllTasks(id);
    }

    // change password
    @PutMapping("/{id}/changePassword")
    public ResponseEntity<Void> changePassword(@PathVariable UUID id, @RequestBody PasswordChangeRequest passwordChangeRequest){
        boolean isPasswordChanged = userService.changePassword(id, passwordChangeRequest.getOldPassword(), passwordChangeRequest.getNewPassword());
        if (!isPasswordChanged) {
            throw new PasswordChangeFailedException("Password change failed");
        }
        return ResponseEntity.noContent().build(); // 204 No Content
    }
}
