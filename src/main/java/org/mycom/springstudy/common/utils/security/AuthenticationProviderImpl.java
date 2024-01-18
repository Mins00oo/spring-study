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

        log.info(String.valueOf(authentication));
        log.info(String.valueOf(token));

        String username = token.getName();
        String password = String.valueOf(token.getCredentials());

        log.info("username:" + username);
        log.info("password:" + password);
        log.info("creden: " + token.getCredentials());

        UserDetailsImpl userDetails;

        User user = userRepository.findByEmail(password)
                .orElseThrow(() -> new BadCredentialsException("이메일 틀림"));
        log.info("username:" + username);
        userDetails = (UserDetailsImpl) userDetailsService.loadUserByUsername(username);


        log.info("ddd");
        if (!bCryptPasswordEncoder.matches(password, user.getPwd())) {
            throw new BadCredentialsException("비번 틀림");
        }
        log.info("zdzd");
        return new UsernamePasswordAuthenticationToken(userDetails.getUsername(), "", userDetails.getAuthorities());

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
