package com.sohwakmo.hr.dto;

import lombok.Data;

@Data
public class AttendanceDto {
    private Integer month;
    private Integer day;
    private Integer hours;
    private Integer minutes;
    private String employeeNo;

}
