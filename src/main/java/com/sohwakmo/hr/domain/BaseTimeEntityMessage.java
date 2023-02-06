package com.sohwakmo.hr.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(value= {AuditingEntityListener.class})
public class BaseTimeEntityMessage {

    @CreatedDate
    private LocalDateTime sendTime;
    private LocalDateTime readTime;

}
