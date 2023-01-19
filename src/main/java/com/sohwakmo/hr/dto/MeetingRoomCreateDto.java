package com.sohwakmo.hr.dto;

import com.sohwakmo.hr.domain.Employee;
import com.sohwakmo.hr.domain.MeetingRoom;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MeetingRoomCreateDto {
    private String title;

    private String roomName;

    private String roomPlace;

    private String reserveDate;

    private String startTime;

    private String endTime;

    private String attendee;

    private Integer attendeeMax;

    private String purpose;

    private Employee employee;

    public MeetingRoom toEntity() {
        return MeetingRoom.builder()
                .title(title)
                .roomName(roomName)
                .roomPlace(roomPlace)
                .reserveDate(reserveDate)
                .attendee(attendee)
                .attendeeMax(attendeeMax)
                .purpose(purpose)
                .employee(employee).build();
    }




}
