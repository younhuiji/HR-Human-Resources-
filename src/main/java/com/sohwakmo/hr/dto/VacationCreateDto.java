package com.sohwakmo.hr.dto;

import com.sohwakmo.hr.domain.BusinessCard;
import com.sohwakmo.hr.domain.PaymentState;
import com.sohwakmo.hr.domain.Vacation;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class VacationCreateDto {

    private Integer no; // PK
    private String employeeNo; // 시행자 사번
    private String approverNo; // 승인자 사번
    private String title; // 제목
    private String reason; // 사유
    private String category; // 결재 분류
    private PaymentState state; // 결재 상태
    private String effectiveDate; // 시행 일자
    private String expirationDate; // 종료 일자
    private String createdTime; // 작성 일시


}
