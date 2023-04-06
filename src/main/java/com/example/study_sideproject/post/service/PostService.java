package com.example.study_sideproject.post.service;

import com.example.study_sideproject.global.exception.CustomException;
import com.example.study_sideproject.global.exception.ErrorCode;
import com.example.study_sideproject.global.jwt.SecurityUtil;
import com.example.study_sideproject.member.domain.Member;
import com.example.study_sideproject.member.repository.MemberRepository;
import com.example.study_sideproject.post.domain.Post;
import com.example.study_sideproject.post.dto.request.PostReqDto;
import com.example.study_sideproject.post.dto.response.PostResponseDto;
import com.example.study_sideproject.post.exception.customException.MemberInfoNotExistException;
import com.example.study_sideproject.post.exception.customException.PostInfoNotExistException;
import com.example.study_sideproject.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor // final이나 @NonNull인 필드 값만 파라미터로 받는 생성자 생성
public class PostService {

	private final PostRepository postRepository;
	private final MemberRepository memberRepository;

	// 게시글 생성
	@Transactional
	public void createPost(PostReqDto postReqDto) {

		Member member = getMemberIfExists();

		Post post = Post.builder()
				.title(postReqDto.getTitle())
				.content(postReqDto.getContent())
				.member(member)
				.build();

		postRepository.save(post);
	}


	// 게시글 상세 조회
	@Transactional(readOnly = true)
	public PostResponseDto getOnePost(Long id){

		Post post = getPostIfExists(id);

		return PostResponseDto.builder()
				.id(post.getId())
				.title(post.getTitle())
				.content(post.getContent())
				.createdAt(post.getCreatedAt())
				.modifiedAt(post.getModifiedAt())
				.build();
	}

	
	// 게시글 수정
	@Transactional
	public void updatePost(Long id, PostReqDto postReqDto) {
		Post post = validateAuthor(id);
		post.updatePost(postReqDto);
	}


	// 게시글 삭제
	@Transactional
	public void deletePost(Long id) {
		Post post = validateAuthor(id);
		postRepository.deleteById(post.getId());
	}

	// 유효한 멤버인지 검증
	private Member getMemberIfExists() {
		// 현재 로그인한 사람이 있을 경우 해당 회원의 email을 가져온다.
		String email = SecurityUtil.getCurrentUserEmail().orElseThrow(MemberInfoNotExistException::new);
		// mysql에서 해당 email을 가진 회원이 없을 경우 예외발생, 있으면 회원 정보를 가져온다.
		return memberRepository.findByEmail(email).orElseThrow(MemberInfoNotExistException::new);
	}

	// 게시글이 존재하는지 확인
	private Post getPostIfExists(Long id) {
		// mysql에서 해당 id에 해당하는 post가 없을 경우 예외발생, 있으면 post 정보를 가져온다.
		return postRepository.findById(id).orElseThrow(PostInfoNotExistException::new);
	}

	// 게시글 작성자인지 확인
	private Post validateAuthor(Long id) {
		getPostIfExists(id);
		Long memberId = getMemberIfExists().getId();
		return postRepository.findByIdAndMemberId(id, memberId).orElseThrow(
				() -> new CustomException(ErrorCode.NOT_AUTHOR));
	}
}
