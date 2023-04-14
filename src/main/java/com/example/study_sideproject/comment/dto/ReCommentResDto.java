package com.example.study_sideproject.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReCommentResDto {
    private Long id;
    private Long parentId;
    private String commenter;
    private String content;
    private LocalDateTime modifiedAt;
}
