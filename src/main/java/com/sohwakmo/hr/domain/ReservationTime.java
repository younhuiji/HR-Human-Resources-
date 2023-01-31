package com.sohwakmo.hr.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Entity
@Data
@RequiredArgsConstructor
public class ReservationTime {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    private Integer meetingRoomNo;

    @ManyToOne(fetch = FetchType.LAZY)
    private Employee employee;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private String reserveDate;  // 예약날짜

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(nullable = false)
    private String startTime;  // 시작시간

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(nullable = false)
    private String endTime;  // 종료시간

}
