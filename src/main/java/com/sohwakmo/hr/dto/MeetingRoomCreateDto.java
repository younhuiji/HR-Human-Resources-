package com.sohwakmo.hr.dto;

import com.sohwakmo.hr.domain.MeetingRoom;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MeetingRoomCreateDto {

    private Integer meetingRoomNo;

    private String reserveName;  // 예약자 이름
    private String title;  // 제목

    private String roomName;  // 회의실 이름

    private String roomPlace;  // 회의실 위치

    private String reserveDate; // 예약 날짜

    private String startTime;  // 예약 시작시간

    private String endTime;  //  예약 종료시간

    private String attendee;  // 참석자 사번

    private String purpose;  // 내용

    private String employeeNo;  // 예약자 사번

    public MeetingRoom toEntity() {
        return MeetingRoom.builder()
                .meetingRoomNo(meetingRoomNo)
                .title(title)
                .roomName(roomName)
                .roomPlace(roomPlace)
                .reserveDate(reserveDate)
                .startTime(startTime)
                .endTime(endTime)
                .attendee(attendee)
                .purpose(purpose)
                .employeeNo(employeeNo)
                .reserveName(reserveName)
                .build();
    }




}
