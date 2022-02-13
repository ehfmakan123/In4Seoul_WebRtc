package com.ssafy.api.response;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class staffLoginPostRes extends  UserLoginPostRes{

    String name;




    public static staffLoginPostRes of(Integer statusCode, String message, String accessToken, String name) {
        staffLoginPostRes res = new staffLoginPostRes();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        res.setAccessToken(accessToken);
        res.setName(name);
        return res;
    }
}
