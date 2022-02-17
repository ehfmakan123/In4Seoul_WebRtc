package com.ssafy.api.service;

public interface FirebaseService {



    // 상담사가 발급받은 토큰 db에 저장
    boolean saveToken(String token, String userId);


    //desk에서 상담 요청 후 메세지 보내기
    boolean sendMessage(int deskPk, int areaId, String deskId);



    // 같은 지역의 코드를 가진 대기 목록을 카운트해서 가져오기
    long getMeeting(int areaId);



    // 상담사의 상담연결 수락  (  상담대기목록 select   후  select한 부분을 삭제 삭제 시 에러가 발생하면 매칭할 상담이 없습니다 or 이미 매칭되었습니다
    //         select에서 가져오는게 없어도 매칭할 상담이 없습니다
    String MeetingConnect(int areaId,String userId);

}
