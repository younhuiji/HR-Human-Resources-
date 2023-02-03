package com.sohwakmo.hr.domain;


import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Entity(name = "MEETING_ROOM")
@SequenceGenerator(name= "MEETING_ROOM_SEQ_GEN", sequenceName = "MEETING_ROOM_SEQ", allocationSize =  1)
public class MeetingRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEETING_ROOM_SEQ_GEN")
    private Integer meetingRoomNo;

//    @ManyToOne(fetch = FetchType.LAZY)
//    private Employee employee;

    @Column(nullable = false)
    private String reservationNo;

    @Column(nullable = false)
    private String title;  // 제목

    @Column(nullable = false)
    private String roomName;  // 회의실명

    @Column(nullable = false)
    private String roomPlace;  // 회의실 위치

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private String reserveDate;  // 예약날짜

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(nullable = false)
    private String startTime;  // 시작시간

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @Column(nullable = false)
    private String endTime;  // 종료시간

    @Column(nullable = false)
    private String reservationName; // 예약자

    @Column(nullable = false)
    private String attendee;  // 참석자

    @Column(nullable = false)
    private Integer attendeeMax;  // 참석인원(최대 9명?)

    @Column(nullable = false)
    private String purpose; // 사용목적

    private boolean state;   // 현재 상태  -- 써야하나 말아야 하나(true : 예약 중)

    public MeetingRoom update(String title, String roomName, String roomPlace, String startTime, String endTime, String attendee, Integer attendeeMax, String purpose) {
        this.title = title;
        this.roomName = roomName;
        this.roomPlace = roomPlace;
        this.startTime = startTime;
        this.endTime = endTime;
        this.attendee = attendee;
        this.attendeeMax = attendeeMax;
        this.purpose = purpose;

        return this;
    }
}