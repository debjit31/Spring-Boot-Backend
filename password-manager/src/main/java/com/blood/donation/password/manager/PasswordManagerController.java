package com.blood.donation.password.manager;

import lombok.AllArgsConstructor;
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
    public void savePasswordForUser(@RequestBody PasswordRequest passwordRequest){
        passwordManagerService.savePassword(passwordRequest);
    }
}
