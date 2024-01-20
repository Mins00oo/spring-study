package org.mycom.springstudy.common.utils.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mycom.springstudy.user.dto.request.UserLoginRequest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        final UsernamePasswordAuthenticationToken authRequest;
        final UserLoginRequest userLoginDto;
        try {
            // 사용자 요청 정보로 UserPasswordAuthenticationToken 발급
            userLoginDto = new ObjectMapper().readValue(request.getInputStream(), UserLoginRequest.class);
            authRequest = new UsernamePasswordAuthenticationToken(userLoginDto.getEmail(), userLoginDto.getPwd());
        } catch (IOException e) {
            throw new BadCredentialsException("bad");
        }

        Authentication authentication = this.getAuthenticationManager().authenticate(authRequest);

        setDetails(request, authRequest);
        return authentication;
    }


    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        SecurityContextHolder.getContext().setAuthentication(authResult);

        chain.doFilter(request, response);

    }
}
