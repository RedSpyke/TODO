package com.myapp.TODO.service;

import com.myapp.TODO.dto.UserCreationDTO;
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

    public User toEntity(UserCreationDTO dto) {
        User user = new User();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setBirthday(dto.getBirthday());
        user.setEmail(dto.getEmail());
        user.setRole(dto.getRole());
        // createdAt, updatedAt, and tasks are not set here
        return user;
    }

    public UserCreationDTO toCreationDTO(UserDTO dto) {
        UserCreationDTO creationDTO = new UserCreationDTO();
        creationDTO.setFirstName(dto.getFirstName());
        creationDTO.setLastName(dto.getLastName());
        creationDTO.setBirthday(dto.getBirthday());
        creationDTO.setEmail(dto.getEmail());
        creationDTO.setRole(dto.getRole());
        return creationDTO;
    }


}