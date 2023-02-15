package com.sohwakmo.hr.dto;

import com.sohwakmo.hr.domain.BusinessTrip;
import com.sohwakmo.hr.domain.Leave;
import com.sohwakmo.hr.domain.BusinessCard;
import com.sohwakmo.hr.domain.Vacation;
import lombok.Data;

@Data
public class updateDto {

    private Integer no ;
    private String returnReason;

    public Leave toEntityLeave() {
        return Leave.builder().no(no).returnReason(returnReason).build();
    }

    public BusinessCard toEntityBusinessCard() {
        return BusinessCard.builder().no(no).returnReason(returnReason).build();
    }

    public BusinessTrip toEntityBusinessTrip() {
        return BusinessTrip.builder().no(no).returnReason(returnReason).build();
    }

    public Vacation toEntityVacation() {
        return Vacation.builder().no(no).returnReason(returnReason).build();
    }

}
