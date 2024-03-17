package com.myapp.TODO.controller;

import com.myapp.TODO.dto.TaskDTO;
import com.myapp.TODO.dto.UserCreationDTO;
import com.myapp.TODO.dto.UserDTO;
import com.myapp.TODO.service.UserMapper;
import com.myapp.TODO.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;

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
    public UserDTO createUser(@RequestBody UserCreationDTO userCreationDTO) {
        System.out.println("userCreationDTO: " + userCreationDTO);
        return userService.createUser(userCreationDTO);
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
}