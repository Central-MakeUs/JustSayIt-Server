package com.justsayit.story.repository;

import com.justsayit.story.domain.Empathy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpathyRepository extends JpaRepository<Empathy, Long>, EmpathyRepositoryCustom {

}
