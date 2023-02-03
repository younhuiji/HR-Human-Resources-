package com.sohwakmo.hr.dto;

import com.sohwakmo.hr.domain.Vacation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Builder
@Getter
@ToString
public class VacationListReadDto {
    private Integer vacationNo;
    private Long employeeNo;
    private String title;
    private String reason;
    private String start;
    private String end;

    public static VacationListReadDto fromEntity(Vacation entity){
        return VacationListReadDto.builder()
                .vacationNo(entity.getNo())
                .employeeNo(entity.getEmployeeNo())
                .title(entity.getTitle())
                .reason(entity.getReason())
                .start(entity.getEffectiveDate())
                .end(entity.getExpirationDate())
                .build();
    }
}
