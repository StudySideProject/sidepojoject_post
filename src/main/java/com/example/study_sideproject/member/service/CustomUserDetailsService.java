package com.example.study_sideproject.member.service;

import com.example.study_sideproject.global.exception.CustomException;
import com.example.study_sideproject.global.exception.ErrorCode;
import com.example.study_sideproject.member.domain.Member;
import com.example.study_sideproject.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Component("userDetailsService")
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
   private final MemberRepository memberRepository;

   @Override
   @Transactional
   public UserDetails loadUserByUsername(String email) throws CustomException {
      Member member = memberRepository.findByEmail(email).orElseThrow(() -> new CustomException(ErrorCode.ID_NOT_EXIST));
      return createUserDetails(member);
   }
   private UserDetails createUserDetails(Member member){
      GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("member");

      return new User(
              member.getEmail(),
              member.getPassword(),
              Collections.singleton(grantedAuthority)
      );
   }
}
