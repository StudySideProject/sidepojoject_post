package com.example.study_sideproject.member.controller;

import com.example.study_sideproject.member.dto.request.EmailCheckRequestDto;
import com.example.study_sideproject.member.dto.request.MemberReqDto;
import com.example.study_sideproject.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<Void> registerMember(@RequestBody @Valid MemberReqDto memberReqDto){
        memberService.signup(memberReqDto);
        return ResponseEntity.status(HttpStatus.OK.value()).body(null);
    }

    //이메일 중복 확인
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/emailcheck")
    public ResponseEntity<?> emailCheck(@RequestBody EmailCheckRequestDto emailCheckRequestDto){
        memberService.emailCheck(emailCheckRequestDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
