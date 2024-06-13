package com.example.dashboard.service;

import com.example.dashboard.model.User;
import com.example.dashboard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Flux<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Mono<User> getUserById(String id) {
        return userRepository.findById(id);
    }

    public Mono<User> createUser(User user) {
        return userRepository.save(user);
    }

    public Mono<User> updateUser(String id, User user) {
        return userRepository.findById(id)
                .flatMap(existingUser -> {
                    existingUser.setName(user.getName());
                    existingUser.setMobileNumber(user.getMobileNumber());
                    existingUser.setEmail(user.getEmail());
                    existingUser.setAadharCardNumber(user.getAadharCardNumber());
                    existingUser.setDateOfBirth(user.getDateOfBirth());
                    existingUser.setResidentialAddress(user.getResidentialAddress());
                    existingUser.setPermanentAddress(user.getPermanentAddress());
                    existingUser.setOccupationDetails(user.getOccupationDetails());
                    return userRepository.save(existingUser);
                });
    }

    public Mono<Void> deleteUser(String id) {
        return userRepository.deleteById(id);
    }
}
