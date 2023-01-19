package com.sohwakmo.hr.dto;

import com.sohwakmo.hr.domain.Employee;
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
    private Part part;
    private String position;
    private String email;

    public static OrgReadDto fromEntity(Employee entity){
        return OrgReadDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .phone(entity.getPhone())
                .photo(entity.getPhoto())
                .part(entity.getPart())
                .position(entity.getPart().getPosition())
                .email(entity.getEmail())
                .build();
    }
}
