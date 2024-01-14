package com.justsayit.core.exception.advice;

import com.justsayit.core.template.response.BaseResponse;
import com.justsayit.core.template.response.ResponseCode;
import com.justsayit.infra.s3.exception.FailToUploadFileException;
import com.justsayit.infra.s3.exception.FileSizeOverflowException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class FileUploadExceptionHandler {

    @ExceptionHandler(FileSizeOverflowException.class)
    public ResponseEntity<BaseResponse<Object>> fileSizeOverflowException(FileSizeOverflowException e) {
        return ResponseEntity.badRequest()
                .body(BaseResponse.ofFail(ResponseCode.FILE_SIZE_OVERFLOW));
    }

    @ExceptionHandler(FailToUploadFileException.class)
    public ResponseEntity<BaseResponse<Object>> failToUploadFileException(FailToUploadFileException e) {
        return ResponseEntity.badRequest()
                .body(BaseResponse.ofFail(ResponseCode.FAIL_TO_UPLOAD_FILE));
    }
}
