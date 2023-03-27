package com.example.study_sideproject.member.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberReqDto {
	public static final String EMAIL_REGEX = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$"; // 이메일
	public static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[0-9])(?=.*[$@$!%*#?&])[A-Za-z[0-9]$@$!%*#?&]{8,16}$"; // 소문자, 숫자, 특수문자


	@Pattern(regexp = EMAIL_REGEX, message = "이메일 형식으로 입력해 주세요.")
	private String email;

	@Pattern(regexp = PASSWORD_REGEX, message = "비밀번호는 소문자, 숫자, 특수문자를 필수로 포함한 8-16자 이어야 합니다. ")
	private String password;

	@NotBlank(message = "패스워드 확인을 입력해 주세요.")
	private String passwordCheck;
}
