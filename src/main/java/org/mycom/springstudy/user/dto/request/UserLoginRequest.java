package org.mycom.springstudy.user.dto.request;

import lombok.Getter;

@Getter
public class UserLoginRequest {
    private String email;

    private String pwd;
}
