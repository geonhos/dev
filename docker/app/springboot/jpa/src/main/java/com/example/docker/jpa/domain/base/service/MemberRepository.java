package com.example.docker.jpa.domain.base.service;

import com.example.docker.jpa.domain.base.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
