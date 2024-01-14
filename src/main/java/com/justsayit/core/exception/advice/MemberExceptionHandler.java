package com.justsayit.core.exception.advice;

import com.justsayit.core.template.response.BaseResponse;
import com.justsayit.core.template.response.ResponseCode;
import com.justsayit.member.exception.AlreadyExistsMemberException;
import com.justsayit.member.exception.InvalidNicknameLengthException;
import com.justsayit.member.exception.NoMemberException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MemberExceptionHandler {

    @ExceptionHandler(NoMemberException.class)
    public ResponseEntity<BaseResponse<Object>> noMemberException(NoMemberException e) {
        return ResponseEntity.badRequest()
                .body(BaseResponse.ofFail(ResponseCode.NO_MEMBER));
    }

    @ExceptionHandler(AlreadyExistsMemberException.class)
    public ResponseEntity<BaseResponse<Object>> noMemberException(AlreadyExistsMemberException e) {
        return ResponseEntity.badRequest()
                .body(BaseResponse.ofFail(ResponseCode.ALREADY_EXISTS_MEMBER));
    }

    @ExceptionHandler(InvalidNicknameLengthException.class)
    public ResponseEntity<BaseResponse<Object>> noMemberException(InvalidNicknameLengthException e) {
        return ResponseEntity.badRequest()
                .body(BaseResponse.ofFail(ResponseCode.INVALID_NICKNAME_LENGTH));
    }
}
