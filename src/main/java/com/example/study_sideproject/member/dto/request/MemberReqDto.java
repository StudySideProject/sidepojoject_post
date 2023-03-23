package com.example.study_sideproject.member.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberReqDto {

	@NotBlank(message = "email은 공백일 수 없습니다.")
	@Pattern(regexp = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$", // 이메일
			 message = "이메일아이디 양식을 확인해주세요.")
	private String email;

	@NotBlank(message = "Password는 공백일 수 없습니다.")
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[0-9])(?=.*[$@$!%*#?&])[A-Za-z[0-9]$@$!%*#?&]{8,16}$", // 소문자, 숫자, 특수문자
			 message = "패스워드 양식을 확인해주세요.")
	private String password;

	@NotBlank
	private String passwordCheck;
}
