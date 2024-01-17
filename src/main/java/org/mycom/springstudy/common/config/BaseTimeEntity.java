package org.mycom.springstudy.common.config;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createDt;

    @LastModifiedDate
    private LocalDateTime updateDt;

    private LocalDateTime deleteDt;

    public LocalDateTime getCreateAt() {
        return createDt;
    }

    public LocalDateTime getUpdateAt() {
        return updateDt;
    }

    public LocalDateTime getDeleteAt() {
        return deleteDt;
    }
}
