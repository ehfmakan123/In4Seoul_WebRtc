package com.ssafy.api.service;

public interface OpenviduService {

    String createSession(String deskId);

   String connectSession(String sessionId);
   boolean disconnectSession(String sessionId, String openviduToken);

}
