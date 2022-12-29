package com.blood.donation.password.manager;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PasswordManagerRepository extends MongoRepository<Password, String> {
}
