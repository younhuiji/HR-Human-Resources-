package com.sohwakmo.hr.dto.post;

import com.sohwakmo.hr.domain.Post;
import lombok.Data;

@Data
public class PostCreateDto {

    private String title;
    private String content;
    private String writer;
    private Integer viewCnt;

    public Post toEntity(){
        return Post.builder()
                .title(title)
                .content(content)
                .writer(writer)
                .viewCnt(viewCnt)
                .build();
    }
}
