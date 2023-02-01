package com.sohwakmo.hr.domain;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Entity
@Data
@ToString(exclude = {"employee"})
@Builder
@DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "ATTENDANCES_SEQ_GEN",sequenceName = "ATTENDANCE_SEQ", allocationSize = 1)
public class Attendance {

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "ATTENDANCES_SEQ_GEN")
    private Long id;

    private String month; // 달
    private String day; // 날짜
    @Column(columnDefinition = "varchar(255) default '00:00'")
    private String startTime; // 출근시간
    @Column(columnDefinition = "varchar(255) default '00:00'")
    private String expectEndTime; // 예상 퇴근시간
    @Column(columnDefinition = "varchar(255) default '00:00'")
    private String endTime; // 퇴근시간
    @Column(columnDefinition = "integer default 2") //  출근 0, 지각,조퇴 1, 결근 2
    private Integer state; // 출근, 지각, 조퇴, 결근

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMPLOYEE_NO")
    private Employee employee; // 사원 객체로 접근해서 사원번호로 관리
}
