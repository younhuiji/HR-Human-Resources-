package com.sohwakmo.hr.service;

import com.sohwakmo.hr.domain.Employee;
import com.sohwakmo.hr.domain.MeetingRoom;
import com.sohwakmo.hr.dto.MeetingRoomCreateDto;
import com.sohwakmo.hr.dto.MeetingRoomUpdateDto;
import com.sohwakmo.hr.repository.EmployeeRepository;
import com.sohwakmo.hr.repository.MeetingRoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class MeetingRoomService {

    private final MeetingRoomRepository meetingRoomRepository;

    private final EmployeeRepository employeeRepository;

    public List<MeetingRoom> read() {
        log.info("read()");

        return meetingRoomRepository.findByOrderByMeetingRoomNoDesc();
    }
//    @Transactional(readOnly = true)
//    public List<MeetingRoom> myRead(String employeeNo) {
//        log.info("myRead(employeeNo={})", employeeNo);
//
//        List<MeetingRoom> list = meetingRoomRepository.findByEmployeeNoOrderByMeetingRoomNoDesc(employeeNo);
//        log.info("list = {}", list);
//
//        return list;
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


    public List<MeetingRoom> getTodayReservation(String employeeNo, String formatedNow) {
        formatedNow = formatedNow.substring(0, 2) + "-" + formatedNow.substring(3);
        return meetingRoomRepository.findByEmployeeNoAndReserveDateContaining(employeeNo,formatedNow);
    }

}
