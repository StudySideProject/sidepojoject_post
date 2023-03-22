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

		// Error : 비빌번호, 비빌번호확인 불일치
		if(!memberReqDto.getPassword().equals(memberReqDto.getPasswordConfirm())){
			throw new GlobalException(ErrorCode.BAD_PASSWORD_CONFIRM);
		}

		Member member = Member.builder()
				.email(memberReqDto.getEmail())
				.password(passwordEncoder.encode(memberReqDto.getPassword()))
				.build();
		memberRepository.save(member);

	}

}
