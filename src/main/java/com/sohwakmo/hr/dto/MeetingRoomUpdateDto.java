package com.sohwakmo.hr.dto;

import com.sohwakmo.hr.domain.MeetingRoom;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
public class MeetingRoomUpdateDto {

    private Integer meetingRoomNo;

    private String title;

    private String roomName;

    private String roomPlace;

    private String reserveDate;

    private String startTime;

    private String endTime;

    private String attendee;

    private Integer attendeeMax;

    private String purpose;

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
                .attendeeMax(attendeeMax)
                .purpose(purpose)
                .build();
    }
}
