package com.leets.backend.blog.post.controller.dto.response;

import com.leets.backend.blog.post.entity.Post;

public class PostSummaryResponse {
    private Long id;
    private String title;
    private String content;

    private PostSummaryResponse(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public static PostSummaryResponse from(Post post) {
        return new PostSummaryResponse(post.getId(), post.getTitle(),  post.getContent());
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
}
