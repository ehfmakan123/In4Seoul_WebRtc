package com.ssafy.api.service;


import com.ssafy.api.dto.ConsultantDto;
import com.ssafy.db.entity.Staff;

import java.util.List;

public interface AdminService {

    Staff getStaffById(String userId);  //admin 로그인에 사용하는 함수

    List<ConsultantDto> getConsultantList(); // 상담사 목록 가져올때 사용하는 함수
    ConsultantDto getConsultant(int id);
    int updateConsultant(ConsultantDto dto);


}
