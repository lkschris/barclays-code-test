package com.eaglebank.app.controller;

import com.eaglebank.app.model.dto.CreateUserRequest;
import com.eaglebank.app.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.eaglebank.app.model.dto.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserResponse createUser(@Valid @RequestBody CreateUserRequest req) {
        return userService.createUser(req);
    }

    @GetMapping("/{userId}")
    public UserResponse getUser(@PathVariable UUID userId) {
        return userService.getUser(userId);
    }

    @PutMapping("/{userId}")
    public UserResponse updateUser(@PathVariable UUID userId, @Valid @RequestBody UpdateUserRequest req) {
        return userService.updateUser(userId, req);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Map<String, String>> deleteUser(@PathVariable UUID userId, @Valid @RequestBody DeleteUserRequest req) {
        userService.deleteUser(userId, req.name());
        Map<String, String> response = Map.of("message", "User has been successfully deleted");
        return ResponseEntity.ok(response);
    }
}
