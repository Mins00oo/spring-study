package org.mycom.springstudy.common.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    USER("사용자"),
    CLEAN("청소업체"),
    DRIVE("용달업체");

    private final String role;
}
