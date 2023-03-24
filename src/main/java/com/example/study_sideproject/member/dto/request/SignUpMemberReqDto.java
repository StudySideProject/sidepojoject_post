package com.example.study_sideproject.member.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpMemberReqDto {
	public static final String EMAIL_REGEX = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$"; // 이메일
	public static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[0-9])(?=.*[$@$!%*#?&])[A-Za-z[0-9]$@$!%*#?&]{8,16}$"; // 소문자, 숫자, 특수문자


	@NotBlank(message = "email은 공백일 수 없습니다.")
	@Pattern(regexp = EMAIL_REGEX, message = "이메일아이디 양식을 확인해주세요.")
	private String email;

	@NotBlank(message = "Password는 공백일 수 없습니다.")
	@Pattern(regexp = PASSWORD_REGEX, message = "패스워드 양식을 확인해주세요.")
	private String password;

	@NotBlank
	private String passwordCheck;
}
