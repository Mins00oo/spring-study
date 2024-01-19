package org.mycom.springstudy.common.utils.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mycom.springstudy.common.utils.CustomUserDetailService;
import org.mycom.springstudy.common.utils.UserDetailsImpl;
import org.mycom.springstudy.user.domain.User;
import org.mycom.springstudy.user.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthenticationProviderImpl implements AuthenticationProvider {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final CustomUserDetailService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;

        String username = token.getName();
        String password = String.valueOf(token.getCredentials());

        UserDetailsImpl userDetails;

        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new BadCredentialsException("이메일 틀림"));
        userDetails = (UserDetailsImpl) userDetailsService.loadUserByUsername(username);


        if (!bCryptPasswordEncoder.matches(password, user.getPwd())) {
            throw new BadCredentialsException("비번 틀림");
        }

        return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), "", userDetails.getAuthorities());

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
