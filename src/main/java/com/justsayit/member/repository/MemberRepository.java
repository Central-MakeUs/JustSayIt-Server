package com.justsayit.member.repository;

import com.justsayit.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("select m from Member m where m.token = :token")
    Optional<Member> findByToken(@Param("token") String token);
}
