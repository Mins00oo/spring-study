package org.mycom.springstudy.common.utils.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.mycom.springstudy.common.config.BaseResponse;
import org.mycom.springstudy.common.utils.JwtTokenProvider;
import org.mycom.springstudy.user.domain.User;
import org.mycom.springstudy.user.repository.UserRepository;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Log4j2
public class AuthSuccessHandler implements AuthenticationSuccessHandler {
    private final JwtTokenProvider jwtTokenProvider;
    private final ObjectMapper mapper;
    private final UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        SecurityContextHolder.getContext().setAuthentication(authentication);
        log.info("000000");
        User user = userRepository.findByEmail(String.valueOf(authentication.getPrincipal()))
                .orElseThrow(() -> new BadCredentialsException("회원 x"));

        String token = jwtTokenProvider.createAccessToken(user.getEmail(), user.getRole());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");

        JwtTokenProvider.setAccessTokenInHeader(token, response);

        Map<String, String> tokenDto = new HashMap<>();

        tokenDto.put("accessToken", token);

        BaseResponse<Map<String, String>> baseResponse = new BaseResponse<>(tokenDto);

        mapper.writeValue(response.getWriter(), baseResponse);
    }
}
