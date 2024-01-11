package org.mycom.springstudy.common.config;

import lombok.RequiredArgsConstructor;
import org.mycom.springstudy.user.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@EnableMethodSecurity
@Configuration
public class AuthenticationConfig {

    private final UserService userService;

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .cors(httpSecurityCorsConfigurer -> {

                })
                .csrf(AbstractHttpConfigurer::disable
                )
                .authorizeHttpRequests((authorizeRequests) ->
                        authorizeRequests
                                .requestMatchers("/**").permitAll()
                                .anyRequest().authenticated()
                )
                .sessionManagement(
                        (sessionManagement) ->
                                sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .addFilterBefore(
//                        new JwtTokenFilter(userService),
//                        UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
