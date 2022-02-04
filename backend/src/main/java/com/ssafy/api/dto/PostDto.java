package com.ssafy.api.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class PostDto {


    Long id;
    String title;
    String content;
    String createdAt;
    String updatedAt;

    public PostDto() {
    }

    @QueryProjection
    public PostDto(Long id, String title, String content, String createdAt, String updatedAt) {

        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
