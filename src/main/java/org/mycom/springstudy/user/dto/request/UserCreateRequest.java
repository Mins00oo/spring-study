package org.mycom.springstudy.user.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.mycom.springstudy.user.domain.User;

@Getter
public class UserCreateRequest {
    private String email;

    private String pwd;

    public static User toEntity(
            UserCreateRequest request,
            String encodedPassword) {
        return User.builder()
                .email(request.getEmail())
                .pwd(encodedPassword)
                .build();
    }
}
