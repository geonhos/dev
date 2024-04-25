package com.example.docker.jpa.domain.base.service;

import com.example.docker.jpa.domain.base.entity.Member;
import com.example.docker.jpa.domain.base.entity.MemberCreateCommand;
import com.example.docker.jpa.domain.base.entity.MemberUpdateCommand;
import com.example.docker.jpa.web.reqres.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@DisplayName("MemberService 테스트")
@ExtendWith(MockitoExtension.class)
class MemberServiceMockitoTest {

    @InjectMocks
    private MemberService memberService;

    @Mock
    private MemberRepository memberRepository;

    @Test
    @DisplayName("member 조회 테스트")
    void findMemberTest() {
        Member member = createFakeMember();

        // mocking
        given(memberRepository.findById(member.getId()))
                .willReturn(Optional.of(member));

        // when
        Response findMember = memberService.find(member.getId());

        // then
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    @DisplayName("member 리스트 조회 테스트")
    void findAllMemberTest() {
        Member member = createFakeMember();

        // mocking
        given(memberRepository.findAll())
                .willReturn(List.of(member));

        // when
        List<Response> findMembers = memberService.findAll();

        // then
        assertThat(findMembers).isNotNull();
        assertThat(findMembers).hasSize(1);
    }

    @Test
    @DisplayName("member 생성 테스트")
    void createMemberTest() {
        // given
        Member member = createFakeMember();
        MemberCreateCommand command = MemberCreateCommand.of(member.getName());

        // mocking
        given(memberRepository.save(any()))
                .willReturn(member);

        given(memberRepository.findById(member.getId()))
                .willReturn(Optional.of(member));

        // when
        Long id = memberService.create(command);

        // then
        Member findMember = memberRepository.findById(id).orElseThrow();
        assertThat(member.getId()).isEqualTo(findMember.getId());
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    @DisplayName("member 변경 테스트")
    void updateMemberTest() {
        // given
        Member member = createFakeMember();
        MemberUpdateCommand command = MemberUpdateCommand.of(member.getId(), member.getName() + "변경");

        // mocking
        given(memberRepository.findById(member.getId()))
                .willReturn(Optional.of(command.toEntity()));

        // when
        memberService.update(command);

        // then
        Member findMember = memberRepository.findById(member.getId()).orElseThrow();
        assertThat(findMember.getName()).isEqualTo(member.getName() + "변경");
    }

    @Test
    @DisplayName("member 삭제 테스트")
    void deleteMemberTest() {
        // given
        Member member = createFakeMember();

        // mocking
        given(memberRepository.findById(member.getId()))
                .willReturn(Optional.of(member));

        // when
        memberService.delete(member.getId());

        when(memberRepository.findById(member.getId()))
                .thenReturn(Optional.empty());

        // then
        assertThatThrownBy(() -> memberRepository.findById(member.getId()).orElseThrow())
                .isInstanceOf(NoSuchElementException.class);
    }

    private Member createFakeMember() {
        Member fakeMember = Member.builder()
                .name("TEST")
                .build();

        Long fakeId = 1L;
        ReflectionTestUtils.setField(fakeMember, "id", fakeId);

        return fakeMember;
    }

}
