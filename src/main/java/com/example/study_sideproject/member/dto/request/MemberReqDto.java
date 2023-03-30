package com.example.study_sideproject.member.dto.request;

import com.example.study_sideproject.member.domain.Member;
import com.example.study_sideproject.member.dto.AuthorityDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.Set;
import java.util.stream.Collectors;

@Data
@Getter
@Setter
@Builder
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

    private Set<AuthorityDto> authorityDtoSet;

    public static MemberReqDto from(Member member) {
        if (member == null) return null;

        return MemberReqDto.builder()
                .email(member.getEmail())
                .authorityDtoSet(member.getAuthorities().stream()
                        .map(authority -> AuthorityDto.builder().authorityName(authority.getAuthorityName()).build())
                        .collect(Collectors.toSet()))
                .build();
    }
}
