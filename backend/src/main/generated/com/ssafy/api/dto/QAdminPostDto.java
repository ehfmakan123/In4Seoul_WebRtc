package com.ssafy.api.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.Generated;

/**
 * com.ssafy.api.dto.QAdminPostDto is a Querydsl Projection type for AdminPostDto
 */
@Generated("com.querydsl.codegen.ProjectionSerializer")
public class QAdminPostDto extends ConstructorExpression<AdminPostDto> {

    private static final long serialVersionUID = 2032052122L;

    public QAdminPostDto(com.querydsl.core.types.Expression<Long> id, com.querydsl.core.types.Expression<String> title, com.querydsl.core.types.Expression<String> content, com.querydsl.core.types.Expression<String> createdAt, com.querydsl.core.types.Expression<String> updatedAt, com.querydsl.core.types.Expression<String> areaName, com.querydsl.core.types.Expression<String> deskName) {
        super(AdminPostDto.class, new Class<?>[]{long.class, String.class, String.class, String.class, String.class, String.class, String.class}, id, title, content, createdAt, updatedAt, areaName, deskName);
    }

}

