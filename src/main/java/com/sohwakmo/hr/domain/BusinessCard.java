package com.sohwakmo.hr.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
@Entity(name="BUSINESSCARD")
@SequenceGenerator(name = "BUSINESSCARD_SEQ_GEN", sequenceName = "BUSINESSCARD_SEQ", allocationSize = 1)
public class BusinessCard {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BUSINESSCARD_SEQ_GEN")
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

    @Enumerated(EnumType.STRING) // 결재 상태
    private PaymentState state;

    @Column // 반려 사유
    private String returnReason;

    @Column(nullable = false) // 작성일자
    private String writeDate;

    @Column(nullable = false, unique = true) // 명함에 쓰일 이메일
    private String email;

    @Column(nullable = false, unique = true) // 명함의 쓰일 전화번호
    private String phone;

}
