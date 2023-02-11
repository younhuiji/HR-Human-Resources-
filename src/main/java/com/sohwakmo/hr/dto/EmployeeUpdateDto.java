package com.sohwakmo.hr.dto;

import com.sohwakmo.hr.domain.Employee;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class EmployeeUpdateDto {
    private String name;
    private String phone;

    public Employee toEntity(){
        return Employee.builder().
                name(name).phone(phone).build();
    }
}
