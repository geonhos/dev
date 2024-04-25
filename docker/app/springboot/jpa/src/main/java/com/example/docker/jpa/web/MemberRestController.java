package com.example.docker.jpa.web;

import com.example.docker.jpa.domain.base.entity.MemberCreateCommand;
import com.example.docker.jpa.domain.base.entity.MemberUpdateCommand;
import com.example.docker.jpa.domain.base.service.MemberService;
import com.example.docker.jpa.web.reqres.CreateRequest;
import com.example.docker.jpa.web.reqres.DeleteRequest;
import com.example.docker.jpa.web.reqres.Response;
import com.example.docker.jpa.web.reqres.UpdateRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberRestController {

    private final MemberService memberService;

    @GetMapping
    public ResponseEntity<?> getMembers() {
        List<Response> response = memberService.findAll();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<?> getMember(@PathVariable Long memberId) {
        Response response = memberService.find(memberId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createMember(@RequestBody @Valid CreateRequest request) {
        Long id = memberService.create(MemberCreateCommand.of(request.getName()));
        Response response = memberService.find(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateMember(@RequestBody @Valid UpdateRequest request) {
        memberService.update(MemberUpdateCommand.of(request.getId(), request.getName()));
        Response response = memberService.find(request.getId());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/delete")
    public ResponseEntity<?> deleteMember(@RequestBody @Valid DeleteRequest request) {
        memberService.delete(request.getId());
        return ResponseEntity.ok().build();
    }

}
