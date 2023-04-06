package com.example.study_sideproject.post.controller;

import com.example.study_sideproject.post.dto.request.PostReqDto;
import com.example.study_sideproject.post.dto.response.PostResponseDto;
import com.example.study_sideproject.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PostController {

	private final PostService postService;

	// 게시글 작성
	@PostMapping("/posts")
	public ResponseEntity<Void> createPost(@RequestBody PostReqDto postReqDto) {
		postService.createPost(postReqDto);
		return ResponseEntity.status(HttpStatus.OK.value()).body(null);}

	// 게시글 상세 조회
	@GetMapping("/posts/{id}")
	public ResponseEntity<PostResponseDto> getOnePost(@PathVariable("id") Long postId) {
		PostResponseDto postResponseDto = postService.getOnePost(postId);
		return ResponseEntity.status(HttpStatus.OK.value()).body(postResponseDto);
	}
}
