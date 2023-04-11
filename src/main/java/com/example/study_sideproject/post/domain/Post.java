package com.example.study_sideproject.post.domain;

import com.example.study_sideproject.comment.domain.Comment;
import com.example.study_sideproject.global.BaseTimeEntity;
import com.example.study_sideproject.member.domain.Member;
import com.example.study_sideproject.post.dto.request.PostReqDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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
    private String title;

    @Column(nullable = false, length = 500)
    private String content;

    @JoinColumn(name = "member_id", nullable = false)
    @ManyToOne(fetch = LAZY)
    private Member member;

    @OneToMany(fetch = LAZY,
            mappedBy = "post",
            cascade =  {CascadeType.PERSIST, CascadeType.REMOVE}) // 부모 엔티티를 삭제하면 자식 엔티티 삭제
    private List<Comment> comment = new ArrayList<>();

    public void updatePost (PostReqDto postReqDto){
        this.title = postReqDto.getTitle();
        this.content = postReqDto.getContent();
    }
}
