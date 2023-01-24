package com.sohwakmo.hr.dto;

import com.sohwakmo.hr.domain.Employee;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@ToString
@Slf4j
public class EmployeeSecurityDto extends User {

    private String username;
    private String password;

    private String name;
    private String employeeNo;
    public EmployeeSecurityDto(String username, String password, String name,String employeeNo, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.username = username;
        this.password = password;
        this.name = name;
        this.employeeNo = employeeNo;

    }

    public static EmployeeSecurityDto fromEntity(Employee e) {
        List<GrantedAuthority> authorities = e.getEmployeePosition().stream()
                .map(x -> new SimpleGrantedAuthority(x.getRole()))
                .collect(Collectors.toList());
        log.info("formEntity = {}", e.getName());
        return new EmployeeSecurityDto(e.getEmployeeNo(),e.getPassword(),e.getName(),e.getEmployeeNo(),authorities);
    }
}
