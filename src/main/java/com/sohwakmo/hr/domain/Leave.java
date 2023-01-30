package com.sohwakmo.hr.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
@Entity(name="LEAVE")
@SequenceGenerator(name = "LEAVE_SEQ_GEN", sequenceName = "LEAVE_SEQ", allocationSize = 1)
public class Leave {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LEAVE_SEQ_GEN")
    private Integer no;

    @Column(nullable = false) // 시행자 사번
    private Long employeeNo;

    @Column(nullable = false) // 승인자1 사번
    private Long approverNo;

    @Column // 승인자2 사번 (상무)
    private Long secondApproverNO;

    @Column(nullable = false, length = 100) // 제목
    private String title;

    @Column(nullable = false) // 시행자 팀
    private String employeeTeam;

    @Column(nullable = false) // 시행자 직급
    private String employeePosition;

    @Column(nullable = false) // 사유
    private String reason;

    @Column(nullable = false) // 결재 분류
    private String category;

    @Embedded // 결재 상태
    private PaymentState state;

    @Column // 반려 사유
    private String returnReason;

    @ColumnDefault("SYSDATE") // 작성일자
    private LocalDateTime writeDate;

    @Column(nullable = false) // 입사일자
    private String joinedDate;

    @Column(nullable = false) // 시행일자
    private String effectiveDate;

    @Column // 결재일시
    private LocalDateTime competeDate;

}
