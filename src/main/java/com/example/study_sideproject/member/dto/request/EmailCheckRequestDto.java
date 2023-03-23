package com.example.study_sideproject.member.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailCheckRequestDto {
    @NotBlank(message = "아이디를 입력해주세요.")
    @Email(message = "이메일 형식만 가능합니다")
    private String email;
}
