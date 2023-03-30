package com.example.study_sideproject.member.repository;

import com.example.study_sideproject.member.domain.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

	boolean existsByEmail(String email);

	@EntityGraph(attributePaths = "authorities")
	Optional<Member> findOneWithAuthoritiesByEmail(String email);

}
