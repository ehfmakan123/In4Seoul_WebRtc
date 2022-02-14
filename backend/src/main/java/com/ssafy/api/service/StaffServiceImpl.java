package com.ssafy.api.service;


import com.ssafy.api.dto.MeetingLogDto;
import com.ssafy.api.dto.StaffDto;
import com.ssafy.api.request.StaffRequest;
import com.ssafy.common.model.response.ListResult;
import com.ssafy.db.entity.Desks;
import com.ssafy.db.entity.MeetingLog;
import com.ssafy.db.entity.Staff;
import com.ssafy.db.repository.DeskRepository;
import com.ssafy.db.repository.MeetingLogRepository;
import com.ssafy.db.repository.StaffRepository;
import com.ssafy.db.repository.StaffRepositorySupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StaffServiceImpl implements StaffService{
    @Autowired
    StaffRepository staffRepository;

    @Autowired
    StaffRepositorySupport staffRepositorySupport;

    @Autowired
    DeskRepository deskRepository;


    @Autowired
    MeetingLogRepository meetingLogRepository;


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
        staff.setFcmToken("0"); //기본값 0 로그아웃 상태
        staff.setMatchYN("N"); // 매치가능 여부 N

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



    //내 정보 조회
    @Override
    public StaffDto getStaffDtoByStaffId(String id) {


        return staffRepositorySupport.getStaffDto(id);
    }




    //내 정보 수정 (비밀번호 부분을 해윤님하고 같이 이야기해보고
    @Override
    public boolean updateStaff(StaffDto dto) {


        Optional<Staff> result = staffRepository.findByStaffId(dto.getUserId());
        Staff staff=result.get();


        staff.setPhone(dto.getPhone());
        staff.setEmail(dto.getEmail());
        staff.setName(dto.getName());

         staffRepository.save(staff);


         return true;
    }

    @Override
    public ListResult<MeetingLogDto> getMeetingLogList(int id, Integer page) {   //상담목록 가져오기

        System.out.println("page값이 없으면? ="+page);

        if(page==null)
        {
            page=1;
        }

        return staffRepositorySupport.getMeetingLog(id,page);
    }





    //상담 시작 시 미팅 로그에 등록
    @Override
    public int meetingLogStart(int staffPk, String sessionId) {


        // sessionId를 통해 desk의 pk값을 가져온다
        Optional<Desks> result = deskRepository.findByDeskId(sessionId);
        Desks desk = result.get();


        MeetingLog meetingLog = new MeetingLog();
            meetingLog.setDesks(new Desks(desk.getId()));
            meetingLog.setStaff(new Staff(staffPk));


        MeetingLog meetingLog1 = meetingLogRepository.save(meetingLog);

        return meetingLog1.getId();
    }


    // 상담종료 시   meetingLog 기록하고  상담사 상태를 상담 가능상태로 바꿔준다?

    @Override
    public void meetingLogEnd(int meetingLogId, String content, int staffId) {


        // 저장된 meetingLog를 meetingLogId(pk)를 통해 찾는다


        Optional<MeetingLog> optionalMeetingLog =  meetingLogRepository.findById(meetingLogId);
        MeetingLog meetingLog = optionalMeetingLog.get();

        meetingLog.setContent(content);
        meetingLogRepository.save(meetingLog); //수정해서 저장
        
        //스태프 상태 수정
        Optional<Staff> optionalStaff = staffRepository.findById(staffId);
        Staff staff = optionalStaff.get();
        staff.setMatchYN("N");

    }
}
