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
    private String employeeNo;
    private String approverNo;
    private String title;
    private String reason;
    private String category;
    private PaymentState state;
    private String returnReason;
    private String effectiveDate;
    private String expirationDate;
    private String place;
    private String companionNo;


}
