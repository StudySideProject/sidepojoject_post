package com.example.study_sideproject.comment.controller;

import com.example.study_sideproject.comment.dto.CommentReqDto;
import com.example.study_sideproject.comment.dto.ReCommentResDto;
import com.example.study_sideproject.comment.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<Void> updateComment(@PathVariable Long commentId,
                                              @RequestBody @Valid CommentReqDto commentReqDto){
        commentService.updateComment(commentId, commentReqDto);
        return ResponseEntity.ok().body(null);
    }

    // 댓글 삭제
    @DeleteMapping("comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    //대댓글 조회
    @GetMapping("comments/{parentId}")
    public ResponseEntity<List<ReCommentResDto>> getReComments(@PathVariable Long parentId) {
       return ResponseEntity.ok().body(commentService.getReComments(parentId));
    }
}
