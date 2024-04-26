package com.example.docker.jpa.domain.base.service;

import com.example.docker.jpa.domain.base.entity.Member;
import com.example.docker.jpa.domain.base.entity.MemberCreateCommand;
import com.example.docker.jpa.domain.base.entity.MemberUpdateCommand;
import com.example.docker.jpa.env.MariaDBTestEnv;
import com.example.docker.jpa.web.reqres.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("MemberService 테스트")
class MemberServiceContainerTest extends MariaDBTestEnv {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    void setUp() {
        memberRepository.deleteAll();
    }

    @Test
    @DisplayName("member 조회 테스트")
    void findMemberTest() {
        // given
        Member member = Member.of("홍길동");
        Member createMember = memberRepository.save(member);

        // when
        Response findMember = memberService.find(createMember.getId());

        // then
        assertThat(findMember).isNotNull();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    @DisplayName("member 전체 조회 테스트")
    void findAllMemberTest() {
        // given
        memberRepository.saveAll(
                List.of(
                        Member.of("홍길동"),
                        Member.of("김길동")
                )
        );

        // when
        List<Response> findMembers = memberService.findAll();

        // then
        assertThat(findMembers).isNotNull();
        assertThat(findMembers.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("member 생성 테스트")
    void createMemberTest() {
        // given
        MemberCreateCommand command = MemberCreateCommand.of("홍길동");

        // when
        Long id = memberService.create(command);

        // then
        Member findMember = memberRepository.findById(id).orElseThrow();
        assertThat(findMember).isNotNull();
        assertThat(findMember.getName()).isEqualTo(command.getName());
    }

    @Test
    @DisplayName("member 수정 테스트")
    void updateMemberTest() {
        // given
        Member member = Member.of("홍길동");
        Member createMember = memberRepository.save(member);

        MemberUpdateCommand command = MemberUpdateCommand.of(createMember.getId(), "김길동");

        // when
        memberService.update(command);

        // then
        Member findMember = memberRepository.findById(createMember.getId()).orElseThrow();
        assertThat(findMember).isNotNull();
        assertThat(command.getName()).isEqualTo(findMember.getName());
    }

    @Test
    @DisplayName("member 삭제 테스트")
    void deleteMemberTest() {
        // given
        Member member = Member.of("홍길동");
        Member createMember = memberRepository.save(member);

        // when
        memberService.delete(createMember.getId());

        // then
        Optional<Member> findMember = memberRepository.findById(createMember.getId());
        assertThat(findMember.isEmpty()).isTrue();
    }

}