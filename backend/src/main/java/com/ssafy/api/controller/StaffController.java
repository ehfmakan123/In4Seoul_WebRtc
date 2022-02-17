package com.ssafy.api.controller;


import com.ssafy.api.dto.MeetingLogDto;
import com.ssafy.api.dto.StaffDto;
import com.ssafy.api.request.StaffRequest;
import com.ssafy.api.response.UserLoginPostRes;
import com.ssafy.api.response.staffLoginPostRes;
import com.ssafy.api.service.FirebaseService;
import com.ssafy.api.service.OpenviduService;
import com.ssafy.api.service.StaffService;
import com.ssafy.common.auth.SsafyUserDetails;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.common.model.response.ListResult;
import com.ssafy.common.model.response.SingleResult;
import com.ssafy.common.util.JwtTokenUtil;
import com.ssafy.db.entity.Staff;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@RequestMapping("/api/staff")
public class StaffController {


    @Autowired
    StaffService staffService;


    @Autowired
    FirebaseService firebaseService;

    @Autowired
    OpenviduService openviduService;

    @Autowired
    PasswordEncoder passwordEncoder;


    // 아이디 중복 확인
    @PostMapping("/idcheck")
    @ApiOperation(value = "아이디 중복확인", notes = "<strong>이미 존재하는 아이디인지 확인한다.</strong>")
    @ApiResponses({
            @ApiResponse(code = 200, message = "사용 가능한 아이디", response = UserLoginPostRes.class),
            @ApiResponse(code = 409, message = "이미 존재하는 아이디", response = BaseResponseBody.class),
            @ApiResponse(code = 404, message = "사용자 없음", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<BaseResponseBody> checkId(@RequestBody StaffRequest request) {


        boolean b = staffService.checkId(request.getUserId());
        System.out.println(b);
        if (b) {
            return ResponseEntity.status(200).body(BaseResponseBody.of(200, "사용 가능한 아이디"));
        } else {
            return ResponseEntity.status(409).body(BaseResponseBody.of(409, "이미 존재하는 아이디"));
        }

    }


    //회원 가입
    @PostMapping("/signup")
    @ApiOperation(value = "아이디 중복확인", notes = "<strong>이미 존재하는 아이디인지 확인한다.</strong>")
    @ApiResponses({
            @ApiResponse(code = 201, message = "회원가입 성공", response = UserLoginPostRes.class),
            @ApiResponse(code = 400, message = "비밀번호가 일치하지 않습니다", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<BaseResponseBody> register(@RequestBody StaffRequest request) {

        if (!request.getPassword().equals(request.getPasswordConfirmation())) {
            return ResponseEntity.status(400).body(BaseResponseBody.of(400, "비밀번호가 일치하지 않습니다 "));
        } else {


            request.setPassword(passwordEncoder.encode(request.getPassword()));

            staffService.register(request);
            return ResponseEntity.status(201).body(BaseResponseBody.of(201, "회원가입 성공"));

        }

    }


    //로그인
    @PostMapping("/login")
    @ApiOperation(value = "아이디 중복확인", notes = "<strong>이미 존재하는 아이디인지 확인한다.</strong>")
    @ApiResponses({
            @ApiResponse(code = 201, message = "회원가입 성공", response = UserLoginPostRes.class),
            @ApiResponse(code = 400, message = "비밀번호가 일치하지 않습니다", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<staffLoginPostRes> login(@RequestBody StaffRequest request) {

        // db에 존재하는 계정인지 확인
        String userId = request.getUserId();
        String password = request.getPassword();


        Staff result = staffService.getStaffByStaffId(userId);

        if (result == null) {
            return ResponseEntity.status(404).body(staffLoginPostRes.of(404, "존재하지 않는 계정입니다", null, null));
        }

        if (passwordEncoder.matches(password, result.getPassword())) {

            if(result.getApproveYN().equals("Y"))
            {
                staffLoginPostRes data = staffLoginPostRes.of(200, "로그인 성공", JwtTokenUtil.getToken(userId, "staff"), result.getName());

                return ResponseEntity.status(200).body(data);
            }

            else
            {
                staffLoginPostRes data = staffLoginPostRes.of(403, "승인처리가 되지 않았습니다",null, null);
                return ResponseEntity.status(403).body(data);




            }






        } else {
            return ResponseEntity.status(401).body(staffLoginPostRes.of(401, "유효하지 않은 비밀번호입니다", null, null));

        }


    }










    //로그아웃
    @PostMapping("/logout")
    public ResponseEntity<BaseResponseBody> logout(@ApiIgnore Authentication authentication) {


        SsafyUserDetails userDetails = (SsafyUserDetails) authentication.getDetails();
        String userId = userDetails.getStaffId();



        staffService.logout(userId);


            return ResponseEntity.status(200).body(BaseResponseBody.of(200,"성공"));




    }





    //내 정보 조회
    @GetMapping("/me")
    @ApiOperation(value = "내 정보 조회", notes = "<strong>이미 존재하는 아이디인지 확인한다.</strong>")
    @ApiResponses({
            @ApiResponse(code = 201, message = "회원가입 성공", response = UserLoginPostRes.class),
            @ApiResponse(code = 400, message = "비밀번호가 일치하지 않습니다", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<SingleResult<StaffDto>> getStaffInfo(@ApiIgnore Authentication authentication) {


        /**
         * 요청 헤더 액세스 토큰이 포함된 경우에만 실행되는 인증 처리이후, 리턴되는 인증 정보 객체(authentication) 통해서 요청한 유저 식별.
         * 액세스 토큰이 없이 요청하는 경우, 403 에러({"error": "Forbidden", "message": "Access Denied"}) 발생.

         */

        SsafyUserDetails userDetails = (SsafyUserDetails) authentication.getDetails();
        String userId = userDetails.getStaffId();

        StaffDto result = staffService.getStaffDtoByStaffId(userId);


        return ResponseEntity.status(200).body(new SingleResult<>(200, "성공", result));


    }


    //내 정보 수정
    @PutMapping("/me")
    @ApiOperation(value = "내 정보 조회", notes = "<strong>이미 존재하는 아이디인지 확인한다.</strong>")
    @ApiResponses({
            @ApiResponse(code = 201, message = "회원가입 성공", response = UserLoginPostRes.class),
            @ApiResponse(code = 400, message = "비밀번호가 일치하지 않습니다", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<BaseResponseBody> updateStaffInfo(@ApiIgnore Authentication authentication, @RequestBody StaffDto dto) {


        /**
         * 요청 헤더 액세스 토큰이 포함된 경우에만 실행되는 인증 처리이후, 리턴되는 인증 정보 객체(authentication) 통해서 요청한 유저 식별.
         * 액세스 토큰이 없이 요청하는 경우, 403 에러({"error": "Forbidden", "message": "Access Denied"}) 발생.

         */


        SsafyUserDetails userDetails = (SsafyUserDetails) authentication.getDetails();
        String userId = userDetails.getStaffId();
        dto.setUserId(userId);


        staffService.updateStaff(dto);


        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "성공"));

    }


    //내 상담기록 목록 조회
    @GetMapping("/meeting-logs")
    @ApiOperation(value = "상담기록 목록 조회", notes = "<strong>이미 존재하는 아이디인지 확인한다.</strong>")
    @ApiResponses({
            @ApiResponse(code = 201, message = "회원가입 성공", response = UserLoginPostRes.class),
            @ApiResponse(code = 400, message = "비밀번호가 일치하지 않습니다", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<ListResult<MeetingLogDto>> getMeetingLog(@ApiIgnore Authentication authentication, @RequestParam(value = "page", required = false) Integer page) {


        /**
         * 요청 헤더 액세스 토큰이 포함된 경우에만 실행되는 인증 처리이후, 리턴되는 인증 정보 객체(authentication) 통해서 요청한 유저 식별.
         * 액세스 토큰이 없이 요청하는 경우, 403 에러({"error": "Forbidden", "message": "Access Denied"}) 발생.

         SsafyUserDetails userDetails = (SsafyUserDetails)authentication.getDetails();
         String userId = userDetails.getUsername();

         */

        // 위의 과정을 통해 유저 아이디를 뽑아냈다고 가정
        SsafyUserDetails userDetails = (SsafyUserDetails) authentication.getDetails();
        String userId = userDetails.getStaffId();

        StaffDto staff = staffService.getStaffDtoByStaffId(userId);
        ListResult<MeetingLogDto> meetingLogList = staffService.getMeetingLogList(staff.getId(), page);


        return ResponseEntity.status(200).body(meetingLogList);


    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////


    // 상담원 로그인 성공 후 fcm토큰 발급해서 백엔드로 넘겨준다

    @PostMapping("/fcmtoken")
    @ApiOperation(value = "아이디 중복확인", notes = "<strong>이미 존재하는 아이디인지 확인한다.</strong>")
    @ApiResponses({
            @ApiResponse(code = 201, message = "회원가입 성공", response = UserLoginPostRes.class),
            @ApiResponse(code = 400, message = "비밀번호가 일치하지 않습니다", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<BaseResponseBody> fcmToken(@RequestBody Map<String, String> body, @ApiIgnore Authentication authentication) {


        //프론트에서 발급받은 토큰
        String token = body.get("token");

        // 토큰 발급받은 상담사 아이디
        SsafyUserDetails userDetails = (SsafyUserDetails) authentication.getDetails();
        String userId = userDetails.getStaffId();

        firebaseService.saveToken(token, userId);


        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "성공"));
    }


    // 같은 지역의 대기중인 상담요청 개수를 가지고 온다
    @GetMapping("/meeting")

    public ResponseEntity<SingleResult<Map<String, Long>>> meeting(@ApiIgnore Authentication authentication) {


        // 토큰 발급받은 상담사 아이디
        SsafyUserDetails userDetails = (SsafyUserDetails) authentication.getDetails();

        int areaId = userDetails.getStaffAreaId();


        Map<String, Long> map = new HashMap<>();

        System.out.println("지역코드=" + areaId);
        long result = firebaseService.getMeeting(areaId);

        map.put("count", result);
        return ResponseEntity.status(200).body(new SingleResult<>(200, "성공", map));
    }


    // 상담 연결 요청
    @PostMapping("/meeting")

    public ResponseEntity<SingleResult<Map<String, String>>> meetingConnect(@ApiIgnore Authentication authentication) {


        Map<String, String> map = new HashMap<>();
        // 토큰 발급받은 상담사 아이디
        SsafyUserDetails userDetails = (SsafyUserDetails) authentication.getDetails();


        int areaId = userDetails.getStaffAreaId();  //지역 번호
        String userId = userDetails.getStaffId();

        System.out.println("지역번호="+areaId);

        String sessionId = firebaseService.MeetingConnect(areaId, userId);

        if (sessionId != null)  // 매칭에 성공했으면 해당 deskId에 대한 토큰 발급

        {
            String token = openviduService.connectSession(sessionId);

            map.put("token", token);
            map.put("sessionId", sessionId);
            return ResponseEntity.status(200).body(new SingleResult<>(200, "성공", map));


        }


        //  다른
        map.put("sessionId", null);
        map.put("token", null);
        return ResponseEntity.status(409).body(new SingleResult<>(409, "매칭할 상담이 없습니다", map));

    }


    // 상담 시작
    @PostMapping("/meeting/start")

    public ResponseEntity<SingleResult<Map<String, Integer>>> meetingStart(@ApiIgnore Authentication authentication, @RequestBody Map<String, String> request) {

        SsafyUserDetails userDetails = (SsafyUserDetails) authentication.getDetails();
        int id = userDetails.getStaffPk();
        String sessionId = request.get("sessionId");


        int meetingLogId = staffService.meetingLogStart(id, sessionId);

        HashMap<String, Integer> map = new HashMap<>();

        map.put("meetingLogId", meetingLogId);

        return ResponseEntity.status(201).body(new SingleResult<>(201, "성공", map));

    }

    // 상담 종료
    @PostMapping("/meeting/end")

    public ResponseEntity<BaseResponseBody> meetingEnd(@ApiIgnore Authentication authentication, @RequestBody Map<String, String> request) {

        SsafyUserDetails userDetails = (SsafyUserDetails) authentication.getDetails();
        int id = userDetails.getStaffPk();
        String sessionId = request.get("sessionId");
        String content = request.get("content");
        String openviduToken = request.get("token");
        int meetingLogId = Integer.parseInt(request.get("meetingLogId"));


        //openvidu 최신화
       openviduService.disconnectSession(sessionId, openviduToken);//정상적으로 처리 완료


         // meetingLog 종료 시간과 content 기록

            staffService.meetingLogEnd(meetingLogId,content,id);

            return ResponseEntity.status(200).body(BaseResponseBody.of(200,"성공"));


        }


}
