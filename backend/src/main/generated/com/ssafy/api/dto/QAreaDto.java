package com.ssafy.api.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.Generated;

/**
 * com.ssafy.api.dto.QAreaDto is a Querydsl Projection type for AreaDto
 */
@Generated("com.querydsl.codegen.ProjectionSerializer")
public class QAreaDto extends ConstructorExpression<AreaDto> {

    private static final long serialVersionUID = -580446456L;

    public QAreaDto(com.querydsl.core.types.Expression<Integer> id, com.querydsl.core.types.Expression<String> korName, com.querydsl.core.types.Expression<String> engName) {
        super(AreaDto.class, new Class<?>[]{int.class, String.class, String.class}, id, korName, engName);
    }

}

