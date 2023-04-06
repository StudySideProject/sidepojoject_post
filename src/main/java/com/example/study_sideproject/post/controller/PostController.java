package com.example.study_sideproject.post.controller;

import com.example.study_sideproject.member.domain.Member;
import com.example.study_sideproject.post.dto.request.PostReqDto;
import com.example.study_sideproject.post.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostController {

	private final PostService postService;
	// 게시글 작성
	@PostMapping("/posts")
	public ResponseEntity<Void> createPost(@RequestBody PostReqDto postReqDto) {
		postService.createPost(postReqDto);
		return ResponseEntity.status(HttpStatus.OK.value()).body(null);}

}
