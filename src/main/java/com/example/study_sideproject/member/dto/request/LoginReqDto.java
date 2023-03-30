package com.example.study_sideproject.member.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginReqDto {

    @NotNull
    private String email;

    @NotNull
    private String password;
}

