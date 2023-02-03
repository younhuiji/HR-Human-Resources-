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

    private Integer no;
    private Long employeeNo;
    private String employeeName;
    private Long approverNo;
    private String title;
    private String reason;
    private String category;
    private PaymentState state;
    private String returnReason;
    private LocalDateTime writeDate;
    private String effectiveDate;
    private String expirationDate;
    private LocalDateTime competeDate;

//    public Vacation toEntity() {
//        return Vacation.builder()
//                .no(no)
//                .employeeNo(employeeNo)
//                .employeeName(employeeName)
//                .approverNo(approverNo)
//                .title(title)
//                .reason(reason)
//                .category(category)
//                .state(state)
//                .returnReason(returnReason)
//                .writeDate(writeDate)
//                .writeDate(writeDate)
//                .effectiveDate(effectiveDate)
//                .expirationDate(expirationDate)
//                .competeDate(competeDate)
//                .build();
//    }


}
