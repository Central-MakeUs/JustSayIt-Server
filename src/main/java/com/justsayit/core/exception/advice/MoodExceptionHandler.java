package com.justsayit.core.exception.advice;

import com.justsayit.core.template.response.BaseResponse;
import com.justsayit.core.template.response.ResponseCode;
import com.justsayit.mood.exception.InvalidMoodCodeException;
import com.justsayit.mood.exception.RecentSavedMoodExistsException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MoodExceptionHandler {

    @ExceptionHandler(InvalidMoodCodeException.class)
    public ResponseEntity<BaseResponse<Object>> invalidMoodCodeException(InvalidMoodCodeException e) {
        return ResponseEntity.badRequest()
                .body(BaseResponse.ofFail(ResponseCode.INVALID_MOOD_CODE));
    }

    @ExceptionHandler(RecentSavedMoodExistsException.class)
    public ResponseEntity<BaseResponse<Object>> recentSavedMoodExistsException(RecentSavedMoodExistsException e) {
        return ResponseEntity.badRequest()
                .body(BaseResponse.ofFail(ResponseCode.RECENT_SAVED_MOOD_EXISTS));
    }
}
