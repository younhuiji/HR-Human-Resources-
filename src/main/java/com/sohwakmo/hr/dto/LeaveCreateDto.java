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

    private Integer no;
    private Long employeeNo;
    private Long approverNo;
    private Long secondApproverNO;
    private String title;
    private String reason;
    private String category;
    private PaymentState state;
    private String returnReason;
    private LocalDateTime writeDate;
    private String effectiveDate;
    private LocalDateTime competeDate;

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
                .writeDate(writeDate)
                .effectiveDate(effectiveDate)
                .competeDate(competeDate)
                .build();
    }


}
