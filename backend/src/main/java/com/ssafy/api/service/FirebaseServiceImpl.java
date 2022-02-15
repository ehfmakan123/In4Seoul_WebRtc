package com.ssafy.api.service;


import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.ssafy.db.entity.Area;
import com.ssafy.db.entity.Desk;
import com.ssafy.db.entity.WaitingList;
import com.ssafy.db.entity.Staff;
import com.ssafy.db.repository.WaitingListRepository;
import com.ssafy.db.repository.StaffRepository;
import com.ssafy.db.repository.StaffRepositorySupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class FirebaseServiceImpl implements FirebaseService {

    @Autowired
    StaffRepository staffRepository;


    @Autowired
    WaitingListRepository waitingListRepository;


    @Autowired
    StaffRepositorySupport staffRepositorySupport;

    @Override
    public boolean saveToken(String token, String userId) {


        Optional<Staff> result = staffRepository.findByStaffId(userId);

        Staff staff = result.get();
        staff.setMatchYN("N");
        staff.setFcmToken(token);


        staffRepository.save(staff);
        return true;

    }


    //desk에서 해당하는 지역 상담사한테 알림 메세지 보내기
    @Override
    public boolean sendMessage(int deskPk, int areaId,String deskId) {


        // 현재 상담 요청 지역에 해당하는 상담 가능한 상담사 목록
        List<Staff> staffList = staffRepositorySupport.getStaffList(areaId);


        System.out.println(staffList.size());

        if (staffList.size() != 0) {
            WaitingList waitingList = new WaitingList();
            waitingList.setDesk(new Desk(deskPk));
            waitingList.setArea(new Area(areaId));


            // 상담요청 테이블에 자신의 상담 신청을 등록 (meeting)에 등록
            WaitingList result = waitingListRepository.save(waitingList);


            for (Staff staff : staffList) {


                System.out.println(staff.getFcmToken());
                Message message = Message.builder().putData("title", "상담 신청")
                        .putData("content", deskId)
                        .setToken(staff.getFcmToken())
                        .build();


                FirebaseMessaging.getInstance().sendAsync(message);


            }

            return true;
        } else {

            return false;
        }


    }


    // 같은 지역을 가진 대기 목록을 카운트해서 보내기
    @Override
    public long getMeeting(int areaId) {


        return staffRepositorySupport.getMeeting(areaId);

    }


    // 상담사의 상담연결 수락
    @Override
    public String MeetingConnect(int areaId, String userId) {

        WaitingList waitingList = staffRepositorySupport.MeetingConnect(areaId);
        String sessionId = null;

        if (waitingList == null)   // 매칭할 상담이 없음
        {
            return null;
        } else {
              /*
          삭제 후에 이미 삭제된 키값으로 또 삭제를 한 경우

          삭제 전에 먼저 해당 키값(id)을 가진 객체가 존재하는 지 확인하고 없다면

        IllegalArgumentException을 던진다


         */


            try {

                sessionId = waitingList.getDesk().getDeskId(); // openvidu에 연결할 세션 아이디

                waitingListRepository.delete(waitingList);


                // 내 상태코드 변경 match_YN을 Y로

                Optional<Staff> result = staffRepository.findByStaffId(userId);

                Staff staff = result.get();


                staff.setMatchYN("Y");  //상담중


            } catch (Exception e) {
                return null;
            }
            return sessionId;
        }

    }
}
