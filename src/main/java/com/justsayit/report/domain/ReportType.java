package com.justsayit.report.domain;

import com.justsayit.report.exception.InvalidReportCodeException;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Getter
public enum ReportType {

    ADVERTISEMENT_SPAM("광고/도배글이에요.", "REPORT001"),
    PORNOGRAPHIC_CONTENT("음란물이에요.", "REPORT002"),
    ILLEGAL_INFORMATION("불법정보를 포함하고 있어요.", "REPORT003"),
    PROFANITY_HATE_DISCRIMINATION("욕설/혐오/차별적 표현이에요.", "REPORT004"),
    PERSONAL_INFORMATION_EXPOSURE("개인정보 노출 게시물이에요.", "REPORT005"),
    OFFENSIVE_EXPRESSIONS("불쾌한 표현이 있어요.", "REPORT006"),
    ;

    private final String message;
    private final String code;

    private static final Map<String, ReportType> BY_CODE = new HashMap<>();

    static {
        for (ReportType e : values()) {
            BY_CODE.put(e.code, e);
        }
    }

    public static ReportType valueOfCode(String code) {
        return Optional.ofNullable(BY_CODE.get(code))
                .orElseThrow(InvalidReportCodeException::new);
    }

    ReportType(String message, String code) {
        this.message = message;
        this.code = code;
    }
}
