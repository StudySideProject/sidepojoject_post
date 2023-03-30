package com.example.study_sideproject.member.repository;


import com.example.study_sideproject.member.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
