package com.ssafy.api.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.Generated;

/**
 * com.ssafy.api.dto.QConsultantDto is a Querydsl Projection type for ConsultantDto
 */
@Generated("com.querydsl.codegen.ProjectionSerializer")
public class QConsultantDto extends ConstructorExpression<ConsultantDto> {

    private static final long serialVersionUID = 1344930266L;

    public QConsultantDto(com.querydsl.core.types.Expression<Integer> id, com.querydsl.core.types.Expression<String> userId, com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<String> phone, com.querydsl.core.types.Expression<String> email, com.querydsl.core.types.Expression<String> deleteYN, com.querydsl.core.types.Expression<String> approveYN, com.querydsl.core.types.Expression<Integer> areaId, com.querydsl.core.types.Expression<String> areaName) {
        super(ConsultantDto.class, new Class<?>[]{int.class, String.class, String.class, String.class, String.class, String.class, String.class, int.class, String.class}, id, userId, name, phone, email, deleteYN, approveYN, areaId, areaName);
    }

}

