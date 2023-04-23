package com.example.study_sideproject.postLike.repository;

import com.example.study_sideproject.postLike.domain.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostLikeRepository extends JpaRepository <PostLike,Long> {
    Optional<PostLike> findByMemberIdAndPostId(Long memberId, Long postId);
}
