package com.example.study_sideproject.post.service;

import com.example.study_sideproject.global.jwt.SecurityUtil;
import com.example.study_sideproject.member.domain.Member;
import com.example.study_sideproject.member.repository.MemberRepository;
import com.example.study_sideproject.post.domain.Post;
import com.example.study_sideproject.post.dto.request.PostReqDto;
import com.example.study_sideproject.post.exception.customException.MemberInfoNotExistException;
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


	private Member getMemberIfExists() {

		// 이미 생성되어 있는 SecurityUtil에 getCurrentUsername()를 가입된 이메일인지 검증하기 위해 사용
		Optional<String> emailOptional = SecurityUtil.getCurrentUsername();

		if (emailOptional.isPresent()) { // 객체 존재여부를 확인

			String email = emailOptional.get();
			Optional<Member> memberOptional = memberRepository.findByEmail(email);

			// 가입한 멤버가 아닐 경우2 : 비어 있을 때
			if (memberOptional.isEmpty()) {
				throw new MemberInfoNotExistException();
			} else return memberOptional.get();

		// 가입한 멤버가 아닐 경우1 : 존재하지 않을 때
		} else throw new MemberInfoNotExistException();
	}
}
