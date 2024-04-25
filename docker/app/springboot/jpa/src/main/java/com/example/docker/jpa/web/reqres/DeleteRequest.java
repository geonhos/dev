package com.example.docker.jpa.web.reqres;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DeleteRequest {

    @NotNull
    private Long id;

}
