package com.example.study_sideproject.member.service;

import com.example.study_sideproject.global.exception.CustomException;
import com.example.study_sideproject.global.exception.ErrorCode;
import com.example.study_sideproject.member.domain.Member;
import com.example.study_sideproject.member.dto.request.MemberReqDto;
import com.example.study_sideproject.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
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
			throw new CustomException(ErrorCode.DUPLICATE_EMAIL);
		}

		// Error : 비밀번호, 비밀번호확인이 일치하지 않는 경우
		if(!memberReqDto.getPassword().equals(memberReqDto.getPasswordCheck())){
			throw new CustomException(ErrorCode.WRONG_PASSWORD_CONFIRM);
		}

		Member member = Member.builder()
				.email(memberReqDto.getEmail())
				.password(passwordEncoder.encode(memberReqDto.getPassword()))
				.build();
		memberRepository.save(member);

	}

}
