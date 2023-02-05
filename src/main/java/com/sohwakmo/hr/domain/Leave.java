package com.sohwakmo.hr.domain;

import javax.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Entity(name="LEAVE")
@SequenceGenerator(name = "LEAVE_SEQ_GEN", sequenceName = "LEAVE_SEQ", allocationSize = 1)
public class Leave extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LEAVE_SEQ_GEN")
    private Integer no;

    @Column(nullable = false) // 시행자 사번
    private String employeeNo;

    @Column(nullable = false) // 승인자 사번
    private String approverNo;

    @Column(nullable = false) // 승인자2 사번 (상무)
    private String secondApproverNO;

    @Column(nullable = false, length = 100) // 제목
    private String title;

    @Column(nullable = false) // 사유
    private String reason;

    @Column(nullable = false) // 결재 분류
    private String category;

    @Column // 세미 결재 상태
    private boolean semiState;

    @ElementCollection(fetch = FetchType.EAGER) // 결재 상태
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Set<PaymentState> state = new HashSet<>();

    @Column // 반려 사유
    private String returnReason;

    @Column(nullable = false) // 시행일자
    private String effectiveDate;

    @Column // 중간 결재일시
    private LocalDateTime semiCompeteDate;
    @Column // 최종 결재일시
    private LocalDateTime competeDate;

    public Leave addRole(PaymentState status) {
        state.add(status);
        return this;
    }
    public Leave semiAdd(LocalDateTime semiCompeteDate){
        this.semiCompeteDate = semiCompeteDate;
        return this;
    }

    public Leave add(LocalDateTime competeDate){
        this.competeDate = competeDate;
        return this;
    }

    public Leave returnReason(String returnReason){
        this.returnReason = returnReason;
        return this;
    }



}
