package com.leets.backend.blog.user.service;

import com.leets.backend.blog.common.exception.CustomException;
import com.leets.backend.blog.common.exception.ErrorCode;
import com.leets.backend.blog.user.controller.dto.request.UserSignupRequest;
import com.leets.backend.blog.user.controller.dto.response.UserResponse;
import com.leets.backend.blog.user.entity.User;
import com.leets.backend.blog.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse signUp(UserSignupRequest userSignupRequest) {
        // 이메일 중복 체크
        userRepository.findByEmail(userSignupRequest.getEmail())
                .ifPresent(user -> {
                    throw new CustomException(ErrorCode.DUPLICATED_EMAIL, "이미 존재하는 이메일입니다.");
                });

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(userSignupRequest.getPassword());

        User user = new User(userSignupRequest.getEmail(), encodedPassword, userSignupRequest.getNickname());

        User savedUser = userRepository.save(user);

        return new UserResponse(savedUser.getEmail(), savedUser.getNickname());
    }
}