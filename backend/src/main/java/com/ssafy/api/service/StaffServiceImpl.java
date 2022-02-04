package com.ssafy.api.service;


import com.ssafy.api.dto.StaffDto;
import com.ssafy.api.request.StaffRequest;
import com.ssafy.db.entity.Staff;
import com.ssafy.db.repository.StaffRepository;
import com.ssafy.db.repository.StaffRepositorySupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StaffServiceImpl implements StaffService{
    @Autowired
    StaffRepository staffRepository;

    @Autowired
    StaffRepositorySupport staffRepositorySupport;



    @Override
    public boolean checkId(String id) {

        Optional<Staff> result = staffRepository.findByStaffId(id);




        if(result.isPresent()) return false;

        return true;


    }


    // 새로운 유저 등록
    @Override
    public boolean register(StaffRequest request) {


        Staff staff=new Staff();
        staff.setStaffId(request.getUserId());
        staff.setPassword(request.getPassword());
        staff.setName(request.getName());
        staff.setPhone(request.getPhone());
        staff.setEmail(request.getEmail());
        staff.setDeleteYN("N");
        staff.setAdminYN("N");
        staff.setApproveYN("N");

        Staff save = staffRepository.save(staff);


        return true;
    }

    @Override
    public Staff getStaffByStaffId(String id) {

        Optional<Staff> result = staffRepository.findByStaffId(id);

        if(result.isPresent())
        {
            return result.get();
        }
        else return null;
    }

    @Override
    public StaffDto getStaffDtoByStaffId(String id) {


        return staffRepositorySupport.getStaffDto(id);
    }


    //내 정보 수정
    @Override
    public boolean updateStaff(StaffDto dto) {
        return false;
    }
}
