package com.ssafy.api.dto;


import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class DeskDto {

    int id;
    String deskId;
    String korName;
    String engName;
    String password;
    String latitude;
    String altitude;
    int areaId;
    String areaName;
    String createdAt;
    String updatedAt;
    String deleteYN;

    public DeskDto() {
    }

    @QueryProjection
    public DeskDto(int id, String deskId, String korName, String engName, String latitude, String altitude, int areaId, String areaName, String createdAt, String updatedAt, String deleteYN) {
        this.id = id;
        this.deskId = deskId;
        this.korName = korName;
        this.engName = engName;
        this.latitude = latitude;
        this.altitude = altitude;
        this.areaId = areaId;
        this.areaName = areaName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.deleteYN = deleteYN;
    }
}
