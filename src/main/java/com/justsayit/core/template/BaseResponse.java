package com.justsayit.core.template;

import lombok.Builder;
import lombok.Getter;

@Getter
public class BaseResponse<D> {

    private final String code;
    private final String message;
    private final D data;
    private static final String SUCCESS = "SUCCESS";
    private static final String FAIL = "FAIL";

    @Builder
    private BaseResponse(String code, String message, D data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 반환 데이터가 없는 성공 메시지 템플릿
     * @param code 상태 코드
     * @return 자기 자신을 반환
     * @param <D> null
     */
    public static <D> BaseResponse<D> ofSuccess(String code) {
        return new BaseResponse<>(code, SUCCESS, null);
    }

    /**
     * 반환 데이터가 있는 성공 메시지 템플릿
     * @param code 상태 코드
     * @param data 반환 데이터
     * @return 자기 자신을 반환
     * @param <D> 반환되는 객체
     */
    public static <D> BaseResponse<D> ofSuccess(String code, D data) {
        return new BaseResponse<>(code, SUCCESS, data);
    }

    /**
     * 예외를 반환하는 실패 메시지 템플릿
     * @param code 상태 코드
     * @return 자기 자신을 반환
     * @param <D> null
     */
    public static <D> BaseResponse<D> ofFail(String code, String cause) {
        return new BaseResponse<>(code, cause, null);
    }
}
