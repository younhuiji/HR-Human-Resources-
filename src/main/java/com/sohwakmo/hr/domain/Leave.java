package com.sohwakmo.hr.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
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

    @Enumerated(EnumType.STRING) // 결재 상태
    private PaymentState state;

    @Column // 반려 사유
    private String returnReason;

    @Column(nullable = false) // 시행일자
    private String effectiveDate;

    @Column // 결재일시
    private LocalDateTime competeDate;

    public static String formatDate(LocalDateTime time){
        String formatDate = time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        return formatDate;
    }

}
