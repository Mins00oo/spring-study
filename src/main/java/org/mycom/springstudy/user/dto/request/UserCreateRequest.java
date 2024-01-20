package org.mycom.springstudy.user.dto.request;

import lombok.Getter;
import org.mycom.springstudy.common.config.Role;
import org.mycom.springstudy.user.domain.User;

@Getter
public class UserCreateRequest {
    private String email;

    private String pwd;

    public static User toEntity(
            UserCreateRequest request,
            String encodedPassword,
            Role role) {
        return User.builder()
                .email(request.getEmail())
                .pwd(encodedPassword)
                .role(role)
                .build();
    }
}
