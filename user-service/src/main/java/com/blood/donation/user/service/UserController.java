package com.blood.donation.user.service;

import lombok.AllArgsConstructor;
import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserRegistrationResponse> registerUser(@RequestBody UserRegistrationRequest userRegistrationRequest) throws JSONException {
        User newUser = userService.registerUser(userRegistrationRequest);

        if(newUser == null){
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new UserRegistrationResponse(LocalDateTime.now(),"403", "User with email address already exists!!"));
        }else{
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(new UserRegistrationResponse(LocalDateTime.now(),"201", "Donor Created Successfully"));
        }
    }

    @GetMapping
    public List<GetUserResponse> getAllUsers(){
        return userService.getAllUsers();
    }
}
