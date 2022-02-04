package com.ssafy.api.request;


import lombok.Getter;
import lombok.Setter;



// 상담사 회원가입 처리 시 프론트에서 넘겨받을 데이터
// 로그인 시 사용
@Getter
@Setter
public class StaffRequest {


    String userId;
    String password;
    String passwordConfirmation;
    String name;
    String phone;
    String email;


}
