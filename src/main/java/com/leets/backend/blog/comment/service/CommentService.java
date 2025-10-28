package com.leets.backend.blog.comment.service;

import com.leets.backend.blog.comment.controller.dto.request.CommentRequest;
import com.leets.backend.blog.comment.controller.dto.response.CommentResponse;
import com.leets.backend.blog.comment.entity.Comment;
import com.leets.backend.blog.comment.exception.CommentNotFoundException;
import com.leets.backend.blog.comment.exception.UnauthorizedCommentAccessException;
import com.leets.backend.blog.comment.repository.CommentRepository;
import com.leets.backend.blog.post.entity.Post;
import com.leets.backend.blog.post.entity.User;
import com.leets.backend.blog.post.exception.PostNotFoundException;
import com.leets.backend.blog.post.exception.UserNotFoundException;
import com.leets.backend.blog.post.repository.PostRepository;
import com.leets.backend.blog.post.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public CommentService(CommentRepository commentRepository, PostRepository postRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    // 댓글 등록
    public CommentResponse createComment(CommentRequest commentRequest, Long postId, Long userId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("게시글을 찾을 수 없습니다."));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("이용자를 찾을 수 없습니다."));

        Comment comment = Comment.of(post, user, commentRequest.getContent());
        return CommentResponse.from(commentRepository.save(comment));
    }

    // 댓글 조회
    @Transactional(readOnly = true)
    public List<CommentResponse> getCommentsByPostId(Long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments.stream()
                .map(CommentResponse::from)
                .toList();
    }

    // 댓글 수정
    public CommentResponse updateComment(Long commentId, Long userId, String newContent) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(CommentNotFoundException::new);

        if(!comment.getUser().getId().equals(userId)){
            throw new UnauthorizedCommentAccessException();
        }

        comment.updateContent(newContent);
        return CommentResponse.from(commentRepository.save(comment));
    }

    // 댓글 삭제
    public void deleteComment(Long commentId, Long userId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(CommentNotFoundException::new);

        if(!comment.getUser().getId().equals(userId))
            throw new UnauthorizedCommentAccessException();

        commentRepository.delete(comment);
    }
}
