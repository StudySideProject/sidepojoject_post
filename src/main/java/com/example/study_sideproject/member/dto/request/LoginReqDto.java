package com.example.study_sideproject.member.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginReqDto {

    @Schema(description = "유저이메일", example = "test123@gmail.com")
    @NotNull
    private String email;

    @Schema(description = "패스워드", example = "sparta123!")
    @NotNull
    private String password;
}

