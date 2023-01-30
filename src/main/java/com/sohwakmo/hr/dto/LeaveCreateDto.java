package com.sohwakmo.hr.dto;

import com.sohwakmo.hr.domain.BusinessCard;
import com.sohwakmo.hr.domain.Leave;
import com.sohwakmo.hr.domain.PaymentState;
import lombok.Builder;
import lombok.Data;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Builder
@Data
public class LeaveCreateDto {

    private Integer no; // PK
    private Long employeeNo; // 시행자 사번
    private Long approverNo; // 승인자 사번
    private Long secondApproverNO; // 승인자2 사번
    private String title; // 제목
    private String reason; // 사유
    private String category; // 결재 분류
    private PaymentState state; // 결재 상태
    private String returnReason; // 반려 사유
    private String effectiveDate; // 시행 일자
    private LocalDateTime competeDate; // 결재 일시

    public Leave toEntity() {
        return Leave.builder()
                .no(no)
                .employeeNo(employeeNo)
                .approverNo(approverNo)
                .secondApproverNO(secondApproverNO)
                .title(title)
                .reason(reason)
                .category(category)
                .state(state)
                .returnReason(returnReason)
                .effectiveDate(effectiveDate)
                .competeDate(competeDate)
                .build();
    }


}
