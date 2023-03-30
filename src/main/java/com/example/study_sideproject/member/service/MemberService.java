package com.example.study_sideproject.member.service;

import com.example.study_sideproject.member.domain.Member;
import com.example.study_sideproject.member.dto.request.MemberReqDto;
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
    public void signup(MemberReqDto memberReqDto) {

        validateMemberSignUpInfo(memberReqDto);

        Member member = Member.builder()
                .email(memberReqDto.getEmail())
                .password(passwordEncoder.encode(memberReqDto.getPassword()))
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
