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

    //게시물에 있는 댓글 테이블까지 CASCADE옵션으로 삭제
    //1. 해당 게시물의 모든 댓글 삭제
    //2. 해당 게시물 삭제
    //3. 이 작업은 하나의 트랜잭션

    //게시물 번호로 댓글 삭제
    //->Update, delete 를 위한 어노테이션
    @Modifying
    @Query("delete from Comment c where c.post.id = : id ")
    void deleteByCommentId(@Param("commmentId") Long commentId);

    //특정 게시물 번호로 댓글 가져오기
    List<Comment> getCommentsByPostOrderById(Post post);

}
