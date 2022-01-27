package com.ssafy.api.dto;


import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
public class ConsultantDto {

    int id;
    String userId;
    String name;
    String phone;
    String email;
    String deleteYN;
    String approveYN;
    int areaId;
    String areaName;


    public ConsultantDto() {
    }

    @QueryProjection
    public ConsultantDto(int id, String userId, String name, String phone, String email, String deleteYN, String approveYN, int areaId, String areaName) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.deleteYN = deleteYN;
        this.approveYN = approveYN;
        this.areaId = areaId;
        this.areaName = areaName;
    }
}
