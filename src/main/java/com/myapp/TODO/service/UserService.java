package com.myapp.TODO.service;

import com.myapp.TODO.dto.TaskDTO;
import com.myapp.TODO.dto.UserCreationDTO;
import com.myapp.TODO.dto.UserDTO;
import com.myapp.TODO.exception.InvalidPasswordException;
import com.myapp.TODO.exception.UserNotFoundException;
import com.myapp.TODO.model.Task;
import com.myapp.TODO.model.User;
import com.myapp.TODO.repository.UserRepository;
import com.myapp.TODO.validation.PasswordValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private final TaskMapper taskMapper;
    private final PasswordValidation passwordValidation;
    public UserService(UserRepository userRepository, UserMapper userMapper, BCryptPasswordEncoder passwordEncoder, TaskMapper taskMapper, PasswordValidation passwordValidation) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.taskMapper = taskMapper;
        this.passwordValidation = passwordValidation;
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(userMapper::toDTO).collect(Collectors.toList());
    }

    public UserDTO getUserById(UUID id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException("User not found"));
        return userMapper.toDTO(user);
    }

   public UserDTO createUser(UserCreationDTO userCreationDTO) {
    User user = userMapper.toEntity(userCreationDTO);
    if(!passwordValidation.validatePassword(userCreationDTO.getPassword())){
        throw new InvalidPasswordException("Password does not meet the requirements");
    }
    user.setHashPassword(encodePassword(userCreationDTO.getPassword()));
    User savedUser = userRepository.save(user);
    return userMapper.toDTO(savedUser);
    }

        public UserDTO saveUser(UUID id, UserDTO userDTO) {
        User existingUser = userRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException("User not found"));
        existingUser.setFirstName(userDTO.getFirstName());
        existingUser.setLastName(userDTO.getLastName());
        existingUser.setBirthday(userDTO.getBirthday());
        existingUser.setEmail(userDTO.getEmail());
        existingUser.setRole(userDTO.getRole());
        User savedUser = userRepository.save(existingUser);
        return userMapper.toDTO(savedUser);
    }

    public void deleteUser(UUID id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User not found");
        }
        userRepository.deleteById(id);
    }

    public UserDTO changePassword(UUID id, String oldPassword, String newPassword) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new UserNotFoundException("User not found"));

    if (!passwordEncoder.matches(oldPassword, user.getHashPassword())) {
        throw new IllegalArgumentException("Old password is incorrect");
    }

    if(!passwordValidation.validatePassword(newPassword)){
        throw new InvalidPasswordException("New password does not meet the requirements");
    }

    user.setHashPassword(passwordEncoder.encode(newPassword));
    User updatedUser = userRepository.save(user);

    return userMapper.toDTO(updatedUser);
    }

    public Set<TaskDTO> getAllTasks(UUID userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException("User not found"));
        Set<Task> tasks = user.getTasks();
        return tasks.stream()
            .map(taskMapper::toDTO)
            .collect(Collectors.toSet());
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}