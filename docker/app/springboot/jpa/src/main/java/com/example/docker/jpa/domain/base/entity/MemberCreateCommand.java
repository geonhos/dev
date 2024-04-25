package com.example.docker.jpa.domain.base.entity;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class MemberCreateCommand {

    @NotBlank
    private String name;

    public static MemberCreateCommand of(String name) {
        MemberCreateCommand command = new MemberCreateCommand();
        command.name = name;
        return command;
    }

    public Member toEntity() {
        return Member.of(name);
    }
}
