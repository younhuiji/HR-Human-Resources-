package com.sohwakmo.hr.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
@SequenceGenerator(name = "EMPLOYEES_SEQ_GEN",sequenceName = "EMPLOYEE_SEQ", allocationSize = 1)
public class Employee {
    @Id
    @Column(name="NO")
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "EMPLOYEES_SEQ_GEN")
    private Long id;

    @Column(unique = true,nullable = false)
    private Long employeeNo; // 사원 번호

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false,unique = true)
    private String phone; // 사내 전화번호


    private String photo;

    @Embedded
    private Part part; // 부서,팀,맡은일, 직책

    @Column(unique = true,nullable = false)
    private String email;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private LocalDateTime joinedDate; // 입사일

    @Enumerated(EnumType.STRING)
    private EmployeePosition employeePosition; // 직책에따른 권한
}
