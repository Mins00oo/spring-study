package org.mycom.springstudy.user.dto;

import lombok.*;
import org.mycom.springstudy.user.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class UserDetailsDto implements UserDetails {
    private Long id;
    private String email;
    private String password;

    @Builder
    public UserDetailsDto(Long id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public static UserDetailsDto toEntity(User user) {
        return UserDetailsDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPwd())
                .build();
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
