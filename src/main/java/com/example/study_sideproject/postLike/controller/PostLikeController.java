package com.example.study_sideproject.postLike.controller;

import com.example.study_sideproject.postLike.service.PostLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostLikeController {
    private final PostLikeService postLikeService;

    // 게시글 좋아요
    @PostMapping("/posts/{postId}/like")
    public ResponseEntity<Void> createPostLike(@PathVariable Long postId) {
        postLikeService.createPostLike(postId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
