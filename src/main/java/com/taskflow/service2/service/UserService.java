package com.taskflow.service2.service;

import com.taskflow.service2.configuration.jwt.JwtUtil;
import com.taskflow.service2.dto.BearerToken;
import com.taskflow.service2.dto.UserDetailsDTO;
import com.taskflow.service2.model.User;
import com.taskflow.service2.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public ResponseEntity<Boolean[]> validateUsers(List<Integer> userIds) {
        List<Boolean> validationResults = userIds.stream()
                .map(id -> userRepository.existsById(id)) // Check if user exists
                .collect(Collectors.toList());
        return new ResponseEntity<>(validationResults.toArray(new Boolean[0]), HttpStatus.OK);
    }

    public ResponseEntity<List<UserDetailsDTO>> getTeamMembers(List<Integer> userIds){
        List<User> users = userIds.stream()
                .map(id-> userRepository.findUserByUserID(id))
                .collect(Collectors.toList());
        List<UserDetailsDTO> teamMembers = users.stream()
                .map(user-> new UserDetailsDTO(
                        user.getUserID(),
                        user.getFirstName(),
                        user.getLastName(),
                        user.getEmail(),
                        user.getRole()
                ))
                .collect(Collectors.toList());
        return new ResponseEntity<>(teamMembers,HttpStatus.OK);
    }

    public ResponseEntity<Object> createNewUser(UserDetailsDTO userDetailsDTO) {
        if (!userRepository.existsByEmail(userDetailsDTO.getEmail())) {
            User newUser = new User();
            newUser.setUserID(userDetailsDTO.getUserID());
            newUser.setFirstName(userDetailsDTO.getFirstName());
            newUser.setLastName(userDetailsDTO.getLastName());
            newUser.setEmail(userDetailsDTO.getEmail());
            newUser.setPassword(passwordEncoder.encode(userDetailsDTO.getPassword()));
            newUser.setRole(userDetailsDTO.getRole().toString());

            userRepository.save(newUser);

            logger.debug("USER : " + newUser.getEmail() + " IS CREATED SUCCESSFULLY");
            String token = jwtUtil.generateToken(newUser.getEmail(), newUser.getRole());
            return new ResponseEntity<>(new BearerToken(token, "Bearer", newUser), HttpStatus.OK);
        } else {
            logger.debug("USER ALREADY EXISTS");
            return new ResponseEntity<>("USER ALREADY EXISTS", HttpStatus.ALREADY_REPORTED);
        }
    }

}
