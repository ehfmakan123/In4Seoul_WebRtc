package com.ssafy.api.dto;


import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class AreaDto {

    int id;
    String korName;
    String engName;

    @QueryProjection

    public AreaDto(int id, String korName, String engName) {
        this.id = id;
        this.korName = korName;
        this.engName = engName;
    }
}
