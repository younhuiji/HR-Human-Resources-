package com.sohwakmo.hr.dto;

import com.sohwakmo.hr.domain.Employee;
import com.sohwakmo.hr.domain.Message;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageSearchDto {
    private Integer messageNo;
    private String messageType;
    private Integer receiveReadCheck;
    private String title;
    private LocalDateTime sendTime;
    private Integer senderNo;
    private Employee employee;
}
