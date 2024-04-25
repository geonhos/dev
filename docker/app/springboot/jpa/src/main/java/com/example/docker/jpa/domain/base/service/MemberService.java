package com.example.docker.jpa.domain.base.service;

import com.example.docker.jpa.domain.base.entity.Member;
import com.example.docker.jpa.domain.base.entity.MemberCreateCommand;
import com.example.docker.jpa.domain.base.entity.MemberUpdateCommand;
import com.example.docker.jpa.web.reqres.Response;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Service
@Validated
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Response find(@NotNull Long id) {
        Optional<Member> member = memberRepository.findById(id);
        return member
                .map(value -> Response.of(value.getName()))
                .orElseGet(Response::new);
    }

    public List<Response> findAll() {
        List<Member> members = memberRepository.findAll();
        return members.stream()
                .map(value -> Response.of(value.getName()))
                .toList();
    }

    public Long create(@NotNull @Valid MemberCreateCommand command) {
        return memberRepository.save(command.toEntity()).getId();
    }

    public void update(@NotNull @Valid MemberUpdateCommand command) {
        Member member = memberRepository.findById(command.getId()).orElseThrow(() -> new IllegalArgumentException("Member not found"));
        member.update(command.toEntity());
    }

    public void delete(@NotNull Long id) {
        memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Member not found"));
        memberRepository.deleteById(id);
    }
}
