package com.sohwakmo.hr.dto;

import com.sohwakmo.hr.domain.Leave;
import lombok.Data;

@Data
public class LeaveUpdateDto {

    private Integer leaveNo ;
    private String returnReason;

    public Leave toEntity() {
        return Leave.builder().no(leaveNo).returnReason(returnReason).build();
    }


}
