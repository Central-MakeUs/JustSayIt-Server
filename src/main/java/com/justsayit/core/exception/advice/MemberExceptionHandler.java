package com.justsayit.core.exception.advice;

import com.justsayit.core.template.response.BaseResponse;
import com.justsayit.core.template.response.ResponseCode;
import com.justsayit.member.exception.*;
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

    @ExceptionHandler(InvalidAppleIdentityToken.class)
    public ResponseEntity<BaseResponse<Object>> invalidAppleIdentityToken(InvalidAppleIdentityToken e) {
        return ResponseEntity.badRequest()
                .body(BaseResponse.ofFail(ResponseCode.INVALID_APPLE_IDENTITY_TOKEN));
    }

    @ExceptionHandler(FailToVerifyAppleIdentityToken.class)
    public ResponseEntity<BaseResponse<Object>> failToVerifyAppleIdentityToken(FailToVerifyAppleIdentityToken e) {
        return ResponseEntity.badRequest()
                .body(BaseResponse.ofFail(ResponseCode.FAIL_TO_VERIFY_APPLE_IDENTITY_TOKEN));
    }

    @ExceptionHandler(FailToGetApplePublicKey.class)
    public ResponseEntity<BaseResponse<Object>> failToGetApplePublicKey(FailToGetApplePublicKey e) {
        return ResponseEntity.badRequest()
                .body(BaseResponse.ofFail(ResponseCode.FAIL_TO_GET_APPLE_PUBLIC_KEY));
    }
}
