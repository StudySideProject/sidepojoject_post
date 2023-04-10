package com.example.study_sideproject.post.dto.response;

import com.example.study_sideproject.comment.dto.CommentResDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostResponseDto {

	private Long id;
	private String title;
	private String content;
	private String email;
	private LocalDateTime modifiedAt;
	private List<CommentResDto> comments;
}
