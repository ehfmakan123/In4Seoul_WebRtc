package com.ssafy.api.dto;


import com.querydsl.core.annotations.QueryProjection;
import com.ssafy.common.model.response.BaseResponseBody;
import lombok.Data;


@Data
public class StaffDto  {

    int id;
    String userId;
    String name;
    String phone;
    String email;
    String deleteYN;
    String approveYN;
    Integer areaId;
    String areaName;
    String createdAt;
    String updatedAt;


    public StaffDto() {
    }

    @QueryProjection
    public StaffDto(int id, String userId, String name, String phone, String email, String deleteYN, String approveYN, Integer areaId, String areaName, String createdAt, String updatedAt) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.deleteYN = deleteYN;
        this.approveYN = approveYN;
        this.areaId = areaId;
        this.areaName = areaName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
