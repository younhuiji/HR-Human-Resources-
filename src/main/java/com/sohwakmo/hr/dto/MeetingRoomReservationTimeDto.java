package com.sohwakmo.hr.dto;

import lombok.Data;

@Data
public class MeetingRoomReservationTimeDto {

    private Integer no;
    private Integer meetingRoomNo; // 번호
    private String roomName; // 회의실 이름
    private String reserveDate;  // 예약 날짜
    private String reserveTime;  // 예약 시간
}
