package com.justsayit.core.exception.advice;

import com.justsayit.core.template.response.BaseResponse;
import com.justsayit.core.template.response.ResponseCode;
import com.justsayit.story.exception.EmptyMainContentException;
import com.justsayit.story.exception.InvalidEmotionCodeException;
import com.justsayit.story.exception.InvalidNumberOfImgException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class StoryExceptionHandler {

    @ExceptionHandler(EmptyMainContentException.class)
    public ResponseEntity<BaseResponse<Object>> emptyMainContentException(EmptyMainContentException e) {
        return ResponseEntity.badRequest()
                .body(BaseResponse.ofFail(ResponseCode.EMPTY_MAIN_CONTENT));
    }

    @ExceptionHandler(InvalidEmotionCodeException.class)
    public ResponseEntity<BaseResponse<Object>> invalidEmotionCodeException(InvalidEmotionCodeException e) {
        return ResponseEntity.badRequest()
                .body(BaseResponse.ofFail(ResponseCode.INVALID_EMOTION_CODE));
    }

    @ExceptionHandler(InvalidNumberOfImgException.class)
    public ResponseEntity<BaseResponse<Object>> invalidNumberOfImgException(InvalidNumberOfImgException e) {
        return ResponseEntity.badRequest()
                .body(BaseResponse.ofFail(ResponseCode.INVALID_NUMBER_OF_IMG));
    }
}
