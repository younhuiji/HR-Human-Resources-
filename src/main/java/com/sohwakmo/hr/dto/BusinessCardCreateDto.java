package com.sohwakmo.hr.dto;

import com.sohwakmo.hr.domain.BusinessCard;
import com.sohwakmo.hr.domain.PaymentState;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class BusinessCardCreateDto {

    private Integer no;
    private Long employeeNo;
    private String employeeName;
    private Long approverNo;
    private String title;
    private String reason;
    private String category;
    private PaymentState state;
    private String returnReason;
    private String writeDate;
    private String email;
    private String phone;

    public BusinessCard toEntity() {
        return BusinessCard.builder()
                .no(no)
                .employeeNo(employeeNo)
                .employeeName(employeeName)
                .approverNo(approverNo)
                .title(title)
                .reason(reason)
                .category(category)
                .state(state)
                .returnReason(returnReason)
                .writeDate(writeDate)
                .email(email)
                .phone(phone)
                .build();
    }


}
