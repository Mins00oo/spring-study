package org.mycom.springstudy.common.utils.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mycom.springstudy.common.ErrorCode;
import org.mycom.springstudy.common.exception.BaseException;
import org.mycom.springstudy.common.utils.CustomUserDetailService;
import org.mycom.springstudy.common.utils.UserDetailsImpl;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthenticationProviderImpl implements AuthenticationProvider {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final CustomUserDetailService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;

        String username = token.getName();
        String password = String.valueOf(token.getCredentials());

        UserDetailsImpl userDetails;

        userDetails = (UserDetailsImpl) userDetailsService.loadUserByUsername(username);

        if (!bCryptPasswordEncoder.matches(password, userDetails.getPassword())) {
            throw new BaseException(ErrorCode.NOT_VALID_PASSWORD);
        }

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
