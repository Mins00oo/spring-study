package org.mycom.springstudy.user.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mycom.springstudy.common.BaseResponse;
import org.mycom.springstudy.common.utils.JwtTokenProvider;
import org.mycom.springstudy.common.utils.UserDetailsImpl;
import org.mycom.springstudy.user.dto.UserTokenDto;
import org.mycom.springstudy.user.dto.request.UserChangePassword;
import org.mycom.springstudy.user.dto.request.UserCreateRequest;
import org.mycom.springstudy.user.dto.request.UserLoginRequest;
import org.mycom.springstudy.user.dto.response.CompanyResponseDto;
import org.mycom.springstudy.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping
    public ResponseEntity<Object> createUser(@RequestBody UserCreateRequest request) {
        userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(new BaseResponse<>("회원이 생성되었습니다."));
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody UserLoginRequest loginRequest, HttpServletResponse response) {
        UserTokenDto userToken = userService.login(loginRequest, response);
        return ResponseEntity.status(HttpStatus.OK).body(new BaseResponse<>(userToken));
    }

    @PutMapping("/password")
    public ResponseEntity<Object> changePassword(@RequestBody UserChangePassword changePassword,
                                                 @AuthenticationPrincipal UserDetailsImpl user) {

        Long userId = jwtTokenProvider.getMemberId("asdsa");
        System.out.println("user = " + user);
        return ResponseEntity.status(HttpStatus.OK).body("비밀번호 변경되었습니다.");
    }

    @GetMapping("/dto")
    public ResponseEntity<Object> retrieveUserToken() {
        ArrayList<UserTokenDto> userTokenDtos = new ArrayList<>();
        UserTokenDto userTokenDto = new UserTokenDto("Bearer saaa");
        userTokenDtos.add(userTokenDto);

        CompanyResponseDto response = CompanyResponseDto.builder()
                .id(1L)
                .name("dd")
                .where("hi")
                .tokens(userTokenDtos)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(new BaseResponse<>(response));
    }

}
