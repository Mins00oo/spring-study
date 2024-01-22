package org.mycom.springstudy.user.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Comment;
import org.mycom.springstudy.common.config.BaseTimeEntity;
import org.mycom.springstudy.common.config.Role;

import java.time.LocalDateTime;

@Entity
@Getter
@ToString
@NoArgsConstructor
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String pwd;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Comment("프로필 사진 url")
    private String profileImgPath;

    @Comment("사진의 원본 이름")
    private String originName;

    @Comment("사진 저장 이름")
    private String saveName;

    private LocalDateTime date;

    @Builder
    public User(String email, String pwd, Role role) {
        this.email = email;
        this.pwd = pwd;
        this.role = role;
    }

    public void changePassword(String encodedPassword) {
        this.pwd = encodedPassword;
    }

}
