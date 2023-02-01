package com.sohwakmo.hr.dto;

import com.sohwakmo.hr.domain.Employee;
import com.sohwakmo.hr.domain.Message;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

@Builder
@Data
public class MessageSearchDto {
    private Integer messageNo;
    private String messageType;
    private String title;
    private String content;
    private String filePath1;
    private String fileName1;
    private String filePath2;
    private String fileName2;
    private String filePath3;
    private String fileName3;
    private Integer receiveNo;
    private Integer receiveReadCheck;
    private Integer receiveTrash;
    private Integer receiveDelete;
    private Integer senderNo;
    private Integer senderTrash;
    private Integer senderDelete;
    private Employee employee;
    private String name;

    public Message toEntity() {
        return Message.builder()
                .messageNo(messageNo)
                .messageType(messageType)
                .title(title)
                .content(content)
                .filePath1(filePath1)
                .fileName1(fileName1)
                .filePath2(filePath2)
                .fileName2(fileName2)
                .filePath3(fileName3)
                .receiveNo(receiveNo)
                .receiveReadCheck(receiveReadCheck)
                .receiveTrash(receiveTrash)
                .receiveDelete(receiveDelete)
                .senderNo(senderNo)
                .senderTrash(senderTrash)
                .senderDelete(senderDelete)
                .build();
    }

}
