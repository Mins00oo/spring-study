package org.mycom.springstudy.user.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class UserTokenDto {
    private String accessToken;

    @Builder
    public UserTokenDto(String accessToken) {
        this.accessToken = Objects.requireNonNull(accessToken);
    }
}
