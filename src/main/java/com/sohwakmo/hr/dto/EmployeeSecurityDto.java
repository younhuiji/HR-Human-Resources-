package com.sohwakmo.hr.dto;

import com.sohwakmo.hr.domain.Employee;
import com.sohwakmo.hr.domain.Part;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
@Slf4j
public class EmployeeSecurityDto extends User {
    private String username;
    private String password;
    private String name;
    private String employeeNo;
    private String joinedDate;
    private Part part;
    private String photo;
    private String email;
    private String phone;
    private String position;
    public EmployeeSecurityDto(String username, String password, String name,String employeeNo,String joinedDate,Part part,String photo,String email,String phone,String position,Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.username = username;
        this.password = password;
        this.name = name;
        this.employeeNo = employeeNo;
        this.joinedDate = joinedDate;
        this.part = part;
        this.photo = photo;
        this.email = email;
        this.phone = phone;
        this.position = position;
    }

    public static EmployeeSecurityDto fromEntity(Employee e) {
        List<GrantedAuthority> authorities = e.getEmployeePosition().stream()
                .map(x -> new SimpleGrantedAuthority(x.getRole()))
                .collect(Collectors.toList());
        return new EmployeeSecurityDto(e.getEmployeeNo(),e.getPassword(),e.getName(),e.getEmployeeNo(),e.getJoinedDate(),e.getPart(),e.getPhoto(),e.getEmail(),e.getPhone(),e.getPosition(),authorities);
    }
}
