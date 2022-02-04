package com.ssafy.api.service;

import com.ssafy.api.dto.StaffDto;
import com.ssafy.api.request.StaffRequest;
import com.ssafy.db.entity.Staff;

public interface StaffService {

    boolean checkId(String id); //아이디 중복 체크

    boolean register(StaffRequest staffRequest);
    Staff getStaffByStaffId(String id); // 아이디 정보 가져오기
    StaffDto getStaffDtoByStaffId(String id);
    boolean updateStaff(StaffDto dto);
}
