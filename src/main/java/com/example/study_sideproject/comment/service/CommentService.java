package com.example.study_sideproject.comment.service;

import com.example.study_sideproject.comment.domain.Comment;
import com.example.study_sideproject.comment.dto.CommentReqDto;
import com.example.study_sideproject.comment.dto.ReCommentResDto;
import com.example.study_sideproject.comment.repository.CommentRepository;
import com.example.study_sideproject.global.ValidateCheck;
import com.example.study_sideproject.member.domain.Member;
import com.example.study_sideproject.post.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        Comment parentComment = null;
        Long parentId = commentReqDto.getParentId();
        if (parentId != null) {
            parentComment = validateCheck.getCommentIfExists(parentId);
            validateCheck.validatePostIdMatch(postId, parentId);
        }
        Comment comment = Comment.builder()
                .content(commentReqDto.getContent())
                .member(member)
                .post(post)
                .parent(parentComment)
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
        validateCheck.validateCommenter(commentId);
        Comment comment = validateCheck.getCommentIfExists(commentId);
        commentRepository.deleteById(comment.getId());
    }

    // 대댓글 조회
    @Transactional(readOnly = true)
    public List<ReCommentResDto> getReComments(Long parentId) {
        validateCheck.getCommentIfExists(parentId);

        return commentRepository.findByParentId(parentId).
                stream().map(comment -> ReCommentResDto.builder()
                        .id(comment.getId())
                        .parentId(comment.getParent().getId())
                        .commenter(comment.getMember().getEmail())
                        .content(comment.getContent())
                        .modifiedAt(comment.getModifiedAt())
                        .build())
                .toList();
    }
}
