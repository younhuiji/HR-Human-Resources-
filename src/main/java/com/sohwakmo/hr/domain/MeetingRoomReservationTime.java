package com.sohwakmo.hr.domain;

import javax.persistence.*;
import lombok.*;

import javax.persistence.*;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
@Entity(name = "RESERVATION_TIME")
@SequenceGenerator(name = "RESERVATION_TIME_SEQ_GEN", sequenceName = "RESERVATION_TIME_SEQ", allocationSize = 1)
public class MeetingRoomReservationTime {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "RESERVATION_TIME_SEQ_GEN")
    private Integer no;

    private Integer meetingRoomNo;

    private String roomName;  // 회의실 이름

    private String reserveDate;  // 예약날짜

    private String reserveTime;  // 예약시간

}
