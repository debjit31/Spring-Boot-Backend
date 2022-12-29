package com.blood.donation.user.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final RestTemplate restTemplate;

    public User registerUser(UserRegistrationRequest userRegistrationRequest) throws JSONException {
        String newUserEmailAddress = userRegistrationRequest.getEmail();
        boolean isUserWithEmailAddressExists = userRepository.findByEmailAddress(newUserEmailAddress).isEmpty();
        if (isUserWithEmailAddressExists) {
            LocalDateTime createdDateTime = LocalDateTime.now();
            User userToBeSaved = User.builder()
                    .id(getUserId())
                    .firstName(userRegistrationRequest.getFirstName())
                    .lastName(userRegistrationRequest.getLastName())
                    .emailAddress(userRegistrationRequest.getEmail())
                    .createdDateTime(createdDateTime)
                    .build();
            log.info("User saved in DB {}", userToBeSaved);

            updateSecretsForUser(userToBeSaved.getId(), userRegistrationRequest.getPassword());

            return userRepository.save(userToBeSaved);
        } else {
            return null;
        }
    }

    private void updateSecretsForUser(String customerId, String password) throws JSONException {
        String updateSecretsUrl = "http://localhost:8081/api/v1/passwords";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        JSONObject secretsObject = new JSONObject();
        secretsObject.put("customerId",customerId);
        secretsObject.put("password", password);
        HttpEntity<String> postRequest = new HttpEntity<String>(secretsObject.toString(), headers);
        ResponseEntity<String> response = restTemplate
                .postForEntity(updateSecretsUrl, postRequest, String.class);
        String respStatus = response.getBody();
        log.info("Secrets Update Status is : " + respStatus + " for customer with Customer Id : - " + secretsObject.get("customerId"));
    }

    public String getUserId() {
        long count = userRepository.count();
        return String.valueOf(count + 1);
    }

    public List<GetUserResponse> getAllUsers() {
        List<User> usersFromDB = userRepository.findAll();
        return usersFromDB.stream().map(user -> GetUserResponse.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .emailAddress(user.getEmailAddress())
                .age(user.getAge())
                .bloodGrp(user.getBloodGrp())
                .contactNumber(user.getContactNumber())
                .address(user.getAddress())
                .landmark(user.getLandmark())
                .createdDateTime(user.getCreatedDateTime())
                .imageData(user.getImageData())
                .build()).toList();
    }
}
