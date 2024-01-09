package org.mycom.springstudy.user.service;

import jakarta.servlet.http.HttpServletResponse;
import org.mycom.springstudy.user.dto.UserTokenDto;
import org.mycom.springstudy.user.dto.request.UserCreateRequest;
import org.mycom.springstudy.user.dto.request.UserLoginRequest;

public interface UserService {
    void createUser(UserCreateRequest request);

    UserTokenDto login(UserLoginRequest loginRequest, HttpServletResponse response);
}
