package com.example.study_sideproject.post.service;

import com.example.study_sideproject.comment.domain.Comment;
import com.example.study_sideproject.comment.dto.CommentResDto;
import com.example.study_sideproject.comment.repository.CommentRepository;
import com.example.study_sideproject.global.ValidateCheck;
import com.example.study_sideproject.member.domain.Member;
import com.example.study_sideproject.member.repository.MemberRepository;
import com.example.study_sideproject.post.domain.Post;
import com.example.study_sideproject.post.dto.request.PostReqDto;
import com.example.study_sideproject.post.dto.response.AllPostResDto;
import com.example.study_sideproject.post.dto.response.PostResponseDto;
import com.example.study_sideproject.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor // final이나 @NonNull인 필드 값만 파라미터로 받는 생성자 생성
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final ValidateCheck validateCheck;
    private final CommentRepository commentRepository;

    // 게시글 생성
    @Transactional
    public void createPost(PostReqDto postReqDto) {

        Member member = validateCheck.getMemberIfExists();

        Post post = Post.builder()
                .title(postReqDto.getTitle())
                .content(postReqDto.getContent())
                .member(member)
                .build();

        postRepository.save(post);
    }

    //게시글 전체 조회
    @Transactional
    public List<AllPostResDto> getAllPost() {
        List<Post> postList = postRepository.findAll();
        return postList.stream()
                .map(AllPostResDto::new).toList();
    }

    // 게시글 상세 조회
    @Transactional(readOnly = true)
    public PostResponseDto getOnePost(Long id) {
        Post post = validateCheck.getPostIfExists(id);

        // 게시글 상세 조회 시 댓글도 함께 조회
        List<Comment> comments = commentRepository.findByPostId(id);
        List<CommentResDto> commentList = new ArrayList<>();
        for (Comment comment : comments) {
            commentList.add(CommentResDto.builder()
                    .id(comment.getId())
                    .commenter(comment.getMember().getEmail())
                    .content(comment.getContent())
                    .modifiedAt(comment.getModifiedAt())
                    .build()
            );
        }

        return PostResponseDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .email(post.getMember().getEmail())
                .modifiedAt(post.getModifiedAt())
                .comments(commentList)
                .build();
    }


    // 게시글 수정
    @Transactional
    public void updatePost(Long id, PostReqDto postReqDto) {
        Post post = validateCheck.validateAuthor(id);
        post.updatePost(postReqDto);
    }


    // 게시글 삭제
    @Transactional
    public void deletePost(Long id) {
        Post post = validateCheck.validateAuthor(id);
        postRepository.deleteById(post.getId());
    }
}
