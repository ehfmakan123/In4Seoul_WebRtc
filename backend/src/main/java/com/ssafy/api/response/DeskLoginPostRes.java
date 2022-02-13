package com.ssafy.api.response;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeskLoginPostRes extends  UserLoginPostRes{



String deskKorName;
String deskEngName;
Integer deskPk;
String areaKorName;
String areaEngName;
Integer areaPk;


   static public DeskLoginPostRes of(Integer statusCode, String message, String accessToken,String deskKorName, String deskEngName, Integer deskPk, String areaKorName, String areaEngName, Integer areaPk) {

        DeskLoginPostRes desk = new DeskLoginPostRes();

        desk.setAreaEngName(areaEngName);
        desk.setAreaKorName(areaKorName);
        desk.setAreaPk(areaPk);
        desk.setDeskPk(deskPk);
        desk.setDeskEngName(deskEngName);
        desk.setDeskKorName(deskKorName);


        desk.setAccessToken(accessToken);

        desk.setStatusCode(statusCode);
        desk.setMessage(message);

        return desk;
    }

}
