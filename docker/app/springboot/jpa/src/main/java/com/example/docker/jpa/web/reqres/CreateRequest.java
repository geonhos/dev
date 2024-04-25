package com.example.docker.jpa.web.reqres;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateRequest {

    @NotBlank
    private String name;

}
