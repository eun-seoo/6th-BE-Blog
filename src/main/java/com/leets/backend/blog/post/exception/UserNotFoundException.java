package com.leets.backend.blog.post.exception;

import com.leets.backend.blog.common.exception.*;

public class UserNotFoundException extends CustomException {
    public UserNotFoundException(String message) {
        super(ErrorCode.USER_NOT_FOUND, message);
    }
}
