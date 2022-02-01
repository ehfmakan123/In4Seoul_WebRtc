package com.ssafy.api.service;

import com.ssafy.api.dto.ConsultantDto;
import com.ssafy.api.dto.DeskDto;
import com.ssafy.db.entity.Areas;
import com.ssafy.db.entity.Desks;
import com.ssafy.db.entity.Staff;
import com.ssafy.db.repository.AdminRepository;
import com.ssafy.db.repository.AdminRepositorySupport;
import com.ssafy.db.repository.StaffRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {


    @Autowired
    AdminRepository adminRepository;

    @Autowired
    StaffRepository staffRepository;


    @Autowired
    AdminRepositorySupport adminRepositorySupport;


    @Override
    public Staff getStaffById(String userId) {
        Optional<Staff> admin = adminRepositorySupport.findStaffById(userId);
        if (admin.isPresent()) {
            return admin.get();
        } else
            return null;
    }

    @Override
    public List<ConsultantDto> getConsultantList() {
        return adminRepositorySupport.getConsultantList();
    }

    @Override
    public ConsultantDto getConsultant(int id) {

        return adminRepositorySupport.getConsultant(id);


    }

    @Override
    public boolean updateConsultant(int id, ConsultantDto dto) {


        Staff staff = staffRepository.findById(id).get();

        staff.setDeleteYN(dto.getDeleteYN());
        staff.setApproveYN(dto.getApproveYN());
        staff.setAreas(new Areas(dto.getAreaId(),null,null));

        Staff save = staffRepository.save(staff);

        return true;



    }


    @Override
    public int deskRegister(DeskDto dto) {


        /*새로운 데스크 생성

        이런식으로 새로 생성해서 DB에 넣을때  Areas같이 외래키에 해당하는 객체는 어떤식으로 넣어줘야하는가..?)
        임시로 desks.setArea(new Areas(dto.getAreaId(),null,null));

        이런식으로 설정했고 db에는 외래키 번호가 잘 들어가긴함 맞는방법인지는 모르겠다..

         */
        Desks desks=new Desks();
        desks.setDeskId(dto.getDeskId());
        desks.setKorName(dto.getKorName());
        desks.setEngName(dto.getEngName());
        desks.setPassword(dto.getPassword());
        desks.setLatitude(dto.getLatitude());
        desks.setAltitude(dto.getAltitude());
        desks.setDeleteYN("N");  // 생성이니까 N설정
        desks.setArea(new Areas(dto.getAreaId(),null,null));

        adminRepository.save(desks);
        return 0;
    }


    //데스크 목록 조회
    @Override
    public List<DeskDto> getDeskList() {
        return adminRepositorySupport.getDeskList();
    }


    // 데스크 정보 조회
    @Override
    public DeskDto getDesk(int id) {
        return adminRepositorySupport.getDesk(id);
    }

    @Override
    public int deskUpdate(DeskDto dto, int id) {

        Optional<Desks> desks=adminRepository.findById(id);
        Desks desk= desks.get();

        desk.setDeskId(dto.getDeskId());
        desk.setKorName(dto.getKorName());
        desk.setEngName(dto.getEngName());
        desk.setPassword(dto.getPassword());
        desk.setLatitude(dto.getLatitude());
        desk.setAltitude(dto.getAltitude());
        desk.setDeleteYN(dto.getDeleteYN());

        // 해당 데스크의 지역 수정
        desk.setArea(new Areas(dto.getAreaId(),null,null));

        adminRepository.save(desk);
return 1;
    }


}
