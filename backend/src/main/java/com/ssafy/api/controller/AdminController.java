package com.ssafy.api.controller;


import com.ssafy.api.request.UserLoginPostReq;
import com.ssafy.api.response.UserLoginPostRes;
import com.ssafy.api.service.AdminService;
import com.ssafy.api.service.UserService;
import com.ssafy.common.model.response.BaseResponseBody;
import com.ssafy.common.util.JwtTokenUtil;
import com.ssafy.db.entity.Staff;
import com.ssafy.db.entity.User;
import com.ssafy.db.repository.AdminRepositorySupport;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @Autowired
    UserService userService;

//    @Autowired
//    PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    @ApiOperation(value = "로그인", notes = "<strong>아이디와 패스워드</strong>를 통해 로그인 한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
            @ApiResponse(code = 401, message = "인증 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 404, message = "사용자 없음", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<UserLoginPostRes> login(@RequestBody @ApiParam(value="로그인 정보", required = true) UserLoginPostReq loginInfo) {
        String userId = loginInfo.getId();
        String password = loginInfo.getPassword();
        Staff admin=adminService.getStaffById(userId);
        if(admin==null) {

            return ResponseEntity.ok(UserLoginPostRes.of(404, "존재하지 않는 계정입니다", null));

        }
       // if(passwordEncoder.matches(password, admin.getPassword()))
        // 로그인 요청한 유저로부터 입력된 패스워드 와 디비에 저장된 유저의 암호화된 패스워드가 같은지 확인.(유효한 패스워드인지 여부 확인)
        if(password.equals(admin.getPassword())) {


            // 로그인도 성공하고 admin 계정이 맞는경우
            if(admin.getAdminYN().equals("Y"))
            {
                // 유효한 패스워드가 맞는 경우, 로그인 성공으로 응답.(액세스 토큰을 포함하여 응답값 전달)
                return ResponseEntity.ok(UserLoginPostRes.of(200, "로그인 성공", JwtTokenUtil.getToken(userId)));
            }
            else {
                return ResponseEntity.ok(UserLoginPostRes.of(403, "권한이 없습니다", null));
            }
        }
        // 유효하지 않는 패스워드인 경우, 로그인 실패로 응답.
        return ResponseEntity.status(401).body(UserLoginPostRes.of(401, "잘못된 비밀번호입니다", null));
    }
}




