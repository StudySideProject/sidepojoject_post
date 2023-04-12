package com.example.study_sideproject.comment.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentUpdateReqDto {

    @Size(min = 3, max = 100) // 유효성 검사를 위해 사용
    private String content;
}