package com.sohwakmo.hr.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
@Entity(name="BUSINESSTRIP")
@SequenceGenerator(name = "BUSINESSTRIP_SEQ_GEN", sequenceName = "BUSINESSTRIP_SEQ", allocationSize = 1)
public class BusinessTrip {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BUSINESSTRIP_SEQ_GEN")
    private Integer no;

    @Column(nullable = false) // 시행자 사번
    private Long employeeNo;

    @Column(nullable = false) // 시행자 이름
    private String employeeName;

    @Column(nullable = false) // 승인자1 사번
    private Long approverNo;

    @Column(nullable = false, length = 100) // 제목
    private String title;

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

    @Column(nullable = false) // 시행일자
    private String effectiveDate;

    @Column(nullable = false) // 종료일자
    private String expirationDate;

    @Column // 결재일시
    private LocalDateTime competeDate;

    @Column(nullable = false) // 출장지
    private String place;

    @Column // 동반 출장자
    private Long companionNO;

}