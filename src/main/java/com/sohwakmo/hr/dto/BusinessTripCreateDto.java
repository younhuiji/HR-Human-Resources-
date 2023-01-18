package com.sohwakmo.hr.dto;

import com.sohwakmo.hr.domain.BusinessCard;
import com.sohwakmo.hr.domain.BusinessTrip;
import com.sohwakmo.hr.domain.PaymentState;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class BusinessTripCreateDto {

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
    private String place;
    private Long companionNo;


    public BusinessTrip toEntity() {
        return BusinessTrip.builder()
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
                .effectiveDate(effectiveDate)
                .expirationDate(expirationDate)
                .competeDate(competeDate)
                .place(place)
                .companionNO(companionNo)
                .build();
    }


}
