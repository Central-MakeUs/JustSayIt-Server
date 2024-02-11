package com.justsayit.core.jwt.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.justsayit.core.template.response.BaseResponse;
import com.justsayit.core.template.response.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
@RequiredArgsConstructor
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    private final ObjectMapper objectMapper;

    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException accessDeniedException)
            throws IOException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        BaseResponse<Object> baseResponse = BaseResponse.ofFail(ResponseCode.NO_ACCESS_PERMISSION);
        try {
            writer.write(objectMapper.writeValueAsString(baseResponse));
        } catch (Exception ignored) {
        } finally {
            if (writer != null) {
                writer.flush();
                writer.close();
            }
        }
        response.getWriter().write(objectMapper.writeValueAsString(baseResponse));
    }
}
