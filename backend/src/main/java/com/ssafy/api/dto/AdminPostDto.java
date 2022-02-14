package com.ssafy.api.dto;


import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

// 어드민용 글 정보
@Data
public class AdminPostDto {



    Long id;
    String title;
    String content;
    String createdAt;
    String updatedAt;
    String areaName;
    String deskName;

    @QueryProjection
    public AdminPostDto(Long id, String title, String content, String createdAt, String updatedAt, String areaName, String deskName) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.areaName = areaName;
        this.deskName = deskName;
    }
}
