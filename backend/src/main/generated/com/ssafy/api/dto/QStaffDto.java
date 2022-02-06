package com.ssafy.api.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.Generated;

/**
 * com.ssafy.api.dto.QStaffDto is a Querydsl Projection type for StaffDto
 */
@Generated("com.querydsl.codegen.ProjectionSerializer")
public class QStaffDto extends ConstructorExpression<StaffDto> {

    private static final long serialVersionUID = -2137047895L;

    public QStaffDto(com.querydsl.core.types.Expression<Integer> id, com.querydsl.core.types.Expression<String> userId, com.querydsl.core.types.Expression<String> name, com.querydsl.core.types.Expression<String> phone, com.querydsl.core.types.Expression<String> email, com.querydsl.core.types.Expression<String> deleteYN, com.querydsl.core.types.Expression<String> approveYN, com.querydsl.core.types.Expression<Integer> areaId, com.querydsl.core.types.Expression<String> areaName, com.querydsl.core.types.Expression<String> createdAt, com.querydsl.core.types.Expression<String> updatedAt) {
        super(StaffDto.class, new Class<?>[]{int.class, String.class, String.class, String.class, String.class, String.class, String.class, int.class, String.class, String.class, String.class}, id, userId, name, phone, email, deleteYN, approveYN, areaId, areaName, createdAt, updatedAt);
    }

}

