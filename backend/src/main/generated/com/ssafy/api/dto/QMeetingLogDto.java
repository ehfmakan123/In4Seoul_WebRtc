package com.ssafy.api.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.Generated;

/**
 * com.ssafy.api.dto.QMeetingLogDto is a Querydsl Projection type for MeetingLogDto
 */
@Generated("com.querydsl.codegen.ProjectionSerializer")
public class QMeetingLogDto extends ConstructorExpression<MeetingLogDto> {

    private static final long serialVersionUID = 363168396L;

    public QMeetingLogDto(com.querydsl.core.types.Expression<Integer> id, com.querydsl.core.types.Expression<String> deskName, com.querydsl.core.types.Expression<String> startedAt, com.querydsl.core.types.Expression<String> endedAt, com.querydsl.core.types.Expression<String> content) {
        super(MeetingLogDto.class, new Class<?>[]{int.class, String.class, String.class, String.class, String.class}, id, deskName, startedAt, endedAt, content);
    }

}

