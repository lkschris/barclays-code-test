package com.eaglebank.app.controller;

import com.eaglebank.app.model.dto.CreateUserRequest;
import com.eaglebank.app.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.eaglebank.app.model.dto.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody CreateUserRequest req) {
        return ResponseEntity.status(201).body(userService.createUser(req));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUser(@PathVariable UUID userId) {
        return ResponseEntity.ok(userService.getUser(userId));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable UUID userId, @Valid @RequestBody UpdateUserRequest req) {
        return ResponseEntity.ok(userService.updateUser(userId, req));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID userId, @Valid @RequestBody DeleteUserRequest req) {
        userService.deleteUser(userId, req.name());
        return ResponseEntity.noContent().build();
    }
}
