package com.justsayit.aws;

import com.justsayit.core.security.auth.AuthServiceHelper;
import com.justsayit.core.template.response.BaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    @GetMapping("/health")
    public String checkHealth() {
        return "healthy";
    }

    @GetMapping("/temp")
    public ResponseEntity<BaseResponse<Boolean>> tempApi() {
        Long memberId = AuthServiceHelper.getMemberId();
        return ResponseEntity.ok(BaseResponse.ofSuccess(true));
    }
}
