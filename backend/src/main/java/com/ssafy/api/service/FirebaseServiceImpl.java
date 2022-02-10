package com.ssafy.api.service;


import com.google.api.core.ApiFuture;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.ssafy.db.entity.Meeting;
import com.ssafy.db.entity.Staff;
import com.ssafy.db.repository.MeetingRepository;
import com.ssafy.db.repository.StaffRepository;
import com.ssafy.db.repository.StaffRepositorySupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class FirebaseServiceImpl implements FirebaseService{

    @Autowired
    StaffRepository staffRepository;



    @Autowired
    MeetingRepository meetingRepository;


    @Autowired
    StaffRepositorySupport staffRepositorySupport;
    @Override
    public boolean saveToken(String token, String userId) {


        Optional<Staff> result = staffRepository.findByStaffId(userId);

        Staff staff = result.get();


        staff.setMatchYN("Y");
        staff.setFcmToken(token);


         staffRepository.save(staff);
         return true;

    }





    //desk에서 해당하는 지역 상담사한테 알림 메세지 보내기
    @Override
    public boolean sendMessage(String deskId, int areaId) {


        Meeting meeting = new Meeting();
        meeting.setDeskId(deskId);
        meeting.setAreaId(areaId);


        System.out.println("deskId="+deskId+" areaId="+areaId);
        // 상담요청 테이블에 자신의 상담 신청을 등록 (meeting)에 등록
        Meeting result = meetingRepository.save(meeting);





        // 현재 상담 요청 지역에 해당하는 상담 가능한 상담사 목록
        List<Staff> staffList = staffRepositorySupport.getStaffList(areaId);


//        System.out.println(staffList.size());
//
//
//        for (Staff staff : staffList) {
//
//
//            System.out.println(staff.getFcmToken());
//            Message message = Message.builder().putData("title", "상담 신청")
//                    .putData("content", deskId)
//                    .setToken(staff.getFcmToken())
//                    .build();
//
//
//           FirebaseMessaging.getInstance().sendAsync(message);
//
//
//        }
return true;


    }



    // 같은 지역을 가진 대기 목록을 카운트해서 보내기
    @Override
    public long getMeeting(int areaId) {


        return staffRepositorySupport.getMeeting(areaId);

    }
}
