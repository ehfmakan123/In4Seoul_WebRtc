package com.ssafy.api.controller;


import com.ssafy.api.dto.AreaDto;
import com.ssafy.api.dto.PostDto;
import com.ssafy.api.dto.SelectedDeskDto;
import com.ssafy.api.request.PostReq;
import com.ssafy.api.request.StaffRequest;
import com.ssafy.api.response.DeskLoginPostRes;
import com.ssafy.api.service.DeskService;
import com.ssafy.api.service.FirebaseService;
import com.ssafy.api.service.OpenviduService;
import com.ssafy.common.auth.SsafyUserDetails;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.common.model.response.ListResult;
import com.ssafy.common.model.response.SingleResult;
import com.ssafy.common.util.JwtTokenUtil;
import com.ssafy.db.entity.Area;
import com.ssafy.db.entity.Desk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/desk")
public class DeskController {


    @Autowired
    DeskService deskService;

    @Autowired
    PasswordEncoder passwordEncoder;


    @Autowired
    FirebaseService firebaseService;

    @Autowired
    OpenviduService openviduService;


    // 데스크 계정 로그인
    @PostMapping("/login")
    public ResponseEntity<DeskLoginPostRes> login(@RequestBody StaffRequest request) {

        // db에 존재하는 계정인지 확인
        String userId = request.getUserId();
        String password = request.getPassword();


        Desk result = deskService.findByDeskId(userId);

        if (result == null) {
            return ResponseEntity.status(404).body(DeskLoginPostRes.of(404, "존재하지 않는 계정입니다", null, null, null, null, null, null, null));
        }


        if (passwordEncoder.matches(password, result.getPassword())) {


            Area area = result.getArea();
            area.getEngName();
            area.getKorName();
            area.getId();


            DeskLoginPostRes data = DeskLoginPostRes.of(200, "로그인 성공", JwtTokenUtil.getToken(userId, "desk"), result.getKorName(), result.getEngName(), result.getId(), area.getKorName(), area.getEngName(), area.getId());


            return ResponseEntity.status(200).body(data);


        } else {
            return ResponseEntity.status(401).body(DeskLoginPostRes.of(401, "유효하지 않은 비밀번호입니다", null,null, null, null, null, null, null));

        }


    }




    // 데스크 계정 로그아웃
    @PostMapping("/logout")
    public ResponseEntity<BaseResponseBody> logout(@RequestBody Map<String,String> request,@ApiIgnore Authentication authentication) {


        String password = request.get("password");


        SsafyUserDetails userDetails = (SsafyUserDetails) authentication.getDetails();
        String deskPassword = userDetails.getDeskPassword();


        if (passwordEncoder.matches(password, deskPassword)) {
            return ResponseEntity.status(200).body(BaseResponseBody.of(200,"성공"));
        } else {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401,"유효하지 않은 비밀번호입니다"));

        }


    }



    // 글 작성
    @PostMapping("/posts")
    public ResponseEntity<BaseResponseBody> registerPost(@RequestBody PostReq postReq, @ApiIgnore Authentication authentication) {


        SsafyUserDetails userDetails = (SsafyUserDetails) authentication.getDetails();
        String userId = userDetails.getDeskId();  // 토큰으로부터 데스크 아이디를 얻어온다



        postReq.setPassword(passwordEncoder.encode(postReq.getPassword()));


        deskService.registerPost(userId,postReq);




        return ResponseEntity.status(200).body(BaseResponseBody.of(201,"성공"));





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



//글 목록 조회  ( desk별로 조회 페이징 처리도 해야함)
    @GetMapping("/posts")
    public ResponseEntity <ListResult<PostDto>> getPostList(@RequestParam(value = "desk" , required = false) Integer desk, @RequestParam(value = "page",required = false) Integer page,@ApiIgnore Authentication authentication) {



        SsafyUserDetails userDetails = (SsafyUserDetails) authentication.getDetails();
        int userId = userDetails.getDestPK();


        if(desk==null)
            desk=userId;

        if(page==null) page=1;

        ListResult<PostDto> postList = deskService.getPostList(desk, page);


        return ResponseEntity.status(200).body(postList);
    }





// 게시글 비밀번호 확인
@PostMapping("/posts/{id}")
public ResponseEntity <BaseResponseBody> passwordCheck(@PathVariable(value = "id") int id, @RequestBody Map<String, String> body) {


    String password = body.get("password");


    String postPassword = deskService.getPostPassword(id);


    if (passwordEncoder.matches(password, postPassword)) {

        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "비밀번호 일치"));


    } else {
        return ResponseEntity.status(401).body(BaseResponseBody.of(401, "유효하지 않은 비밀번호입니다"));

    }


}





    //화상 상담 관련
    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////



    //상담 신청   발급받은 openvidu 토큰과 session아이디
    @PostMapping("/meeting")        //url 이 이게 맞을까..
    public ResponseEntity<SingleResult<Map<String,String>>> Meeting(@ApiIgnore Authentication authentication) {


        SsafyUserDetails userDetails = (SsafyUserDetails) authentication.getDetails();
        String deskId = userDetails.getDeskId();
        int deskPk=userDetails.getDestPK();
        int areaId=userDetails.getDeskAreaId();


        // 요청 데스크와 상담 가능한 상담사가 있는지 체크






        String token = openviduService.createSession(deskId);//  데스크 아이디 넘겨주면서 방 만들기

        System.out.println("token="+token);

        Map<String,String> map=new HashMap<>();

        map.put("token",token);
        map.put("sessionId",deskId);

        boolean result = firebaseService.sendMessage(deskPk, areaId,deskId);

        if(result)
        {
            return ResponseEntity.status(200).body(new SingleResult<>(200,"성공",map));
        }

        else{

            openviduService.disconnectSession(deskId,token);

            return ResponseEntity.status(409).body(new SingleResult<>(409,"상담 가능한 상담사가 없습니다",null));



        }

    }



    //상담 종료  (


    @DeleteMapping ("/meeting/end")        //url 이 이게 맞을까..
    public ResponseEntity<BaseResponseBody> MeetingEnd(@ApiIgnore Authentication authentication) {


        SsafyUserDetails userDetails = (SsafyUserDetails) authentication.getDetails();
        String deskId = userDetails.getDeskId();
        int deskPk=userDetails.getDestPK();



        openviduService.disconnectSession(deskId,null); // openvidu 삭제


        // 요청 데스크와 상담 가능한 상담사가 있는지 체크
        deskService.deleteWaitingList(deskPk);

        return ResponseEntity.status(200).body(BaseResponseBody.of(200,"성공"));



    }

}
