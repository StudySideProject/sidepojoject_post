package com.example.study_sideproject.global;

import com.example.study_sideproject.comment.domain.Comment;
import com.example.study_sideproject.comment.repository.CommentRepository;
import com.example.study_sideproject.global.exception.CustomException;
import com.example.study_sideproject.global.exception.ErrorCode;
import com.example.study_sideproject.global.exception.customException.CommentNotExistException;
import com.example.study_sideproject.global.exception.customException.MemberInfoNotExistException;
import com.example.study_sideproject.global.exception.customException.PostInfoNotExistException;
import com.example.study_sideproject.global.jwt.SecurityUtil;
import com.example.study_sideproject.member.domain.Member;
import com.example.study_sideproject.member.repository.MemberRepository;
import com.example.study_sideproject.post.domain.Post;
import com.example.study_sideproject.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ValidateCheck {
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    // 유효한 멤버인지 검증
    public Member getMemberIfExists() {
        // 현재 로그인한 사람이 있을 경우 해당 회원의 email을 가져온다.
        String email = SecurityUtil.getCurrentUserEmail().orElseThrow(MemberInfoNotExistException::new);
        // mysql에서 해당 email을 가진 회원이 없을 경우 예외발생, 있으면 회원 정보를 가져온다.
        return memberRepository.findByEmail(email).orElseThrow(MemberInfoNotExistException::new);
    }

    // 게시글이 존재하는지 확인
    public Post getPostIfExists(Long id) {
        // mysql에서 해당 id에 해당하는 post가 없을 경우 예외발생, 있으면 post 정보를 가져온다.
        return postRepository.findById(id).orElseThrow(PostInfoNotExistException::new);
    }

    // 게시글 작성자인지 확인
    public Post validateAuthor(Long id) {
        getPostIfExists(id);
        Long memberId = getMemberIfExists().getId();
        return postRepository.findByIdAndMemberId(id, memberId).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_AUTHOR));
    }

    //댓글 작성자인지 확인
    public void validateCommenter(Long id) {
        getCommentIfExists(id);
        Long memberId = getMemberIfExists().getId();
        commentRepository.findByIdAndMemberId(id, memberId).orElseThrow(
                () -> new CustomException(ErrorCode.NOT_COMMENTER)
        );
    }

    //댓글이 존재하는지 확인
    public Comment getCommentIfExists(Long id) {
        return commentRepository.findById(id).orElseThrow(CommentNotExistException::new);
    }

    // 부모 댓글과 자식 댓글의 게시글 번호가 일치하는지 확인
    public void validatePostIdMatch(Long postId, Long parentId) {
        Long parentPostId = getCommentIfExists(parentId).getPost().getId();
        if (!parentPostId.equals(postId))
            throw new CustomException(ErrorCode.POST_ID_NOT_MATCH_WITH_PARENTCOMMENT);
    }
}
