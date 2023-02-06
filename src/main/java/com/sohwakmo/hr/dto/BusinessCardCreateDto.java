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
    private String employeeNo;
    private String approverNo;
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



}
