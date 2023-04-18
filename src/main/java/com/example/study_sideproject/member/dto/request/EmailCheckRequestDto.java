package com.example.study_sideproject.member.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmailCheckRequestDto {

    @Schema(description = "유저이메일", example = "test123@gmail.com")
    @NotBlank(message = "아이디를 입력해주세요.")
    @Email(message = "이메일 형식만 가능합니다")
    private String email;
}
