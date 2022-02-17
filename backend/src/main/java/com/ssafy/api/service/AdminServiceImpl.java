package com.ssafy.api.service;

import com.ssafy.api.dto.*;
import com.ssafy.common.model.response.ListResult;
import com.ssafy.db.entity.Area;
import com.ssafy.db.entity.Desk;
import com.ssafy.db.entity.Staff;
import com.ssafy.db.repository.DeskRepository;
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
    DeskRepository deskRepository;

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
    public ListResult<StaffDto> getConsultantList(Integer page) {

        if (page == null) page = 1;

        ListResult<StaffDto> consultantList = adminRepositorySupport.getConsultantList(page);
        //페이지네이션 처리

        Long totalCount = consultantList.getTotalCount();

        int totalPage = totalCount.intValue() / 10; //총 페이지

        if (totalCount % 10 > 0) {
            totalPage++;
        }


        int startPage = ((page - 1) / 5) * 5 + 1; //시작 페이지 번호
        int endPage = startPage + 4;


        if (endPage > totalPage) endPage = totalPage;

        boolean pre = false;

        boolean start = false;


        if (page - 5 >= 1) {
            pre = true;
        }


        if (startPage > 1) {
            start = true;
        }


        boolean next = false;

        boolean end = false;

        if (page + 5 <= totalPage || endPage < totalPage) {
            next = true;

        }

        if (endPage < totalPage) {
            end = true;
        }

        consultantList.setStartPage(startPage);
        consultantList.setEndPage(endPage);
        consultantList.setNowPage(page);
        consultantList.setNext(next);
        consultantList.setPre(pre);
        consultantList.setStart(start);
        consultantList.setEnd(end);
        consultantList.setTotalPage(totalPage);
        return consultantList;
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

        if(dto.getAreaId()!=null)
        staff.setArea(new Area(dto.getAreaId(), null, null));

        //추가된 부분
        staff.setName(dto.getName());
        staff.setPhone(dto.getPhone());
        staff.setEmail(dto.getEmail());


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
        Desk desk = new Desk();
        desk.setDeskId(dto.getDeskId());
        desk.setKorName(dto.getKorName());
        desk.setEngName(dto.getEngName());
        desk.setPassword(dto.getPassword());
        desk.setLatitude(dto.getLatitude());
        desk.setAltitude(dto.getAltitude());
        desk.setDeleteYN("N");  // 생성이니까 N설정
        desk.setArea(new Area(dto.getAreaId(), null, null));

        deskRepository.save(desk);
        return 0;
    }


    //데스크 목록 조회
    @Override
    public ListResult<DeskDto> getDeskList(Integer page) {


        if (page == null) page = 1;

        ListResult<DeskDto> deskList = adminRepositorySupport.getDeskList(page);


        //페이지네이션 처리

        Long totalCount = deskList.getTotalCount();

        int totalPage = totalCount.intValue() / 10; //총 페이지

        if (totalCount % 10 > 0) {
            totalPage++;
        }


        int startPage = ((page - 1) / 5) * 5 + 1; //시작 페이지 번호
        int endPage = startPage + 4;


        if (endPage > totalPage) endPage = totalPage;

        boolean pre = false;

        boolean start = false;


        if (page - 5 >= 1) {
            pre = true;
        }


        if (startPage > 1) {
            start = true;
        }


        boolean next = false;

        boolean end = false;

        if (page + 5 <= totalPage || endPage < totalPage) {
            next = true;

        }

        if (endPage < totalPage) {
            end = true;
        }


        // 값 세팅

        deskList.setStartPage(startPage);
        deskList.setEndPage(endPage);
        deskList.setNowPage(page);
        deskList.setNext(next);
        deskList.setPre(pre);
        deskList.setStart(start);
        deskList.setEnd(end);
        deskList.setTotalPage(totalPage);
        return deskList;


    }


    // 데스크 정보 조회
    @Override
    public DeskDto getDesk(int id) {
        return adminRepositorySupport.getDesk(id);
    }

    @Override
    public int deskUpdate(DeskDto dto, int id) {

        Optional<Desk> desks = deskRepository.findById(id);
        Desk desk = desks.get();

        desk.setDeskId(dto.getDeskId());
        desk.setKorName(dto.getKorName());
        desk.setEngName(dto.getEngName());

        if (!(dto.getPassword() == null || dto.getPassword().equals(""))) {
            desk.setPassword(dto.getPassword());
        }
        desk.setLatitude(dto.getLatitude());
        desk.setAltitude(dto.getAltitude());
        desk.setDeleteYN(dto.getDeleteYN());

        // 해당 데스크의 지역 수정
        desk.setArea(new Area(dto.getAreaId(), null, null));

        deskRepository.save(desk);
        return 1;
    }


    // db에 해당 아이디를 가진 데스크가 있는지 확인
    @Override
    public Desk findByDeskId(String id) {


        Optional<Desk> result = deskRepository.findByDeskId(id);

        if (result.isPresent())  //db에 존재하는 경우
        {
            return result.get();

        }

        return null;   // 존재하지 않는 경우 null 반환


    }

    @Override
    public ListResult<AdminPostDto> getPostList(Integer page) {

        if (page == null) page = 1;


        ListResult<AdminPostDto> result = adminRepositorySupport.getPostList(page);

        //페이지네이션 처리

        Long totalCount = result.getTotalCount();

        int totalPage = totalCount.intValue() / 10; //총 페이지

        if (totalCount % 10 > 0) {
            totalPage++;
        }


        int startPage = ((page - 1) / 5) * 5 + 1; //시작 페이지 번호
        int endPage = startPage + 4;


        if (endPage > totalPage) endPage = totalPage;

        boolean pre = false;

        boolean start = false;


        if (page - 5 >= 1) {
            pre = true;
        }


        if (startPage > 1) {
            start = true;
        }


        boolean next = false;

        boolean end = false;

        if (page + 5 <= totalPage || endPage < totalPage) {
            next = true;

        }

        if (endPage < totalPage) {
            end = true;
        }


        // 값 세팅

        result.setStartPage(startPage);
        result.setEndPage(endPage);
        result.setNowPage(page);
        result.setNext(next);
        result.setPre(pre);
        result.setStart(start);
        result.setEnd(end);
        result.setTotalPage(totalPage);
        return result;


    }

    @Override
    public AdminPostDto getPost(long id) {

        AdminPostDto result = adminRepositorySupport.getPost(id);

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
