package com.dev.java.swagger.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorResponse {
    @Schema(description = "에러 코드", example = "400")
    private final String code;

    @Schema(description = "에러 메시지", example = "에러 메시지")
    private final String error;

    public static ErrorResponse of(String code, String error) {
        return new ErrorResponse(code, error);
    }
}
