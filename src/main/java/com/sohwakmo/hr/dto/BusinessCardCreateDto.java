package com.sohwakmo.hr.dto;

import com.sohwakmo.hr.domain.BusinessCard;
import com.sohwakmo.hr.domain.PaymentState;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Builder
@Data
public class BusinessCardCreateDto {

    private Integer no;
    private Long employeeNo;
    private Long approverNo;
    private String title;
    private String reason;
    private String category;
    private PaymentState state;
    private String returnReason;
    private String createdTime;


    public static String formatDate(LocalDateTime time){
        String formatDate = time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        return formatDate;
    }

//    public BusinessCard toEntity() {
//        return BusinessCard.builder()
//                .no(no)
//                .employeeNo(employeeNo)
//                .approverNo(approverNo)
//                .title(title)
//                .reason(reason)
//                .category(category)
//                .returnReason(returnReason)
//                .createdTime(formatDate(entity.getCreatedTime()))
//                .build();
//    }


}
