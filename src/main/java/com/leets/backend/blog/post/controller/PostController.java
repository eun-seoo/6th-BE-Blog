package com.leets.backend.blog.post.controller;

import com.leets.backend.blog.common.response.ApiResponse;
import com.leets.backend.blog.post.controller.dto.request.PostRequest;
import com.leets.backend.blog.post.controller.dto.request.PostUpdateRequest;
import com.leets.backend.blog.post.controller.dto.response.PostResponse;
import com.leets.backend.blog.post.controller.dto.response.PostSummaryResponse;
import com.leets.backend.blog.post.service.PostService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<PostSummaryResponse>>> getAllPosts() {
        return ResponseEntity.ok(ApiResponse.ok(postService.getAllPosts()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<PostResponse>> getPostById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.ok(postService.getPostById(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<PostResponse>> createPost(@Valid @RequestBody PostRequest request) {
        return ResponseEntity.ok(ApiResponse.created(postService.createPost(request)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PostResponse>> updatePost(
            @PathVariable Long id,
            @RequestParam Long userId,
            @Valid @RequestBody PostUpdateRequest request) {
        return ResponseEntity.ok(ApiResponse.ok(postService.updatePost(id, userId, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePost(
            @PathVariable Long id,
            @RequestParam Long userId) {
        postService.deletePost(id, userId);
        return ResponseEntity.ok(ApiResponse.ok(null));
    }
}
