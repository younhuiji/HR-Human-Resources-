package com.sohwakmo.hr.dto;

import com.sohwakmo.hr.domain.Employee;
import com.sohwakmo.hr.domain.Message;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MessageSendDto {
    private Integer senderNo;
    private String messageType;
    private String title;
    private Integer receiveNo;
    private String content;
    private Employee employee;

    public Message toEntity() {
        return Message.builder()
                .senderNo(senderNo)
                .messageType(messageType)
                .title(title)
                .receiveNo(receiveNo)
                .content(content)
                .employee(employee)
                .build();
    }

}
