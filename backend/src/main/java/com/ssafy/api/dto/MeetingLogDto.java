package com.ssafy.api.dto;


import com.querydsl.core.annotations.QueryProjection;
import com.ssafy.common.model.response.BaseResponseBody;
import lombok.Data;

@Data
public class MeetingLogDto  {


int id;
String deskName;
String startedAt;
String endedAt;
String content;


    @QueryProjection

    public MeetingLogDto(int id, String deskName, String startedAt, String endedAt, String content) {
        this.id = id;
        this.deskName = deskName;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
        this.content = content;
    }
}
