package com.sohwakmo.hr.service;

import com.sohwakmo.hr.domain.MeetingRoom;
import com.sohwakmo.hr.dto.MeetingRoomCreateDto;
import com.sohwakmo.hr.dto.MeetingRoomUpdateDto;
import com.sohwakmo.hr.repository.MeetingRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

//    public List<MeetingRoom> readTime(String date) {
//        log.info("date={}", date);
//
////        List<MeetingRoom> meetingRoomTime = meetingRoomRepository.findByReserveDateOrderByRoomNameAscStartTimeAsc(date);
////        log.info("meetingRoomTime={}", meetingRoomTime);
//        return meetingRoomRepository.findByReserveDateOrderByRoomNameAscStartTimeAsc();
//    }

    //    public List<MeetingReadDto> readMeetingListByDate(String date) {
//        log.info("readAllMeetingList(date={})", date);
//
//        List<MeetingRoom> meetingRoomListByDate = meetingRoomRepository.findByReserveDateOrderByRoomNameAscStartTimeAsc(date);
//        log.info("meetingRoomListByDate={}", meetingRoomListByDate);
//
//        return meetingRoomListByDate.stream()
//                .map(MeetingReadDto::fromEntity)
//                .toList();
//    }


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
        MeetingRoom newMeetingRoom = entity.update(dto.getTitle(), dto.getAttendee(), dto.getPurpose());
        log.info("newMeetingRoom = {}",newMeetingRoom.toString());

        return entity.getMeetingRoomNo();
    }


}
