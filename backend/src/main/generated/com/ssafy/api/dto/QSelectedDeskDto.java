package com.ssafy.api.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.Generated;

/**
 * com.ssafy.api.dto.QSelectedDeskDto is a Querydsl Projection type for SelectedDeskDto
 */
@Generated("com.querydsl.codegen.ProjectionSerializer")
public class QSelectedDeskDto extends ConstructorExpression<SelectedDeskDto> {

    private static final long serialVersionUID = 1561057377L;

    public QSelectedDeskDto(com.querydsl.core.types.Expression<Integer> id, com.querydsl.core.types.Expression<String> korName, com.querydsl.core.types.Expression<String> engName) {
        super(SelectedDeskDto.class, new Class<?>[]{int.class, String.class, String.class}, id, korName, engName);
    }

}

