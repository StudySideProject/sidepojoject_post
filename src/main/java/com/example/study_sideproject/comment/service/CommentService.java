package com.example.study_sideproject.comment.service;

import com.example.study_sideproject.comment.domain.Comment;
import com.example.study_sideproject.comment.dto.CommentReqDto;
import com.example.study_sideproject.comment.dto.CommentResDto;
import com.example.study_sideproject.comment.repository.CommentRepository;
import com.example.study_sideproject.global.ValidateCheck;
import com.example.study_sideproject.member.domain.Member;
import com.example.study_sideproject.post.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final ValidateCheck validateCheck;
    private final CommentRepository commentRepository;

    // 댓글 생성
    @Transactional
    public void createComment(Long postId, CommentReqDto commentReqDto) {
        Member member = validateCheck.getMemberIfExists();
        Post post = validateCheck.getPostIfExists(postId);

        Comment comment = Comment.builder()
                .content(commentReqDto.getContent())
                .member(member)
                .post(post)
                .build();
        commentRepository.save(comment);
    }

    //댓글 수정
    @Transactional
    public void updateComment(Long commentId, CommentReqDto commentReqDto) {
        validateCheck.validateCommenter(commentId);
        Comment comment = validateCheck.getCommentIfExists(commentId);
        comment.updateComment(commentReqDto);
    }

    // 댓글 삭제
    @Transactional
    public void deleteComment(Long commentId) {
//        Comment comment = validateCheck.validatecommenter(commentId);
//        commentRepository.deleteById(comment.getId());
    }


}
