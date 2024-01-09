package org.mycom.springstudy.common.utils.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mycom.springstudy.common.exception.GlobalException;
import org.mycom.springstudy.common.utils.JwtTokenProvider;
import org.mycom.springstudy.user.dto.UserDetailsDto;
import org.mycom.springstudy.user.service.UserService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private final UserService userService;

    private final String AUTHORIZATION_HEADER = "Authorization";

    private static final List<String> PERMIT_URLS =
            List.of("/", "/h2", "/api/v1/users", "/api/v1/users/login");

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            final String header = request.getHeader(AUTHORIZATION_HEADER);
            if (header == null || !header.startsWith("Bearer ")) {
                log.error("Authorization Header does not start with Bearer {}", request.getRequestURI());
                throw new BadCredentialsException("Invalid Token");
            }

            final String token = header.split(" ")[1].trim();
            if (JwtTokenProvider.isExpired(token)) {
                throw new BadCredentialsException("Expired Access Token");
            }

            String email = JwtTokenProvider.getUserEmail(token);
            UserDetailsDto user = userService.loadUserByEmail(email);

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            log.info("Token verification successful. URI: {}", request.getRequestURI());

        } catch (RuntimeException e) {
            if (e instanceof GlobalException) {
                ObjectMapper objectMapper = new ObjectMapper();
                // response로 Exception Handling 필수!
            }
        }
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return PERMIT_URLS.stream()
                .anyMatch(exclude -> exclude.equalsIgnoreCase(request.getServletPath()));
    }

}
