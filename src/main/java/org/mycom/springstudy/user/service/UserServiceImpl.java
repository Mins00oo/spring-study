package org.mycom.springstudy.user.service;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mycom.springstudy.common.utils.JwtTokenProvider;
import org.mycom.springstudy.user.domain.User;
import org.mycom.springstudy.user.dto.UserTokenDto;
import org.mycom.springstudy.user.dto.request.UserCreateRequest;
import org.mycom.springstudy.user.dto.request.UserLoginRequest;
import org.mycom.springstudy.user.repository.UserRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public void createUser(UserCreateRequest request) {
        // 기존에 가입되었는지 검증 필요 => 예외처리

        User user = UserCreateRequest.toEntity(
                request,
                encoder.encode(request.getPwd())
        );
        userRepository.save(user);
    }

    @Override
    public UserTokenDto login(UserLoginRequest loginRequest, HttpServletResponse response) {
        User savedUser = userRepository
                .findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new BadCredentialsException("이메일 틀렸음"));

        checkPassword(loginRequest.getPwd(), savedUser.getPwd());

        // jwt 발급
        UserTokenDto token = generateToken(loginRequest.getEmail());

        jwtTokenProvider.setAccessTokenInHeader(token.getAccessToken(), response);
        // 추후에 refreshToken도 함께 발급

        return token;
    }

    private void checkPassword(String request, String password) {
        if (!encoder.matches(request, password)) {
            throw new BadCredentialsException("비번 틀렸음");
        }
    }

    private UserTokenDto generateToken(String requestEmail) {
        UserTokenDto tokenDto =
                UserTokenDto.builder()
                        .accessToken(jwtTokenProvider.createAccessToken(requestEmail))
                        .build();

        return new UserTokenDto(tokenDto.getAccessToken());
    }


}
