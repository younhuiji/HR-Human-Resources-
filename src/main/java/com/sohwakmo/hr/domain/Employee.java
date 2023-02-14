package com.sohwakmo.hr.domain;

import javax.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@ToString(exclude = {"attendances"})
@Table(uniqueConstraints = {@UniqueConstraint(name = "PHONE_EMAIL_UNIQUE", columnNames = {"PHONE","EMAIL"})})
@SequenceGenerator(name = "EMPLOYEES_SEQ_GEN",sequenceName = "EMPLOYEE_SEQ", allocationSize = 1)
public class Employee implements Serializable {
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

    @Column(nullable = false,unique = true)
    private String phone; // 사내 전화번호


    @Column(columnDefinition = "varchar(255) default '/images/employeeImage/사진미정'")
    private String photo;

    @Embedded
    private Part part; // 부서,팀,맡은일

    @Column(columnDefinition = "varchar(255) default '사원'")
    private String position; // 직책

    @Column(nullable = false)
    private String email; // 사내 email or 쪽지 주소

    @Column(nullable = false)
    private String joinedDate; // 입사일

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    @Builder.Default
    private List<Attendance> attendances = new ArrayList<Attendance>(); // 사원번호로 출격 관리 리스트 불러오기

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Set<EmployeePosition> employeePosition = new HashSet<>(); // 직책에따른 권한

    public Employee addRole(EmployeePosition position) {
        employeePosition.add(position);
        return this;
    }

    public Employee update(String name, String phone, Part part,String photo) {
        this.name = name;
        this.phone = phone;
        this.part = part;
        this.photo = photo;
        return this;
    }

    public Employee update(String name, String phone,String photo) {
        this.name = name;
        this.phone = phone;
        this.photo = photo;
        return this;
    }

}
