package com.ssafy.api.service;


import io.openvidu.java.client.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static io.openvidu.java.client.OpenViduRole.PUBLISHER;

@Service
public class OpenviduServiceImpl implements OpenviduService {

    // OpenVidu object as entrypoint of the SDK   openvidu 객체
    private OpenVidu openVidu;

    // Collection to pair session names and OpenVidu Session objects
    private Map<String, Session> mapSessions = new ConcurrentHashMap<>();  //방 목록


    // Collection to pair session names and tokens (the inner Map pairs tokens and
    // role associated)
    private Map<String, Map<String, OpenViduRole>> mapSessionNamesTokens = new ConcurrentHashMap<>();
    // 각 방의 토큰 목록



    // URL where our OpenVidu server is listening
    private String OPENVIDU_URL;  //오픈비두 서버 url
    // Secret shared with our OpenVidu server
    private String SECRET;  //설정한 비밀번호


    public OpenviduServiceImpl(@Value("${openvidu.secret}") String secret, @Value("${openvidu.url}") String openviduUrl) {
        this.SECRET = secret;
        this.OPENVIDU_URL = openviduUrl;
        this.openVidu = new OpenVidu(OPENVIDU_URL, SECRET);
    }




    /*
    상담 신청으로  deskId로 방을 만들고 토큰을 발급받아서 관리
     */
    @Override
    public String createSession(String deskId) {



        // 방 이름
        String sessionName=deskId;


        // Role associated to this user
        OpenViduRole role = PUBLISHER; // 권한 부여



        String serverData = "{\"serverData\": \"" + "data" + "\"}"; //요게 뭐람



        // Build connectionProperties object with the serverData and the role
        ConnectionProperties connectionProperties = new ConnectionProperties.Builder().type(ConnectionType.WEBRTC).data(serverData).role(role).build();


        try {


            //데스크 아이디로 방 생성
            // Create a new OpenVidu Session
            Session session = this.openVidu.createSession();

            System.out.println("여기는 오고???");

            String token = session.createConnection(connectionProperties).getToken(); //토큰 생성



            // 세션과 토큰 최신화
            this.mapSessions.put(sessionName, session);
            this.mapSessionNamesTokens.put(sessionName, new ConcurrentHashMap<>());
            this.mapSessionNamesTokens.get(sessionName).put(token, role);

            return token;
        }

        catch(Exception e)  //  세션 생성 과정에서 실패
        {

            System.out.println("오픈비두 토큰 발급중 에러발생" +e);
            return "";
        }

    }
}
