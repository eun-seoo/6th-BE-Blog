package com.leets.backend.blog.user.controller.dto.response;

import com.leets.backend.blog.user.entity.User;

public class UserResponse {
    private String email;
    private String nickname;

    public UserResponse(String email, String nickname) {
        this.email = email;
        this.nickname = nickname;
    }

    public static UserResponse from(User user) {
        return new UserResponse(user.getEmail(), user.getNickname());
    }

    public String getEmail() {
        return email;
    }

    public String getNickname() {
        return nickname;
    }
}
