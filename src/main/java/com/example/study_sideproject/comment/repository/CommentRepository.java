package com.example.study_sideproject.comment.repository;

import com.example.study_sideproject.comment.domain.Comment;
import com.example.study_sideproject.post.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findByIdAndMemberId(Long Id, Long memberId);
    List<Comment> findByPostId(Long postId);

}
