package com.example.study_sideproject.comment.controller;

import com.example.study_sideproject.comment.dto.CommentReqDto;
import com.example.study_sideproject.comment.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    // 댓글 생성
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<Void> createComment(@PathVariable Long postId,
                                              @RequestBody @Valid CommentReqDto commentReqDto) {
        commentService.createComment(postId, commentReqDto);
        return ResponseEntity.ok().body(null);
    }
}
