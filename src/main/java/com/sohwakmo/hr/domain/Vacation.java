package com.sohwakmo.hr.domain;

import javax.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
@Entity(name="VACATION")
@SequenceGenerator(name = "VACATION_SEQ_GEN", sequenceName = "VACATION_SEQ", allocationSize = 1)
public class Vacation  extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VACATION_SEQ_GEN")
    private Integer no;

    @Column(nullable = false) // 시행자 사번
    private String employeeNo;

    @Column(nullable = false) // 승인자1 사번
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

    @Column(nullable = false) // 시행일자
    private String effectiveDate;

    @Column(nullable = false) // 종료일자
    private String expirationDate;

    @Column // 최종 결재일시
    private LocalDateTime competeDate;

    public Vacation addRole(PaymentState status) {
        state.add(status);
        return this;
    }

    public Vacation add(LocalDateTime competeDate){
        this.competeDate = competeDate;
        return this;
    }

    public Vacation returnReason(String returnReason){
         this.returnReason = returnReason;
         return this;
    }



}
