package com.example.study_sideproject.member.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberReqDto {

    @Schema(description = "유저이메일", example = "test123@gmail.com")
    @Pattern(regexp = EMAIL_REGEX, message = "이메일 형식으로 입력해 주세요.")
    private String email;

    @Schema(description = "패스워드", example = "sparta123!")
    @Pattern(regexp = PASSWORD_REGEX, message = "비밀번호는 소문자, 숫자, 특수문자를 필수로 포함한 8-16자 이어야 합니다. ")
    private String password;

    @Schema(description = "패스워드체크", example = "sparta123!")
    @NotBlank(message = "패스워드 확인을 입력해 주세요.")
    private String passwordCheck;



    public static final String EMAIL_REGEX = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$"; // 이메일
    public static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[0-9])(?=.*[$@$!%*#?&])[A-Za-z[0-9]$@$!%*#?&]{8,16}$"; // 소문자, 숫자, 특수문자

}
