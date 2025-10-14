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
public class PostContoller {
}
