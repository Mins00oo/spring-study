package org.mycom.springstudy.user.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TEAM_ID")
    private Long id;

    private String name;

    @Builder
    public Team(String name) {
        this.name = name;
    }
}
