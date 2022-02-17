package com.ssafy.api.controller;

import com.ssafy.api.dto.*;
import com.ssafy.api.request.UserLoginPostReq;
import com.ssafy.api.response.UserLoginPostRes;
import com.ssafy.api.service.AdminService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.common.model.response.ListResult;
import com.ssafy.common.model.response.SingleResult;
import com.ssafy.common.util.JwtTokenUtil;
import com.ssafy.db.entity.Desk;
import com.ssafy.db.entity.Staff;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {


    //@ApiIgnore 는 스웨거 상 무시한다는 뜻
  //확인, 현규 확인

    @Autowired
    AdminService adminService;



     @Autowired
     PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    @ApiOperation(value = "로그인", notes = "<strong>아이디와 패스워드</strong>를 통해 로그인 한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
            @ApiResponse(code = 401, message = "인증 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 404, message = "사용자 없음", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<UserLoginPostRes> login(@RequestBody @ApiParam(value = "로그인 정보", required = true) UserLoginPostReq loginInfo) {
        String userId = loginInfo.getUserId();
        String password = loginInfo.getPassword();
        Staff admin = adminService.getStaffById(userId);
        if (admin == null) {
            return ResponseEntity.status(404).body(UserLoginPostRes.of(404, "존재하지 않는 계정입니다", null));
        }

        if (passwordEncoder.matches(password, admin.getPassword())) {


            // 로그인도 성공하고 admin 계정이 맞는경우
            if (admin.getAdminYN().equals("Y")) {
                // 유효한 패스워드가 맞는 경우, 로그인 성공으로 응답.(액세스 토큰을 포함하여 응답값 전달)
                return ResponseEntity.ok(UserLoginPostRes.of(200, "로그인 성공", JwtTokenUtil.getToken(userId, "admin")));
            } else {
                return ResponseEntity.status(403).body(UserLoginPostRes.of(403, "권한이 없습니다", null));
            }
        }
        // 유효하지 않는 패스워드인 경우, 로그인 실패로 응답.
        return ResponseEntity.status(401).body(UserLoginPostRes.of(401, "잘못된 비밀번호입니다", null));
    }


    // 상담사 목록 가져오기
    @GetMapping("/staff")
    @ApiOperation(value = "로그인", notes = "<strong>아이디와 패스워드</strong>를 통해 로그인 한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
            @ApiResponse(code = 401, message = "인증 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 404, message = "사용자 없음", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<ListResult<StaffDto>> staffList(@RequestParam(value = "page", required = false) Integer page) {

        ListResult<StaffDto> result = adminService.getConsultantList(page);

        // 유효하지 않는 패스워드인 경우, 로그인 실패로 응답.
        return ResponseEntity.status(200).body(result);
    }


    // staff 정보 조회
    @GetMapping("/staff/{id}")
    @ApiOperation(value = "로그인", notes = "<strong>아이디와 패스워드</strong>를 통해 로그인 한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
            @ApiResponse(code = 401, message = "인증 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 404, message = "사용자 없음", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity <SingleResult<StaffDto>> getStaff(@PathVariable("id") int id) {

        StaffDto result = adminService.getConsultant(id);


        return ResponseEntity.status(200).body(new SingleResult<>(200,"성공",result));
    }



    //지역목록 가져오기

    @GetMapping("/areas")
    @ApiOperation(value = "로그인", notes = "<strong>아이디와 패스워드</strong>를 통해 로그인 한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
            @ApiResponse(code = 401, message = "인증 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 404, message = "사용자 없음", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity <ListResult<AreaDto>> getAreas() {

        List<AreaDto> result = adminService.getAreas();
        return ResponseEntity.status(200).body(new ListResult<>(200,"성공",result));
    }







    //staff 정보 수정
    @PutMapping("/staff/{id}")
    @ApiOperation(value = "로그인", notes = "<strong>아이디와 패스워드</strong>를 통해 로그인 한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
            @ApiResponse(code = 401, message = "인증 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 404, message = "사용자 없음", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<BaseResponseBody> staffUpdate(@PathVariable("id") int id, @RequestBody StaffDto staffDto) {
        System.out.println("=====================시작부분======================");

        StaffDto result = adminService.getConsultant(id);





        boolean b = adminService.updateConsultant(id, staffDto);

        return ResponseEntity.status(200).body(BaseResponseBody.of(200,"성공"));
    }



    //데스크 아이디 중복 체크
    @PostMapping("/desks/idcheck")
    @ApiOperation(value = "로그인", notes = "<strong>아이디와 패스워드</strong>를 통해 로그인 한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
            @ApiResponse(code = 401, message = "인증 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 404, message = "사용자 없음", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<BaseResponseBody> deskRegister(@RequestBody UserLoginPostReq request) {



        Desk result = adminService.findByDeskId(request.getUserId());

        if(result==null)
        {
            return ResponseEntity.status(200).body(BaseResponseBody.of(200,"사용 가능한 아이디"));
        }

      else{

            return ResponseEntity.status(409).body(BaseResponseBody.of(409,"이미 존재하는 아이디"));
        }

    }





    //데스크 등록
    @PostMapping("/desks")
    @ApiOperation(value = "로그인", notes = "<strong>아이디와 패스워드</strong>를 통해 로그인 한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
            @ApiResponse(code = 401, message = "인증 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 404, message = "사용자 없음", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<BaseResponseBody> deskRegister(@RequestBody DeskDto deskDto) {


        deskDto.setPassword(passwordEncoder.encode(deskDto.getPassword()));
        adminService.deskRegister(deskDto);
        return ResponseEntity.status(201).body(BaseResponseBody.of(201,"성공"));
    }


    //데스크 수정
    @PutMapping("/desks/{id}")
    @ApiOperation(value = "로그인", notes = "<strong>아이디와 패스워드</strong>를 통해 로그인 한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
            @ApiResponse(code = 401, message = "인증 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 404, message = "사용자 없음", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<BaseResponseBody> deskUpdate(@RequestBody DeskDto deskDto, @PathVariable("id") int id) {



        if(deskDto.getPassword()==null||deskDto.getPassword().equals(""))
        {
            adminService.deskUpdate(deskDto,id);
        }

        else {
            deskDto.setPassword(passwordEncoder.encode(deskDto.getPassword()));
            adminService.deskUpdate(deskDto,id);
        }
        return ResponseEntity.status(200).body(BaseResponseBody.of(200,"성공"));
    }


    //데스크 하나 조회
    @GetMapping("/desks/{id}")
    @ApiOperation(value = "로그인", notes = "<strong>아이디와 패스워드</strong>를 통해 로그인 한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
            @ApiResponse(code = 401, message = "인증 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 404, message = "사용자 없음", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<SingleResult <DeskDto> > getDesk(@PathVariable("id") int id) {

        DeskDto result = adminService.getDesk(id);


        // 유효하지 않는 패스워드인 경우, 로그인 실패로 응답.
        return ResponseEntity.status(200).body(new SingleResult<>(200,"성공",result));
    }


    //데스크 목록 조회
    @GetMapping("/desks")
    @ApiOperation(value = "로그인", notes = "<strong>아이디와 패스워드</strong>를 통해 로그인 한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
            @ApiResponse(code = 401, message = "인증 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 404, message = "사용자 없음", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<ListResult<DeskDto>> getDeskList(@RequestParam(value = "page", required = false) Integer page) {

        ListResult<DeskDto> result = adminService.getDeskList(page);


        // 유효하지 않는 패스워드인 경우, 로그인 실패로 응답.
        return ResponseEntity.status(200).body(result);
    }






    // 게시글 목록 조회
    @GetMapping("/board/posts")
    @ApiOperation(value = "로그인", notes = "<strong>아이디와 패스워드</strong>를 통해 로그인 한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
            @ApiResponse(code = 401, message = "인증 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 404, message = "사용자 없음", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<ListResult<AdminPostDto>> getPostList(@RequestParam(value = "page", required = false) Integer page) {


        ListResult<AdminPostDto> postList = adminService.getPostList(page);



        // 유효하지 않는 패스워드인 경우, 로그인 실패로 응답.
        return ResponseEntity.status(200).body(postList);
    }


    // 게시글 상세조회
    @GetMapping("/board/posts/{id}")
    @ApiOperation(value = "로그인", notes = "<strong>아이디와 패스워드</strong>를 통해 로그인 한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
            @ApiResponse(code = 401, message = "인증 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 404, message = "사용자 없음", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<SingleResult<AdminPostDto>> getPost(@PathVariable("id") long id) {


        AdminPostDto post = adminService.getPost(id);



        return ResponseEntity.status(200).body(new SingleResult<>(200,"성공",post));
    }


    // 게시글 삭제
    @DeleteMapping("/board/posts/{id}")
    @ApiOperation(value = "로그인", notes = "<strong>아이디와 패스워드</strong>를 통해 로그인 한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
            @ApiResponse(code = 401, message = "인증 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 404, message = "사용자 없음", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<BaseResponseBody> deletePost(@PathVariable("id") int id) {


        boolean b = adminService.postDelete(id);



        return ResponseEntity.status(200).body(BaseResponseBody.of(200,"성공"));
    }


}




