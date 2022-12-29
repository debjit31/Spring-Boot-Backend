package com.blood.donation.user.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetUserResponse {
    private String id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String age;
    private String bloodGrp;
    private String contactNumber;
    private String address;
    private String landmark;
    private LocalDateTime createdDateTime;
    private byte[] imageData;
}
