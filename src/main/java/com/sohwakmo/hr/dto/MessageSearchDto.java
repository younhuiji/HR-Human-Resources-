package com.sohwakmo.hr.dto;

import com.sohwakmo.hr.domain.Employee;
import lombok.*;

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
    private String senderNo;
    private Employee senderEmployeeNo;
    private Employee receiveEmployeeNo;

}
