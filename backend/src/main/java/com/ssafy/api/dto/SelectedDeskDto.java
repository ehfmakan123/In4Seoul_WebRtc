package com.ssafy.api.dto;


import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
//선택 지역에 해당 하는 데스크 목록을 위한 dto
@Data
public class SelectedDeskDto {


    int id;
    String korName;
    String engName;


    @QueryProjection
    public SelectedDeskDto(int id, String korName, String engName) {
        this.id = id;
        this.korName = korName;
        this.engName = engName;
    }
}
