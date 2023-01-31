package com.sohwakmo.hr.dto;

import com.sohwakmo.hr.domain.MeetingRoom;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class MeetingRoomMyReserveDto {

    private Integer meetingRoomNo;

    private String roomName;

    private String roomPlace;

    private Date reserveDate;

    private String startTime;

    private String endTime;

    private String title;

    public MeetingRoom toEntity() {
        return MeetingRoom.builder()
                .meetingRoomNo(meetingRoomNo)
                .roomName(roomName)
                .roomPlace(roomPlace)
                .startTime(startTime)
                .endTime(endTime)
                .title(title)
                .build();

    }




}
