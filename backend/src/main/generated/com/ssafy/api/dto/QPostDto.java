package com.ssafy.api.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.Generated;

/**
 * com.ssafy.api.dto.QPostDto is a Querydsl Projection type for PostDto
 */
@Generated("com.querydsl.codegen.ProjectionSerializer")
public class QPostDto extends ConstructorExpression<PostDto> {

    private static final long serialVersionUID = -225185259L;

    public QPostDto(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> title, com.querydsl.core.types.Expression<String> content, com.querydsl.core.types.Expression<String> createdAt, com.querydsl.core.types.Expression<String> updatedAt) {
        super(PostDto.class, new Class<?>[]{long.class, String.class, String.class, String.class, String.class}, id, title, content, createdAt, updatedAt);
    }

}

