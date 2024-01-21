package org.mycom.springstudy.user.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.mycom.springstudy.user.dto.UserTokenDto;

import java.util.List;

@Getter
@Setter
public class CompanyResponseDto {
    private String name;

    private String where;

    private Long id;

    List<UserTokenDto> tokens;

    @Builder
    public CompanyResponseDto(String name, String where, Long id, List<UserTokenDto> tokens) {
        this.name = name;
        this.where = where;
        this.id = id;
        this.tokens = tokens;
    }
}
