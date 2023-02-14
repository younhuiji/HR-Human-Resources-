package com.sohwakmo.hr.dto;

import com.sohwakmo.hr.domain.BusinessCard;
import com.sohwakmo.hr.domain.Leave;
import com.sohwakmo.hr.domain.PaymentState;
import lombok.Builder;
import lombok.Data;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Builder
@Data
public class LeaveCreateDto {

    private Integer no; // PK
    private String employeeNo; // 시행자 사번
    private String approverNo; // 승인자 사번
    private String secondApproverNo; // 승인자2 사번
    private String title; // 제목
    private String reason; // 사유
    private String category; // 결재 분류
    private PaymentState state; // 결재 상태
    private String effectiveDate; // 시행 일자
    private String createdTime; // 작성 일시


    public static String formatDate(LocalDateTime time){
        String formatDate = time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        return formatDate;
    }



}
