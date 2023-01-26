package com.sohwakmo.hr.dto;

import com.sohwakmo.hr.domain.MeetingRoom;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@AllArgsConstructor
@Builder
@Getter
@ToString
public class CalReadDto {
    private Integer meetingRoomNo;
    private String title;
    private String roomName;
    private String roomPlace;
    private String reserveDate;
    private String start;
    private String end;
    private String attendee;
    private String purpose;

    public static CalReadDto fromEntity(MeetingRoom entity){
        return CalReadDto.builder()
                .meetingRoomNo(entity.getMeetingRoomNo())
                .title(entity.getTitle())
                .roomName(entity.getRoomName())
                .roomPlace(entity.getRoomPlace())
                .reserveDate(entity.getReserveDate())
                .start(entity.getStartTime())
                .end(entity.getEndTime())
                .attendee(entity.getAttendee())
                .purpose(entity.getPurpose())
                .build();
    }
}
