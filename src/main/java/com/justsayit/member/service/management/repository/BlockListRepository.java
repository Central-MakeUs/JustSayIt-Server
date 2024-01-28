package com.justsayit.member.service.management.repository;

import com.justsayit.member.domain.BlockList;
import com.justsayit.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BlockListRepository extends JpaRepository<BlockList, Long> {

    @Query("select bl.blocked.id from BlockList bl where bl.blocker = :blocker")
    List<Long> findAllByBlocker(@Param("blocker") Member blocker);
}
