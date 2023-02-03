package com.sohwakmo.hr.domain;

import javax.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
@Entity(name="BUSINESSCARD")
@SequenceGenerator(name = "BUSINESSCARD_SEQ_GEN", sequenceName = "BUSINESSCARD_SEQ", allocationSize = 1)
public class BusinessCard extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BUSINESSCARD_SEQ_GEN")
    private Integer no;

    @Column(nullable = false) // 시행자 사번
    private String employeeNo;

    @Column(nullable = false) // 승인자 사번
    private String approverNo;

    @Column(nullable = false, length = 100) // 제목
    private String title;

    @Column(nullable = false) // 사유
    private String reason;

    @Column(nullable = false) // 결재 분류
    private String category;

    @ElementCollection(fetch = FetchType.EAGER) // 결재 상태
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Set<PaymentState> state = new HashSet<>();

    @Column // 반려 사유
    private String returnReason;

    @Column // 최종 결재일시
    private LocalDateTime competeDate;

}
