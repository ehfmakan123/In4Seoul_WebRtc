package com.ssafy.api.service;

public interface FirebaseService {



    // 상담사가 발급받은 토큰 db에 저장
    boolean saveToken(String token, String userId);


    //desk에서 상담 요청 후 메세지 보내기
    boolean sendMessage(String deskId, int areaId);



    // 같은 지역의 코드를 가진 대기 목록을 카운트해서 가져오기
    long getMeeting(int areaId);

}
