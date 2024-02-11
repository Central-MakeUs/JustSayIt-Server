package com.justsayit.member.repository;

import com.justsayit.member.domain.Member;
import com.justsayit.member.domain.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("select m from Member m where m.email = :email and m.provider = :provider")
    Member findByEmailAndProvider(@Param("email") String email, @Param("provider") Provider provider);
}
