package com.example.docker.jpa.web.reqres;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateRequest {

    @NotNull
    private Long id;

    @NotBlank
    private String name;

}
