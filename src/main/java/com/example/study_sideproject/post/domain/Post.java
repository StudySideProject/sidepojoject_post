package com.example.study_sideproject.post.domain;

import com.example.study_sideproject.global.BaseTimeEntity;
import com.example.study_sideproject.member.domain.Member;
import com.example.study_sideproject.post.dto.request.PostReqDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Builder
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20) // SQL Column 길이를 설정하는 데 사용, 단 유효성 검사는 해주지 않음
    @Size(min = 3, max = 20) // 유효성 검사를 위해 사용
    private String title;

    @Column(nullable = false, length = 500)
    @Size(min = 10, max = 500)
    private String content;

    @JoinColumn(name = "member_id", nullable = false)
    @ManyToOne(fetch = LAZY)
    private Member member;

    public void updatePost (PostReqDto postReqDto){
        this.title = postReqDto.getTitle();
        this.content = postReqDto.getContent();
    }
}
