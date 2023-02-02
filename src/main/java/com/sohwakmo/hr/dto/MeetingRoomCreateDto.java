package com.sohwakmo.hr.dto;

import com.sohwakmo.hr.domain.Employee;
import com.sohwakmo.hr.domain.MeetingRoom;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Builder
@Data
public class MeetingRoomCreateDto {

    private Integer meetingRoomNo;
    private String title;

    private String roomName;

    private String roomPlace;

    private String reserveDate;

    private String startTime;

    private String endTime;

    private Integer attendee;

    private String purpose;

    private Long employeeNo;

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
                .build();
    }




}
