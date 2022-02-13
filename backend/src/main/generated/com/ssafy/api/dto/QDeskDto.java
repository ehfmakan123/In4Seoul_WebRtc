package com.ssafy.api.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.Generated;

/**
 * com.ssafy.api.dto.QDeskDto is a Querydsl Projection type for DeskDto
 */
@Generated("com.querydsl.codegen.ProjectionSerializer")
public class QDeskDto extends ConstructorExpression<DeskDto> {

    private static final long serialVersionUID = 1723112828L;

    public QDeskDto(com.querydsl.core.types.Expression<Integer> id, com.querydsl.core.types.Expression<String> deskId, com.querydsl.core.types.Expression<String> korName, com.querydsl.core.types.Expression<String> engName, com.querydsl.core.types.Expression<String> latitude, com.querydsl.core.types.Expression<String> altitude, com.querydsl.core.types.Expression<Integer> areaId, com.querydsl.core.types.Expression<String> areaName, com.querydsl.core.types.Expression<String> createdAt, com.querydsl.core.types.Expression<String> updatedAt, com.querydsl.core.types.Expression<String> deleteYN) {
        super(DeskDto.class, new Class<?>[]{int.class, String.class, String.class, String.class, String.class, String.class, int.class, String.class, String.class, String.class, String.class}, id, deskId, korName, engName, latitude, altitude, areaId, areaName, createdAt, updatedAt, deleteYN);
    }

}

