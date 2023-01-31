package com.sohwakmo.hr.dto;

import lombok.Data;

@Data
public class AttendanceDto {
    private String month;
    private String date;
    private Integer hours;
    private Integer minutes;
    private String employeeNo;

}
