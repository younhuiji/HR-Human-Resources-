package com.sohwakmo.hr.repository;

import com.sohwakmo.hr.domain.MeetingRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MeetingRoomRepository extends JpaRepository<MeetingRoom, Integer> {
    List<MeetingRoom> findAll();

    List<MeetingRoom> findByAttendeeOrEmployeeNo(String attendee, String employeeNo);

    List<MeetingRoom> findByOrderByMeetingRoomNoDesc();


//    List<MeetingRoom> findByMeetingRoomNoAndStartTimeAndEndTimeOrderByDesc();

    // 회의실 시간 select
//    List<MeetingRoom> findByRoomNameOrderByReserveDateDesc();

    List<MeetingRoom> findByReserveDateOrderByRoomNameAscStartTimeAsc(String date);
}
