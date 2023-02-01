package com.sohwakmo.hr.domain;

import jakarta.persistence.*;
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
    private Long employeeNo;

    @Column(nullable = false) // 승인자 사번
    private Long approverNo;

    @Column(nullable = false) // 승인자2 사번 (상무)
    private Long secondApproverNO;

    @Column(nullable = false, length = 100) // 제목
    private String title;

    @Column(nullable = false) // 사유
    private String reason;

    @Column(nullable = false) // 결재 분류
    private String category;

    @Column // 세미 결재 분류
    private boolean semiState;

    @ElementCollection(fetch = FetchType.EAGER) // 결재 상태
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Set<PaymentState> state = new HashSet<>();

    @Column // 반려 사유
    private String returnReason;

    @Column(nullable = false) // 시행일자
    private String effectiveDate;

    @Column // 결재일시
    private LocalDateTime competeDate;

    public Leave addRole(PaymentState status) {
        state.add(status);
        return this;
    }

    public Leave add(LocalDateTime competeDate){
        competeDate.toLocalDate();
        return this;
    }


}
