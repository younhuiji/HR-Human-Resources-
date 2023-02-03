package com.sohwakmo.hr.service;

import com.sohwakmo.hr.domain.MeetingRoom;
import com.sohwakmo.hr.domain.MeetingRoomReservationTime;
import com.sohwakmo.hr.dto.MeetingRoomCreateDto;
import com.sohwakmo.hr.dto.MeetingRoomReservationTimeDto;
import com.sohwakmo.hr.dto.MeetingRoomUpdateDto;
import com.sohwakmo.hr.repository.MeetingRoomRepository;
import com.sohwakmo.hr.repository.MeetingRoomReservationTimeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class MeetingRoomService {

    private final MeetingRoomRepository meetingRoomRepository;

    private final MeetingRoomReservationTimeRepository meetingRoomReservationTimeRepository;


    public List<MeetingRoom> read() {
        log.info("read()");

        return meetingRoomRepository.findByOrderByMeetingRoomNoDesc();
    }

    @Transactional
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
    public void delete(Integer meetingRoomNo) {
        log.info("delete(meetingRoomNo={})", meetingRoomNo);


        meetingRoomRepository.deleteById(meetingRoomNo);
    }

    @Transactional
    public Integer update(MeetingRoomUpdateDto dto) {
        log.info("update(dto={})", dto);

        MeetingRoom entity = meetingRoomRepository.findById(dto.getMeetingRoomNo()).get();
        MeetingRoom newMeetingRoom = entity.update(dto.getTitle(),  dto.getStartTime(), dto.getEndTime(), dto.getAttendee(), dto.getPurpose());
        log.info("newMeetingRoom = {}",newMeetingRoom.toString());

        return entity.getMeetingRoomNo();
    }

    @Transactional
    public void addReserveTime(MeetingRoomReservationTimeDto dto) {
        MeetingRoom meetingRoom = meetingRoomRepository.findById(dto.getNo()).get();
        log.info("meetingRoom={}", meetingRoom);

        MeetingRoomReservationTime entity = MeetingRoomReservationTime.builder()
                .reserveDate(dto.getReserveDate())
                .roomName(dto.getRoomName())
                .reserveTime(dto.getReserveTime())
                .build();
        log.info("entity={}", entity);
        Optional<MeetingRoomReservationTime> result = meetingRoomReservationTimeRepository.findByMeetingRoomNo(dto.getMeetingRoomNo()); 

        meetingRoomReservationTimeRepository.save(entity);


    }


}
