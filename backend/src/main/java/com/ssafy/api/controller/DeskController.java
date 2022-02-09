package com.ssafy.api.controller;


import com.ssafy.api.dto.AreaDto;
import com.ssafy.api.dto.PostDto;
import com.ssafy.api.dto.SelectedDeskDto;
import com.ssafy.api.request.PostReq;
import com.ssafy.api.request.StaffRequest;
import com.ssafy.api.response.UserLoginPostRes;
import com.ssafy.api.service.DeskService;
import com.ssafy.api.service.FirebaseService;
import com.ssafy.common.auth.SsafyUserDetails;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.common.model.response.ListResult;
import com.ssafy.common.model.response.SingleResult;
import com.ssafy.common.util.JwtTokenUtil;
import com.ssafy.db.entity.BaseEntity;
import com.ssafy.db.entity.Desks;
import com.ssafy.db.entity.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@RequestMapping("api/desk")
public class DeskController {


    @Autowired
    DeskService deskService;

    @Autowired
    PasswordEncoder passwordEncoder;


    @Autowired
    FirebaseService firebaseService;



    // 데스크 계정 로그인
    @PostMapping("/login")
    public ResponseEntity<UserLoginPostRes> login(@RequestBody StaffRequest request) {

        // db에 존재하는 계정인지 확인
        String userId = request.getUserId();
        String password = request.getPassword();


        Desks result = deskService.findByDeskId(userId);

        if (result == null) {
            return ResponseEntity.status(404).body(UserLoginPostRes.of(404, "존재하지 않는 계정입니다", null));
        }


        if (passwordEncoder.matches(password, result.getPassword())) {

            return ResponseEntity.status(200).body(UserLoginPostRes.of(200, "로그인 성공", JwtTokenUtil.getToken(userId, "desk")));


        } else {
            return ResponseEntity.status(401).body(UserLoginPostRes.of(401, "유효하지 않은 비밀번호입니다", null));

        }


    }



    // 글 작성
    @PostMapping("/posts")
    public ResponseEntity<BaseResponseBody> registerPost(@RequestBody PostReq postReq, @ApiIgnore Authentication authentication) {


        SsafyUserDetails userDetails = (SsafyUserDetails) authentication.getDetails();
        String userId = userDetails.getDeskId();  // 토큰으로부터 데스크 아이디를 얻어온다

        deskService.registerPost(userId,postReq);


        return ResponseEntity.status(200).body(BaseResponseBody.of(200,"성공"));





    }


//글 상세 조회
    @GetMapping("/posts/{id}")
    public ResponseEntity<SingleResult<PostDto>> registerPost(@PathVariable("id") long id) {



        PostDto result = deskService.getPost(id);


        return ResponseEntity.status(200).body(new SingleResult<>(200,"성공",result));

    }




// 글 수정

    @PutMapping("/posts/{id}")
    public ResponseEntity<BaseResponseBody> updatePost(@PathVariable("id") long id, @RequestBody PostReq postReq) {



      deskService.updatePost(id,postReq);


        return ResponseEntity.status(200).body(BaseResponseBody.of(200,"성공"));




    }

    //글 삭제

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<BaseResponseBody> DeletePost(@PathVariable("id") long id) {



        deskService.deletePost(id);


        return ResponseEntity.status(200).body(BaseResponseBody.of(200,"성공"));




    }



    // 지역 목록 가져오기

    @GetMapping("/areas")
    public ResponseEntity <ListResult<AreaDto>> getAreas() {

        List<AreaDto> result = deskService.getAreas();
        return ResponseEntity.status(200).body(new ListResult<>(200,"성공",result));
    }





    //  선택 지역에 해당하는 데스크 목록 가져오기
    @GetMapping("/areas/{id}")        //url 이 이게 맞을까..
    public ResponseEntity <ListResult<SelectedDeskDto>> getDeskList(@PathVariable("id") int id) {


        List<SelectedDeskDto> result = deskService.getDeskList(id);

        return ResponseEntity.status(200).body(new ListResult<>(200,"성공",result));
    }



    //화상 상담 관련
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////



    //상담 신청
    @PostMapping("/meeting")        //url 이 이게 맞을까..
    public ResponseEntity <BaseResponseBody> Meeting(@ApiIgnore Authentication authentication) {


        SsafyUserDetails userDetails = (SsafyUserDetails) authentication.getDetails();
        String DeskId = userDetails.getDeskId();
        int areaId=userDetails.getDeskAreaId();


        firebaseService.sendMessage(DeskId,areaId);
        return ResponseEntity.status(200).body(BaseResponseBody.of(200,"성공"));
    }


}
