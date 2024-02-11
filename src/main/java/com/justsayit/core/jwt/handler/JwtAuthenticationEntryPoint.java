package com.justsayit.core.jwt.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.justsayit.core.template.response.BaseResponse;
import com.justsayit.core.template.response.ResponseCode;
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
        BaseResponse<Object> baseResponse = null;
        String exception = (String) request.getAttribute("exception");
        if (exception == null) {
            baseResponse = BaseResponse.ofFail(ResponseCode.UNAUTHENTICATED);
        } else if (exception.equals("ExpiredJwtException")) {
            baseResponse = BaseResponse.ofFail(ResponseCode.JWT_TOKEN_EXPIRED);
        }
        try {
            writer.write(objectMapper.writeValueAsString(baseResponse));
        } catch (Exception ignored) {
            log.error("에러 메시지 작성 중 예외 발생");
        } finally {
            if (writer != null) {
                writer.flush();
                writer.close();
            }
        }
        response.getWriter().write(objectMapper.writeValueAsString(baseResponse));
    }
}
