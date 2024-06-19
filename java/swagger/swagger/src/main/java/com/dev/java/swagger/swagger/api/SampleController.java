package com.dev.java.swagger.swagger.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Tag(name = "sample", description = "sample API")
@RestController
@RequestMapping("/api/v1/sample")
public class SampleController {

    @Operation(summary = "테스트 API", description = "테스트 API")
    @ApiResponse(description = "통신에 성공할 경우 success 를 응답받는다.")
    @GetMapping
    public ResponseEntity<String> get() {
        return ResponseEntity.ok("success");
    }

    @Operation(summary = "조회용 테스트 API", description = "id로 샘플을 조회하는 API")
    @Parameter(name = "id", description = "조회할 id", required = true)
    @ApiResponse(description = "요청한 id가 응답 메시지로 온다.")
    @GetMapping("/{id}")
    public ResponseEntity<String> getSample(@PathVariable String id) {
        return ResponseEntity.ok(id);
    }

    @Operation(summary = "수정용 테스트 API", description = "이름과 나이를 수정하는 API")
    @ApiResponse(
            responseCode = "200",
            description = "요청한 이름과 나이가 응답 메시지로 온다.",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = SampleResponse.class)
            )
    )
    @ApiResponse(
            responseCode = "400",
            description = "필수 값 확인 체크에 실패한 경우 400 에러가 발생한다.",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = ErrorResponse.class)
            )
    )
    @PostMapping
    public ResponseEntity<?> post(@RequestBody @Valid SampleRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(ErrorResponse.of(
                    "9999",
                    bindingResult.getAllErrors().getFirst().getDefaultMessage()
            ));
        }

        return ResponseEntity.ok(SampleResponse.success());
    }

}
