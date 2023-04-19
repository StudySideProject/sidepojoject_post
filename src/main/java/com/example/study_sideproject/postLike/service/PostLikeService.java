package com.example.study_sideproject.postLike.service;

import com.example.study_sideproject.global.ValidateCheck;
import com.example.study_sideproject.member.domain.Member;
import com.example.study_sideproject.post.domain.Post;
import com.example.study_sideproject.postLike.domain.PostLike;
import com.example.study_sideproject.postLike.repository.PostLikeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostLikeService {
    private final ValidateCheck validateCheck;
    private final PostLikeRepository postLikeRepository;

    // 게시글 좋아요
    @Transactional
    public void createPostLike(Long postId) {
        // 멤버 존재하는지 확인
        Member member = validateCheck.getMemberIfExists();
        Post post = validateCheck.getPostIfExists(postId);
        PostLike postLike = validateCheck.getPostLikeIfExists(member.getId(), postId);

        // 이미 좋아요 누른 글이면 좋아요 취소
        if (postLike != null) {
            postLikeRepository.delete(postLike);

        // 좋아요 안누른 글이면 좋아요 생성
        } else {
            PostLike createPostLike = new PostLike(member,post);
            postLikeRepository.save(createPostLike);
        }
    }
}
