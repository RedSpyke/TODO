package com.myapp.TODO.service;

import com.myapp.TODO.dto.UserDTO;
import com.myapp.TODO.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserMapper {

    @Autowired
    private TaskMapper taskMapper;

    public UserDTO toDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setBirthday(user.getBirthday());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        dto.setCreatedAt(user.getCreatedAt());
        dto.setUpdatedAt(user.getUpdatedAt());
        dto.setTasks(user.getTasks().stream().map(taskMapper::toDTO).collect(Collectors.toSet()));
        return dto;
    }

    public User toEntity(UserDTO dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setBirthday(dto.getBirthday());
        user.setEmail(dto.getEmail());
        user.setRole(dto.getRole());
        // createdAt, updatedAt, and tasks are not set here
        return user;
    }
}