package com.example.study_sideproject.member.service;

import com.example.study_sideproject.global.exception.ErrorCode;
import com.example.study_sideproject.global.exception.GlobalException;
import com.example.study_sideproject.member.domain.Member;
import com.example.study_sideproject.member.dto.request.MemberReqDto;
import com.example.study_sideproject.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;

	//회원가입
	@Transactional
	public void signup(MemberReqDto memberReqDto) {

		// Error : 이미 이 이메일을 사용하는 유저가 존재하는 경우
		if(memberRepository.existsByEmail(memberReqDto.getEmail())){
			throw new IllegalStateException("이미 사용중인 이메일입니다.");
		}

		// Error : 비밀번호, 비밀번호확인이 일치하지 않는 경우
		if(!memberReqDto.getPassword().equals(memberReqDto.getPasswordCheck())){
			throw new IllegalStateException("비밀번호가 일치하지 않습니다.");
		}

		Member member = Member.builder()
				.email(memberReqDto.getEmail())
				.password(passwordEncoder.encode(memberReqDto.getPassword()))
				.build();
		memberRepository.save(member);

	}

}
