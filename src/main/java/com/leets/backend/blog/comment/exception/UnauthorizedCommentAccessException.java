package com.leets.backend.blog.comment.exception;

import com.leets.backend.blog.common.exception.CustomException;
import com.leets.backend.blog.common.exception.ErrorCode;

public class UnauthorizedCommentAccessException extends CustomException {
    public UnauthorizedCommentAccessException() {
        super(ErrorCode.UNAUTHORIZED_COMMENT_ACCESS, ErrorCode.UNAUTHORIZED_COMMENT_ACCESS.getMessage());
    }
}
