package com.sohwakmo.hr.dto;

import com.sohwakmo.hr.domain.Employee;
import com.sohwakmo.hr.domain.Part;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
public class EmployeeJoinDto {
    private String employeeNo;
    private String password;
    private String name;
    private String phone; // 사내 전화번호
    private String email;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date joinedDate;

    public Employee toEntity(){
        return Employee.builder()
                .employeeNo(employeeNo).password(password).name(name).phone(phone).email(email).build();
    }
}
