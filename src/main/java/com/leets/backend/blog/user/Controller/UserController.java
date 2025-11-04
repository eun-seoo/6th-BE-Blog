package com.leets.backend.blog.user.controller;

import com.leets.backend.blog.common.response.ApiResponse;
import com.leets.backend.blog.user.controller.dto.request.UserSignupRequest;
import com.leets.backend.blog.user.controller.dto.response.UserResponse;
import com.leets.backend.blog.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
        public ResponseEntity<ApiResponse<UserResponse>> signUp(@Valid @RequestBody UserSignupRequest userSignupRequest) {

        UserResponse response = userService.signUp(userSignupRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.created(response));
    }

}
