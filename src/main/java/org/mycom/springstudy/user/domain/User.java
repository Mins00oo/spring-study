package org.mycom.springstudy.user.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.mycom.springstudy.common.config.BaseTimeEntity;

@Entity
@Getter
@NoArgsConstructor
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    private String pwd;

    @Builder
    public User(String email, String pwd) {
        this.email = email;
        this.pwd = pwd;
    }

    public void changePassword(String encodedPassword) {
        this.pwd = encodedPassword;
    }
}
