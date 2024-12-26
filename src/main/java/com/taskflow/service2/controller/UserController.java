package com.taskflow.service2.controller;


import com.taskflow.service2.dto.*;
import com.taskflow.service2.service.UserService;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = {"http://localhost:9000", "http://localhost:3000"})
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    /*
    @PostMapping("/new")
    public ResponseEntity<Object> newUser(@RequestBody UserDetailsDTO user){
        return userService.createNewUser(user);
    }*/

    @PostMapping("/validate_users")
    public ResponseEntity<Boolean[]> validateUsers(@RequestBody List<Integer> ids){
        return userService.validateUsers(ids);
    }

    @PostMapping("/get_team_members")
    public ResponseEntity<List<UserDetailsDTO>> getTeamMembers(@RequestBody List<Integer> teamMembers){
        return userService.getTeamMembers(teamMembers);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<Object> authenticate(@RequestBody LoginDTO loginDTO){
        return userService.authenticate(loginDTO);
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody RegisterDTO registerDTO){
        return userService.createNewUser(registerDTO);
    }

    @GetMapping("/get_user_details/{userId}")
    public ResponseEntity<UserDetailsDTO> getUserDetails(@PathVariable Integer userId){
        return userService.getUserDetails(userId);
    }

    @GetMapping("/get_user_by_email/{email}")

    public ResponseEntity<UserDetailsDTO> getUserByEmail(@PathVariable String email){
        return userService.getUserDetailsByEmail(email);
    }

}
