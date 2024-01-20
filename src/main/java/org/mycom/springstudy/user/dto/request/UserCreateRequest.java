package org.mycom.springstudy.user.dto.request;

import lombok.Getter;
import org.mycom.springstudy.common.config.Role;
import org.mycom.springstudy.user.domain.User;
import org.springframework.boot.autoconfigure.web.format.DateTimeFormatters;

import java.time.LocalDateTime;

@Getter
public class UserCreateRequest {
    private String email;

    private String pwd;

    private String date;


    public static User toEntity(
            UserCreateRequest request,
            String encodedPassword,
            Role role,
            LocalDateTime date
            ) {
        return User.builder()
                .email(request.getEmail())
                .pwd(encodedPassword)
                .role(role)
                .date(date)
                .build();
    }
}
