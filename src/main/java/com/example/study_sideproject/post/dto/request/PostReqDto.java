package com.example.study_sideproject.post.dto.request;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter // 필드를 private로 만들어 외부의 접근을 제한(캡슐화)한 후, Setter를 사용해 전달받은 값을 내부에서 가공해 필드에 넣어줄 수 있음
@NoArgsConstructor // 파라미터가 없는 기본 생성자 생성
@AllArgsConstructor // 모든 필드 값을 파라미터로 받는 생성자 생성
public class PostReqDto {

	@Size(min = 3, max = 20) // 유효성 검사를 위해 사용
	private String title;

	@Size(min = 10, max = 500)
	private String content;

}
