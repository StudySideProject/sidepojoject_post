package com.example.study_sideproject.member.controller;

import com.example.study_sideproject.global.jwt.JwtFilter;
import com.example.study_sideproject.global.jwt.TokenProvider;
import com.example.study_sideproject.member.dto.request.EmailCheckRequestDto;
import com.example.study_sideproject.member.dto.request.LoginReqDto;
import com.example.study_sideproject.member.dto.request.MemberReqDto;
import com.example.study_sideproject.member.service.MemberService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "멤버 컨트롤러")
@RequiredArgsConstructor
@RestController
public class MemberController {

	private final MemberService memberService;
	private final TokenProvider tokenProvider;
	private final AuthenticationManagerBuilder authenticationManagerBuilder;


	// 회원가입
	@PostMapping("/signup")
	public ResponseEntity<Void> registerMember(@RequestBody @Valid MemberReqDto memberReqDto){
		memberService.signup(memberReqDto);
		return ResponseEntity.status(HttpStatus.OK.value()).body(null);
	}

	// 로그인
	@PostMapping("/login")
	public ResponseEntity<Void> authorize(@Valid @RequestBody LoginReqDto loginReqDto) {
		memberService.validateLogInInfo(loginReqDto);

		UsernamePasswordAuthenticationToken authenticationToken =
				new UsernamePasswordAuthenticationToken(loginReqDto.getEmail(), loginReqDto.getPassword());

		Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = tokenProvider.createToken(authentication);

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

		return new ResponseEntity<>(null, httpHeaders, HttpStatus.OK);
	}


    //이메일 중복 확인
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/emailcheck")
    public ResponseEntity<Void> emailCheck(@Valid @RequestBody EmailCheckRequestDto emailCheckRequestDto){
        memberService.emailCheck(emailCheckRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
