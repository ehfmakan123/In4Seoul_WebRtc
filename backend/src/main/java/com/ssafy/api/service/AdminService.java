package com.ssafy.api.service;


import com.ssafy.api.dto.*;
import com.ssafy.common.model.response.ListResult;
import com.ssafy.db.entity.Desk;
import com.ssafy.db.entity.Staff;

import java.util.List;

public interface AdminService {

    Staff getStaffById(String userId);  //admin 로그인에 사용하는 함수

    ListResult<StaffDto> getConsultantList(Integer page); // 상담사 목록 조회
    StaffDto getConsultant(int id); //  // 상담사 상세정보 조회
    boolean updateConsultant(int id, StaffDto dto); // 상담사 정보 수정


    int deskRegister(DeskDto dto);  // 데스크 등록  int가 맞나..
    ListResult<DeskDto> getDeskList(Integer page); // 데스크 목록 조회
    DeskDto getDesk(int id);  //데스크 정보 조회
    int deskUpdate(DeskDto dto, int id); // 데스크 정보 수정



    /////////////////////////////////

    Desk findByDeskId(String id);


    /////////////////////////////////


    //게시글 목록 조회
    ListResult<AdminPostDto> getPostList(Integer page);

    //게시글 조회
    AdminPostDto getPost(long id);


    //게시글 삭제
    boolean postDelete(long id);



    //지역 목록 조회
    List<AreaDto> getAreas();

}
