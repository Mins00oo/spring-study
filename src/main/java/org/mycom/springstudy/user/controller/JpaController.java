package org.mycom.springstudy.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mycom.springstudy.user.domain.Team;
import org.mycom.springstudy.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class JpaController {

    private final UserService userService;

    @GetMapping("/test/{memberId}")
    public ResponseEntity<Object> teamByUserId(@PathVariable Long memberId) {
        Team team = userService.teamByMemberId(memberId);

        return ResponseEntity.ok(team);
    }
}
