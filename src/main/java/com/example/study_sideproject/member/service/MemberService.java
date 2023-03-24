package com.example.study_sideproject.member.service;

import com.example.study_sideproject.global.exception.CustomException;
import com.example.study_sideproject.global.exception.ErrorCode;
import com.example.study_sideproject.member.domain.Member;
import com.example.study_sideproject.member.dto.request.SignUpMemberReqDto;
import com.example.study_sideproject.member.exception.customexception.InvalidIDFormException;
import com.example.study_sideproject.member.exception.customexception.InvalidPasswordFormException;
import com.example.study_sideproject.member.exception.customexception.MemberEamilAlreadyExistsException;
import com.example.study_sideproject.member.exception.customexception.WrongPasswordCornfirmException;
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
	public void signup(SignUpMemberReqDto signUpReqDto) {

		validateMemberSignUpInfo(signUpReqDto);
		signUpReqDto.setPassword(passwordEncoder.encode(signUpReqDto.getPassword()));

		Member member = Member.builder()
				.email(signUpReqDto.getEmail())
				.password(passwordEncoder.encode(signUpReqDto.getPassword()))
				.build();
		memberRepository.save(member);

	}

	private void validateMemberSignUpInfo(SignUpMemberReqDto signUpReqDto) {

		if(memberRepository.existsByEmail(signUpReqDto.getEmail()))
			throw new MemberEamilAlreadyExistsException();
		if(!isValidUserEamil(signUpReqDto.getEmail()))
			throw new InvalidIDFormException();
		if(!isValidPassword(signUpReqDto.getPassword()))
			throw new InvalidPasswordFormException();
		if(!signUpReqDto.getPassword().equals(signUpReqDto.getPasswordCheck())){
			throw new WrongPasswordCornfirmException();
		}

	}


	private boolean isValidUserEamil(String email) {
		String regex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
		return email.matches(regex);
	}

	private boolean isValidPassword(String password) {
		String regex = "^(?=.*[a-z])(?=.*[0-9])(?=.*[$@$!%*#?&])[A-Za-z[0-9]$@$!%*#?&]{8,16}$";
		return password.matches(regex);
	}

}
