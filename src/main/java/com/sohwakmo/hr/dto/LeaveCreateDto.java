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
    private String secondApproverNO; // 승인자2 사번
    private String title; // 제목
    private String reason; // 사유
    private String category; // 결재 분류
    private PaymentState state; // 결재 상태
    private String returnReason; // 반려 사유
    private String effectiveDate; // 시행 일자
    private LocalDateTime competeDate; // 결재 일시
    private String createdTime; // 작성 일시

    public static LeaveCreateDto fromEntity(Leave entity){
        LeaveCreateDto build = LeaveCreateDto.builder()
                .no(entity.getNo())
                .employeeNo(entity.getEmployeeNo())
                .approverNo(entity.getApproverNo())
                .secondApproverNO(entity.getApproverNo())
                .title(entity.getTitle())
                .reason(entity.getReason())
                .category(entity.getCategory())
                .returnReason(entity.getReturnReason())
                .effectiveDate(entity.getEffectiveDate())
                .competeDate(entity.getCompeteDate())
                .createdTime(formatDate(entity.getCreatedTime()))
                .build();
        return build;
    }

    public static String formatDate(LocalDateTime time){
        String formatDate = time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        return formatDate;
    }



}
