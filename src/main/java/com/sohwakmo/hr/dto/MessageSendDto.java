package com.sohwakmo.hr.dto;

import com.sohwakmo.hr.domain.Message;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Builder
@Data
public class MessageSendDto {
    private Integer senderNo;
    private String messageType;
    private String title;
    private Integer receiveNo;
    private String content;

    public Message toEntity() {
        return Message.builder()
                .senderNo(senderNo)
                .messageType(messageType)
                .title(title)
                .receiveNo(receiveNo)
                .content(content)
                .build();
    }

}
