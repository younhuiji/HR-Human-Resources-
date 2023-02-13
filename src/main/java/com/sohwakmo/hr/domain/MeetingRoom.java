package com.sohwakmo.hr.domain;



import lombok.*;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.List;
import java.util.Map;


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


    @Column(nullable = false)
    private String employeeNo;  // 예약자 사번

    @Column(nullable = false)
    private String reserveName;  // 예약자 이름

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

    @Convert(converter = StringListConverter.class)
    private List<String> attendee;  // 참석자사번
    @Column(nullable = false)
    private String purpose; // 사용목적

    private boolean state;   // 현재 상태  -- 써야하나 말아야 하나(true : 예약 중)

    public MeetingRoom update( String title, List<String> attendee, String purpose) {
        this.title = title;

        this.attendee = attendee;
        this.purpose = purpose;

        return this;
    }
}
