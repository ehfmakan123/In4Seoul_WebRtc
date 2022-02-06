package com.ssafy.api.dto;


import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class AreaDto {

    int id;
    String areaName;

    @QueryProjection
    public AreaDto(int id, String areaName) {
        this.id = id;
        this.areaName = areaName;
    }
}
