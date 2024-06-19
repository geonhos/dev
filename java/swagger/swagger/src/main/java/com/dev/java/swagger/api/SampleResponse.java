package com.dev.java.swagger.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class SampleResponse {
    @Schema(description = "결과 코드", example = "0000")
    private final String code;

    @Schema(description = "결과 메시지", example = "성공")
    private final String message;

    public static SampleResponse success() {
        return new SampleResponse("0000", "성공");
    }
}
