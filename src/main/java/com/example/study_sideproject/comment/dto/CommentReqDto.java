package com.example.study_sideproject.comment.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentReqDto {

    @Schema(description = "부모댓글 아이디", example = "1")
    private Long parentId;

    @Schema(description = "코멘트 컨텐트", example = "댓글 내용내용내용내용")
    @Size(min = 3, max = 100) // 유효성 검사를 위해 사용
    private String content;
}
