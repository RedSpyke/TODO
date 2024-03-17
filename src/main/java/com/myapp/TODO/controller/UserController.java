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
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper){
    this.userService = userService;
    this.userMapper = userMapper;
    }

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable UUID id) {
        return userService.getUserById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public UserDTO createUser(@RequestBody UserCreationDTO userCreationDTO) {
        System.out.println("userCreationDTO: " + userCreationDTO);
        return userService.createUser(userCreationDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable UUID id, @RequestBody UserDTO userDTO) {
        return userService.getUserById(id)
                .map(existingUser -> {
                    userDTO.setId(id);
                    UserCreationDTO userCreationDTO = userMapper.toCreationDTO(userDTO);
                    return ResponseEntity.ok(userService.saveUser(id, userCreationDTO));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        return userService.getUserById(id)
                .map(user -> {
                    userService.deleteUser(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // get all tasks of a user
    @GetMapping("/{id}/tasks")
    public Set<TaskDTO> getAllTasks(@PathVariable UUID id) {
        return userService.getAllTasks(id);
    }
}