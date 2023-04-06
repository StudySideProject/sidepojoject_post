package com.example.study_sideproject.global.jwt;

import com.example.study_sideproject.global.exception.ErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        String exception = (String)request.getAttribute("exception");

        // 토큰 없는 경우
        if(exception == null) {
            setResponse(response, ErrorCode.NEED_TO_LOGIN);
        }
        // 잘못된 타입의 토큰인 경우
        else if(exception.equals(ErrorCode.WRONG_TYPE_TOKEN.getCode())) {
            setResponse(response, ErrorCode.WRONG_TYPE_TOKEN);
        }
        // 토큰 만료된 경우
        else if(exception.equals(ErrorCode.EXPIRED_TOKEN.getCode())) {
            setResponse(response, ErrorCode.EXPIRED_TOKEN);
        }
        // 지원되지 않는 토큰인 경우
        else if(exception.equals(ErrorCode.UNSUPPORTED_TOKEN.getCode())) {
            setResponse(response, ErrorCode.UNSUPPORTED_TOKEN);
        }
        // 잘못된 토큰인 경우
        else if(exception.equals(ErrorCode.WRONG_TOKEN.getCode())) {
            setResponse(response, ErrorCode.WRONG_TOKEN);
        }
        else if(exception.equals(ErrorCode.UNDEFINED_ERROR.getCode())) {
            setResponse(response, ErrorCode.UNDEFINED_ERROR);
        }
    }
    //한글 출력을 위해 getWriter() 사용
    private void setResponse(HttpServletResponse response, ErrorCode errorCode) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        JSONObject responseJson = new JSONObject();
        responseJson.put("code", errorCode.getCode());
        responseJson.put("errorMessage", errorCode.getMessage());

        response.getWriter().print(responseJson);
    }
}
