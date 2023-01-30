package com.sohwakmo.hr.domain;


import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(uniqueConstraints = {@UniqueConstraint(name = "PHONE_EMAIL_UNIQUE", columnNames = {"PHONE","EMAIL"})})
@SequenceGenerator(name = "EMPLOYEES_SEQ_GEN",sequenceName = "EMPLOYEE_SEQ", allocationSize = 1)
public class Employee {
    @Id
    @Column(name="NO")
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "EMPLOYEES_SEQ_GEN")
    private Long id;

    @Column(nullable = false,unique = true)
    private String employeeNo; // 사원 번호

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phone; // 사내 전화번호


    private String photo;

    @Embedded
    private Part part; // 부서,팀,맡은일

    @Column(columnDefinition = "varchar(255) default '사원'")
    private String position; // 직책

    @Column(nullable = false)
    private String email; // 사내 email or 쪽지 주소

    @Column(nullable = false)
    private String joinedDate; // 입사일


    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Set<EmployeePosition> employeePosition = new HashSet<>(); // 직책에따른 권한

    public Employee addRole(EmployeePosition position) {
        employeePosition.add(position);
        return this;
    }

    public Employee update(String name, String phone, Part part) {
        this.name = name;
        this.phone = phone;
        this.part = part;
        return this;
    }
}
