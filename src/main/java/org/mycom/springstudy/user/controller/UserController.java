package org.mycom.springstudy.user.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mycom.springstudy.user.dto.UserTokenDto;
import org.mycom.springstudy.user.dto.request.UserChangePassword;
import org.mycom.springstudy.user.dto.request.UserCreateRequest;
import org.mycom.springstudy.user.dto.request.UserLoginRequest;
import org.mycom.springstudy.user.service.UserService;
import org.springframework.http.ResponseEntity;
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
        return ResponseEntity.ok("생성 완료");
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody UserLoginRequest loginRequest, HttpServletResponse response) {
        UserTokenDto userToken = userService.login(loginRequest, response);
        return ResponseEntity.ok(userToken);
    }

    @PutMapping("/password")
    public ResponseEntity<Object> changePassword(@RequestBody UserChangePassword changePassword) {

        return ResponseEntity.ok("");
    }


    @PutMapping("/password/vs")
    public ResponseEntity<Object> changePassword2(@RequestBody UserChangePassword changePassword) {

        String a = "123";
        String B = "1235555";

        return ResponseEntity.ok("");
    }

}
