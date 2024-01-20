package org.mycom.springstudy.user.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mycom.springstudy.common.config.BaseResponse;
import org.mycom.springstudy.common.utils.UserDetailsImpl;
import org.mycom.springstudy.user.domain.Team;
import org.mycom.springstudy.user.dto.UserDetailsDto;
import org.mycom.springstudy.user.dto.UserTokenDto;
import org.mycom.springstudy.user.dto.request.UserChangePassword;
import org.mycom.springstudy.user.dto.request.UserCreateRequest;
import org.mycom.springstudy.user.dto.request.UserLoginRequest;
import org.mycom.springstudy.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody UserCreateRequest request) {
        userService.createUser(request);
        String result = "생성이 완료되었습니다.";
        return ResponseEntity.status(HttpStatus.CREATED).body(new BaseResponse<>(result));
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody UserLoginRequest loginRequest, HttpServletResponse response) {
        UserTokenDto userToken = userService.login(loginRequest, response);
        return ResponseEntity.status(HttpStatus.OK).body(new BaseResponse<>(userToken));
    }

    @PutMapping("/password")
    public ResponseEntity<Object> changePassword(@RequestBody UserChangePassword changePassword,
                                                 @AuthenticationPrincipal UserDetailsImpl user) {

        System.out.println("user = " + user);
        return ResponseEntity.status(HttpStatus.OK).body("dd");
    }

}
