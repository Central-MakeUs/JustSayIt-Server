package com.justsayit.core.template;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorMessage {

    private String errorCode;
    private String message;
}
