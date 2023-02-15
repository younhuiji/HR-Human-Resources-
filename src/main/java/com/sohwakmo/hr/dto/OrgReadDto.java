package com.sohwakmo.hr.dto;

import com.sohwakmo.hr.domain.Employee;
import com.sohwakmo.hr.domain.EmployeePosition;
import com.sohwakmo.hr.domain.Part;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Builder
@Getter
@ToString
public class OrgReadDto {

    private Long id;
    private String employeeNo;
    private String name;
    private String phone;
    private String photo;
    private String department;
    private String team;
    private String work;
    private String position;
    private String email;


    public static OrgReadDto fromEntity(Employee entity){
        return OrgReadDto.builder()
                .id(entity.getId())
                .employeeNo(entity.getEmployeeNo())
                .name(entity.getName())
                .phone(entity.getPhone())
                .photo(entity.getPhoto())
                .department(entity.getPart().getDepartment())
                .team(entity.getPart().getTeam())
                .work(entity.getPart().getWork())
                .position(entity.getPosition())
                .email(entity.getEmail())
                .employeeNo(entity.getEmployeeNo())
                .build();
    }
}
