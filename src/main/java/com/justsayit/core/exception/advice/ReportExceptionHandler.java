package com.justsayit.core.exception.advice;

import com.justsayit.core.template.response.BaseResponse;
import com.justsayit.core.template.response.ResponseCode;
import com.justsayit.report.exception.InvalidReportCodeException;
import com.justsayit.report.exception.ReportMyStoryException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ReportExceptionHandler {

    @ExceptionHandler(InvalidReportCodeException.class)
    public ResponseEntity<BaseResponse<Object>> invalidReportCodeException(InvalidReportCodeException e) {
        return ResponseEntity.badRequest()
                .body(BaseResponse.ofFail(ResponseCode.INVALID_REPORT_CODE));
    }

    @ExceptionHandler(ReportMyStoryException.class)
    public ResponseEntity<BaseResponse<Object>> reportMyStoryException(ReportMyStoryException e) {
        return ResponseEntity.badRequest()
                .body(BaseResponse.ofFail(ResponseCode.REPORT_MY_STORY));
    }
}
