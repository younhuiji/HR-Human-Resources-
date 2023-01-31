package com.sohwakmo.hr.service;

import com.sohwakmo.hr.domain.Employee;
import com.sohwakmo.hr.domain.MeetingRoom;
import com.sohwakmo.hr.dto.MeetingRoomCreateDto;
import com.sohwakmo.hr.dto.MeetingRoomUpdateDto;
import com.sohwakmo.hr.repository.MeetingRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class MeetingRoomService {

    private final MeetingRoomRepository meetingRoomRepository;

    public List<MeetingRoom> read() {
        log.info("read()");

        return meetingRoomRepository.findByOrderByMeetingRoomNoDesc();
    }

    public MeetingRoom read(Integer meetingRoomNo) {
        log.info("read(meetingRoomNo = {})", meetingRoomNo);

        return meetingRoomRepository.findById(meetingRoomNo).get();
    }

    public MeetingRoom create(MeetingRoomCreateDto dto) {
        log.info("create(dto={})", dto);

        MeetingRoom entity = meetingRoomRepository.save(dto.toEntity());

        return entity;
    }

    // 삭제
    public Integer delete(Integer meetingRoomNo) {
        log.info("delete(meetingRoomNo={})", meetingRoomNo);

        MeetingRoom meetingRoom =meetingRoomRepository.findById(meetingRoomNo).get();

        return meetingRoomNo;
    }

    public Integer update(MeetingRoomUpdateDto dto) {
        log.info("update(dto={})", dto);

        MeetingRoom entity = meetingRoomRepository.findById(dto.getMeetingRoomNo()).get();
        MeetingRoom newMeetingRoom = entity.update(dto.getTitle(), dto.getRoomName(), dto.getRoomPlace(), dto.getReserveDate(), dto.getStartTime(), dto.getEndTime(), dto.getAttendee(), dto.getAttendeeMax(), dto.getPurpose());
        log.info("newMeetingRoom={}");

        return entity.getMeetingRoomNo();
    }
}
