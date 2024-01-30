package com.justsayit.core.exception.advice;

import com.justsayit.core.template.response.BaseResponse;
import com.justsayit.core.template.response.ResponseCode;
import com.justsayit.story.exception.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class StoryExceptionHandler {

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

    @ExceptionHandler(InvalidBodyTextException.class)
    public ResponseEntity<BaseResponse<Object>> invalidBodyTextLengthException(InvalidBodyTextException e) {
        return ResponseEntity.badRequest()
                .body(BaseResponse.ofFail(ResponseCode.INVALID_BODY_TEXT));
    }

    @ExceptionHandler(AlreadyDeletedStoryException.class)
    public ResponseEntity<BaseResponse<Object>> alreadyDeletedStoryException(AlreadyDeletedStoryException e) {
        return ResponseEntity.badRequest()
                .body(BaseResponse.ofFail(ResponseCode.ALREADY_DELETED_STORY));
    }

    @ExceptionHandler(NoStoryException.class)
    public ResponseEntity<BaseResponse<Object>> noStoryException(NoStoryException e) {
        return ResponseEntity.badRequest()
                .body(BaseResponse.ofFail(ResponseCode.NO_STORY));
    }

    @ExceptionHandler(NotMyStoryException.class)
    public ResponseEntity<BaseResponse<Object>> notMyStoryException(NotMyStoryException e) {
        return ResponseEntity.badRequest()
                .body(BaseResponse.ofFail(ResponseCode.NOT_MY_STORY));
    }
}
