package com.example.study_sideproject.post.dto.response;

import com.example.study_sideproject.post.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AllPostResDto {

    private String title;
    private String author;
    private LocalDateTime modifiedAt;

    public AllPostResDto(Post post) {
        this.title = post.getTitle();
        this.author = post.getMember().getEmail();
        this.modifiedAt = post.getModifiedAt();
    }
}
