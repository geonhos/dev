package com.example.docker.jpa.domain.base.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

@Getter
@Entity
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "member_name", nullable = false)
    private String name;

    @Builder
    public static Member of(String name) {
        Member member = new Member();
        member.name = name;
        return member;
    }

    public void update(Member member) {
        this.name = member.name;
    }
}
