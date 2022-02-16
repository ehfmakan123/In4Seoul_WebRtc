package com.ssafy.api.service;

import com.ssafy.api.dto.MeetingLogDto;
import com.ssafy.api.dto.StaffDto;
import com.ssafy.api.request.StaffRequest;
import com.ssafy.common.model.response.ListResult;
import com.ssafy.db.entity.Staff;

import java.util.List;

public interface StaffService {

    boolean checkId(String id); //아이디 중복 체크

    boolean register(StaffRequest staffRequest); //회원가입
    Staff getStaffByStaffId(String id); // 아이디 정보 가져오기 (로그인 시  현재 입력한 비밀번호와
    StaffDto getStaffDtoByStaffId(String id);  //내 정보 조회
    boolean updateStaff(StaffDto dto); // 내 정보 수정


    ListResult<MeetingLogDto> getMeetingLogList(int id, Integer page);  //내 상담목록 가져오기



    int meetingLogStart(int staffPk, String sessionId); //상담 시작 시  meeting log에 등록


    void meetingLogEnd(int meetingLogId, String content,int staffId);


    void logout(String staffId);  //로그아웃하면서 fcm 값 갱신



}
