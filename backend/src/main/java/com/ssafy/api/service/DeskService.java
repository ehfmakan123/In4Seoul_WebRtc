package com.ssafy.api.service;

import com.ssafy.api.dto.AreaDto;
import com.ssafy.api.dto.PostDto;
import com.ssafy.api.dto.SelectedDeskDto;
import com.ssafy.api.request.PostReq;
import com.ssafy.common.model.response.ListResult;
import com.ssafy.db.entity.Desk;

import java.util.List;

public interface DeskService {



    // 로그인할때 사용
    Desk findByDeskId(String id);



    // 게시글 등록
    boolean registerPost(String id, PostReq dto);



    // 게시글 조회
    PostDto getPost(long id);




    // 게시글 수정
    boolean updatePost(long id, PostReq dto);


    //게시글 삭제
    boolean deletePost(long id);


    String getPostPassword(long id);



    // 지역 목록 가져오기
List<AreaDto> getAreas();



//선택지역에 해당하는 데스크 목록 가져오기
List<SelectedDeskDto> getDeskList(int id);



// 게시글 목록 가져오기
ListResult<PostDto> getPostList(Integer desk, Integer page);





    void deleteWaitingList(long deskPk);



}
