package com.sohwakmo.hr.dto;

import com.sohwakmo.hr.domain.MeetingRoom;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class MeetingRoomUpdateDto {

    private Integer meetingRoomNo;

    private String title;

    private String roomName;

    private String roomPlace;

    private String startTime;

    private String endTime;

    private Integer attendee;

    private String purpose;

    public MeetingRoom toEntity() {
        return MeetingRoom.builder()
                .meetingRoomNo(meetingRoomNo)
                .title(title)
                .roomName(roomName)
                .roomPlace(roomPlace)
                .startTime(startTime)
                .endTime(endTime)
                .attendee(attendee)
                .purpose(purpose)
                .build();
    }
}
