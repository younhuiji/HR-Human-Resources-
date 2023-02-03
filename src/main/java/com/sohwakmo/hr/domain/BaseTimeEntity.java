package com.sohwakmo.hr.domain;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@EntityListeners(value = {AuditingEntityListener.class})

public class BaseTimeEntity {

    @CreatedDate
    private LocalDateTime sendTime;
    private LocalDateTime readTime;

}
