package com.ssafy.api.service;

import com.ssafy.api.dto.AreaDto;
import com.ssafy.api.dto.StaffDto;
import com.ssafy.api.dto.DeskDto;
import com.ssafy.api.dto.PostDto;
import com.ssafy.db.entity.Areas;
import com.ssafy.db.entity.Desks;
import com.ssafy.db.entity.Staff;
import com.ssafy.db.repository.AdminRepository;
import com.ssafy.db.repository.AdminRepositorySupport;
import com.ssafy.db.repository.PostRepository;
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


    @Autowired
    PostRepository postRepository;

    @Override
    public Staff getStaffById(String userId) {
        Optional<Staff> admin = adminRepositorySupport.findStaffById(userId);
        if (admin.isPresent()) {
            return admin.get();
        } else
            return null;
    }

    @Override
    public List<StaffDto> getConsultantList() {
        return adminRepositorySupport.getConsultantList();
    }

    @Override
    public StaffDto getConsultant(int id) {

        return adminRepositorySupport.getConsultant(id);


    }

    @Override
    public boolean updateConsultant(int id, StaffDto dto) {


        Staff staff = staffRepository.findById(id).get();

        staff.setDeleteYN(dto.getDeleteYN());
        staff.setApproveYN(dto.getApproveYN());
        staff.setAreas(new Areas(dto.getAreaId(), null, null));

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
        Desks desks = new Desks();
        desks.setDeskId(dto.getDeskId());
        desks.setKorName(dto.getKorName());
        desks.setEngName(dto.getEngName());
        desks.setPassword(dto.getPassword());
        desks.setLatitude(dto.getLatitude());
        desks.setAltitude(dto.getAltitude());
        desks.setDeleteYN("N");  // 생성이니까 N설정
        desks.setArea(new Areas(dto.getAreaId(), null, null));

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

        Optional<Desks> desks = adminRepository.findById(id);
        Desks desk = desks.get();

        desk.setDeskId(dto.getDeskId());
        desk.setKorName(dto.getKorName());
        desk.setEngName(dto.getEngName());
        desk.setPassword(dto.getPassword());
        desk.setLatitude(dto.getLatitude());
        desk.setAltitude(dto.getAltitude());
        desk.setDeleteYN(dto.getDeleteYN());

        // 해당 데스크의 지역 수정
        desk.setArea(new Areas(dto.getAreaId(), null, null));

        adminRepository.save(desk);
        return 1;
    }


    // db에 해당 아이디를 가진 데스크가 있는지 확인
    @Override
    public Desks findByDeskId(String id) {


        Optional<Desks> result = adminRepository.findByDeskId(id);

        if(result.isPresent())  //db에 존재하는 경우
        {
            return result.get();

        }

        return null;   // 존재하지 않는 경우 null 반환


    }

    @Override
    public List<PostDto> getPostList() {


        List<PostDto> result = adminRepositorySupport.getPostList();

        return result;
    }

    @Override
    public PostDto getPost(long id) {

        PostDto result = adminRepositorySupport.getPost(id);

        return result;

    }

    @Override
    public boolean postDelete(long id) {



        /*
          삭제 후에 이미 삭제된 키값으로 또 삭제를 한 경우

          삭제 전에 먼저 해당 키값(id)을 가진 객체가 존재하는 지 확인하고 없다면

        IllegalArgumentException을 던진다


         */


        try {
            postRepository.deleteById(id);

        } catch (Exception e) {
            return false;
        }


        return true;
    }


    //지역목록 조회
    @Override
    public List<AreaDto> getAreas() {


        return adminRepositorySupport.getAreas();
    }


}
