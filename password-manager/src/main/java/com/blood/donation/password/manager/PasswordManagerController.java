package com.blood.donation.password.manager;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/passwords")
@AllArgsConstructor
public class PasswordManagerController {

    private final PasswordManagerService passwordManagerService;

    @PostMapping
    public ResponseEntity<String> savePasswordForUser(@RequestBody PasswordRequest passwordRequest){
        String res = passwordManagerService.savePassword(passwordRequest);
        if(res.equals("SUCCESS")){
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(res);
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(res);
        }
    }
}
