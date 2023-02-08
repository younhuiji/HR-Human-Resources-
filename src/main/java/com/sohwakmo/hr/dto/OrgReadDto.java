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
    private String name;
    private String phone;
    private String photo;
    private String department;
    private String team;
    private String work;
    private String position;
    private EmployeePosition employeePosition;
    private String email;


    public static OrgReadDto fromEntity(Employee entity){
        return OrgReadDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .phone(entity.getPhone())
                .photo(entity.getPhoto())
                .department(entity.getPart().getDepartment())
                .team(entity.getPart().getTeam())
                .work(entity.getPart().getWork())
                .position(entity.getPosition())
//                .employeePosition(entity.getEmployeePosition())
                .email(entity.getEmail())
                .build();
    }
}
