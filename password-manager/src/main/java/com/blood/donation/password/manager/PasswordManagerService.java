package com.blood.donation.password.manager;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class PasswordManagerService {

    private final PasswordManagerRepository passwordManagerRepository;

    public String savePassword(PasswordRequest passwordRequest){
        Password password = Password.builder()
                .customerId(passwordRequest.getCustomerId())
                .password(passwordRequest.getPassword())
                .build();
        log.info("Secrets updated for customer with Customer Id : {}", password.getCustomerId());
        try{
            passwordManagerRepository.save(password);
            return "SUCCESS";
        }catch (Exception e){
            e.printStackTrace();
            return "FAILURE";
        }
    }
}
