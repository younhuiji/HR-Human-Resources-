package com.sohwakmo.hr.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Entity
@Data
@Builder
@DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "ATTENDANCES_SEQ_GEN",sequenceName = "ATTENDANCE_SEQ", allocationSize = 1)
public class Attendance {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "ATTENDANCES_SEQ_GEN")
    private Long id;
    @Column(columnDefinition = "varchar(255) default '00:00'")
    private String inTime; // 출근시간
    @Column(columnDefinition = "varchar(255) default '00:00'")
    private String outTime; // 퇴근시간

    private Integer state; // 출근, 지각, 조퇴, 결근

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMPLOYEE_NO")
    private Employee employee; // 사원 객체로 접근해서 사원번호로 관리
}
