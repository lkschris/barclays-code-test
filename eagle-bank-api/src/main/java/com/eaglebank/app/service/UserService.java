package com.eaglebank.app.service;

import com.eaglebank.app.exception.ConflictException;
import com.eaglebank.app.exception.ForbiddenActionException;
import com.eaglebank.app.exception.ResourceNotFoundException;
import com.eaglebank.app.model.User;
import com.eaglebank.app.model.dto.CreateUserRequest;
import com.eaglebank.app.model.dto.UpdateUserRequest;
import com.eaglebank.app.model.dto.UserResponse;
import com.eaglebank.app.repository.AccountRepository;
import com.eaglebank.app.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.OffsetDateTime;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AccountRepository accountRepository;

    @Transactional
    public UserResponse createUser(CreateUserRequest req) {
        UUID id = UUID.fromString("usr-" + UUID.randomUUID());
        OffsetDateTime now = OffsetDateTime.now();
        User u = new User(id, req.name(), req.address(), req.phoneNumber(), req.email(), now, now);
        userRepository.saveAndFlush(u);
        return new UserResponse(u.getId(), u.getName(), u.getAddress(), u.getPhoneNumber(), u.getEmail());
    }

    public UserResponse getUser(UUID userId) {
        User u = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        return new UserResponse(u.getId(), u.getName(), u.getAddress(), u.getName(), u.getEmail());
    }

    @Transactional
    public UserResponse updateUser(UUID userId, UpdateUserRequest req) {
        User u = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        if (req.name() != null) u.setName(req.name());
        if (req.address() != null) u.setAddress(req.address());
        if (req.phoneNumber() != null) u.setPhoneNumber(req.phoneNumber());
        if (req.email() != null) u.setEmail(req.email());
        userRepository.saveAndFlush(u);
        return new UserResponse(u.getId(), u.getName(), u.getAddress(), u.getPhoneNumber(), u.getEmail());
    }

    public void deleteUser(UUID userId, String userName) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // Check if the user making the request is the same user
        if (!user.getName().equals(userName)) {
            throw new ForbiddenActionException("You are not allowed to delete this user");
        }

        // Check if the user has a bank account
        boolean hasBankAccount = accountRepository.existsByUserId(userId);
        if (hasBankAccount) {
            throw new ConflictException("User has an associated bank account and cannot be deleted");
        }

        userRepository.delete(user);
    }
}
