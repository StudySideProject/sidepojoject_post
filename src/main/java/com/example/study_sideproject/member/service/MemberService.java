package com.example.study_sideproject.member.service;

import com.example.study_sideproject.global.exception.CustomException;
import com.example.study_sideproject.global.exception.ErrorCode;
import com.example.study_sideproject.global.jwt.SecurityUtil;
import com.example.study_sideproject.member.domain.Authority;
import com.example.study_sideproject.member.domain.Member;
import com.example.study_sideproject.member.dto.request.MemberReqDto;
import com.example.study_sideproject.member.exception.customexception.MemberEamilAlreadyExistsException;
import com.example.study_sideproject.member.exception.customexception.WrongPasswordCornfirmException;
import com.example.study_sideproject.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;


@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    //회원가입
    @Transactional
    public void signup(MemberReqDto memberReqDto) {

        validateMemberSignUpInfo(memberReqDto);

        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        Member member = Member.builder()
                .email(memberReqDto.getEmail())
                .password(passwordEncoder.encode(memberReqDto.getPassword()))
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();
        memberRepository.save(member);

    }

    private void validateMemberSignUpInfo(MemberReqDto memberReqDto) {
        if (memberRepository.existsByEmail(memberReqDto.getEmail()))
            throw new MemberEamilAlreadyExistsException();
        if (!memberReqDto.getPassword().equals(memberReqDto.getPasswordCheck())) {
            throw new WrongPasswordCornfirmException();
        }

    }

}
