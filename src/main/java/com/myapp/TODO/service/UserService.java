package com.myapp.TODO.service;

import com.myapp.TODO.dto.UserCreationDTO;
import com.myapp.TODO.dto.UserDTO;
import com.myapp.TODO.model.User;
import com.myapp.TODO.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(userMapper::toDTO).collect(Collectors.toList());
    }

    public Optional<UserDTO> getUserById(UUID id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(userMapper::toDTO);
    }

   public UserDTO createUser(UserCreationDTO userCreationDTO) {
    User user = userMapper.toEntity(userCreationDTO);
    user.setHashPassword(passwordEncoder.encode(userCreationDTO.getPassword()));
    User savedUser = userRepository.save(user);
    return userMapper.toDTO(savedUser);
    }

   public UserDTO saveUser(UUID id, UserCreationDTO userCreationDTO) {
    User existingUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    existingUser.setFirstName(userCreationDTO.getFirstName());
    existingUser.setLastName(userCreationDTO.getLastName());
    existingUser.setBirthday(userCreationDTO.getBirthday());
    existingUser.setEmail(userCreationDTO.getEmail());
    existingUser.setRole(userCreationDTO.getRole());
    User savedUser = userRepository.save(existingUser);
    return userMapper.toDTO(savedUser);
}

    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }
}