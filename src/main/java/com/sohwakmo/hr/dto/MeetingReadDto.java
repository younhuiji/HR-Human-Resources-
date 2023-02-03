package com.sohwakmo.hr.dto;

import com.sohwakmo.hr.domain.MeetingRoom;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Builder
@Getter
@ToString
public class MeetingReadDto {
    private Integer meetingRoomNo;
    private Long reservationNo;
    private String title;
    private String roomName;
    private String roomPlace;
    private String reserveDate;
    private String start;
    private String end;
    private Long attendee;
    private String purpose;

    public static MeetingReadDto fromEntity(MeetingRoom entity){
        return MeetingReadDto.builder()
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
