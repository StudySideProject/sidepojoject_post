package com.example.study_sideproject.member.controller;

import com.example.study_sideproject.member.dto.request.MemberReqDto;
import com.example.study_sideproject.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MemberController {

	private final MemberService memberService;

	// 회원가입
	@PostMapping("/signup")
	public ResponseEntity<Void> registerMember(@RequestBody @Valid MemberReqDto memberReqDto){
		memberService.signup(memberReqDto);
		System.out.println("회원가입완료");
		return ResponseEntity.status(HttpStatus.OK.value()).body(null);
	}
}
