package com.justsayit.core.jwt.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.justsayit.core.template.ErrorMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper;

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException)
            throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        ErrorMessage errorMessage = null;
        String exception = (String) request.getAttribute("exception");
        if (exception == null) {
            errorMessage = getErrorMessage("AUTH-401", "인증되지 않은 사용자입니다.");
        } else if (exception.equals("ExpiredJwtException")) {
            errorMessage = getErrorMessage("AUTH-410", "토큰이 만료되었습니다. 토큰 재발급을 받아야 합니다.");
        }
        try {
            writer.write(objectMapper.writeValueAsString(errorMessage));
        } catch (Exception ignored) {
            log.error("에러 메시지 작성 중 예외 발생");
        } finally {
            if (writer != null) {
                writer.flush();
                writer.close();
            }
        }
        response.getWriter().write(objectMapper.writeValueAsString(errorMessage));
    }

    private ErrorMessage getErrorMessage(String errorCode, String message) {
        return ErrorMessage.builder().errorCode(errorCode).message(message).build();
    }
}
