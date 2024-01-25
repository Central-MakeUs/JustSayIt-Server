package com.justsayit.story.repository;

import com.justsayit.story.domain.EmpathyCount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpathyCountRepository extends JpaRepository<EmpathyCount, Long>, EmpathyRepositoryCustom {

}
