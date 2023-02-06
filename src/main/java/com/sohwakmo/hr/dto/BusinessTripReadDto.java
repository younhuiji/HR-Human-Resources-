package com.sohwakmo.hr.dto;

import com.sohwakmo.hr.domain.BusinessTrip;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Builder
@Getter
@ToString
public class BusinessTripReadDto {
    private Integer businessNo;
    private String employeeNo;
    private String title;
    private String reason;
    private String start;
    private String end;
    private String place;
    private String companionNo;

    public static BusinessTripReadDto fromEntity(BusinessTrip entity){
        return BusinessTripReadDto.builder()
                .businessNo(entity.getNo())
                .employeeNo(entity.getEmployeeNo())
                .title(entity.getTitle())
                .reason(entity.getReason())
                .start(entity.getEffectiveDate())
                .end(entity.getExpirationDate())
                .place(entity.getPlace())
                .companionNo(entity.getCompanionNO())
                .build();
    }

}
