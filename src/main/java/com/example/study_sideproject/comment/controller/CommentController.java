package com.example.study_sideproject.comment.controller;

import com.example.study_sideproject.comment.dto.CommentReqDto;
import com.example.study_sideproject.comment.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    //댓글 수정
    @PutMapping("/comments/{commentId}")
    public ResponseEntity<CommentReqDto> updateComment(@PathVariable Long commentId,
                                                             @RequestBody @Valid CommentReqDto commentReqDto){
        commentService.updateComment(commentId, commentReqDto);
        return ResponseEntity.ok().body(null);
    }
}
