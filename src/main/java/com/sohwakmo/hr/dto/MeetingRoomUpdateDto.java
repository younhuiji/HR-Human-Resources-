package com.sohwakmo.hr.dto;

import com.sohwakmo.hr.domain.MeetingRoom;
import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
@Builder
public class MeetingRoomUpdateDto {

    private Integer meetingRoomNo;

    private String title;


    private List<String> attendee;

    private String purpose;

    public MeetingRoom toEntity() {
        return MeetingRoom.builder()
                .meetingRoomNo(meetingRoomNo)
                .title(title)
                .attendee(attendee)
                .purpose(purpose)
                .build();
    }
}
