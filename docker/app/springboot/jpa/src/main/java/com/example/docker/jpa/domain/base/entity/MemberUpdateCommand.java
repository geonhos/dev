package com.example.docker.jpa.domain.base.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class MemberUpdateCommand {

    @NotNull
    private Long id;

    @NotBlank
    private String name;

    public static MemberUpdateCommand of(Long id, String name) {
        MemberUpdateCommand command = new MemberUpdateCommand();
        command.id = id;
        command.name = name;
        return command;
    }

    public Member toEntity() {
        return Member.of(name);
    }

}
