package com.ssafy.api.service;


import com.ssafy.api.dto.ConsultantDto;
import com.ssafy.api.dto.DeskDto;
import com.ssafy.db.entity.Staff;

import java.util.List;

public interface AdminService {

    Staff getStaffById(String userId);  //admin 로그인에 사용하는 함수

    List<ConsultantDto> getConsultantList(); // 상담사 목록 조회
    ConsultantDto getConsultant(int id); //  // 상담사 상세정보 조회
    boolean updateConsultant(int id, ConsultantDto dto); // 상담사 정보 수정



    int deskRegister(DeskDto dto);  // 데스크 등록  int가 맞나..



List<DeskDto> getDeskList();

    DeskDto getDesk(int id);  //데스크 정보 조회

    int deskUpdate(DeskDto dto, int id); // 데스크 정보 수정

}
