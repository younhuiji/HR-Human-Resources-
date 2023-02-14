package com.sohwakmo.hr.repository;

import com.sohwakmo.hr.domain.MeetingRoom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface MeetingRoomRepository extends JpaRepository<MeetingRoom, Integer> {
    List<MeetingRoom> findAll();

//    List<MeetingRoom> findByAttendeeOrEmployeeNo(String attendee, String employeeNo);
    @Query(value = "SELECT * from MEETING_ROOM m where m.employee_no = :employee_no or m.attendee like %:attendee% ", nativeQuery = true)
    List<MeetingRoom> findByEmployeeNoOrAttendeeQuestion(@RequestParam("employee_no") String employee_no, @RequestParam("attendee") String attendee);
//    List<MeetingRoom> findByEmployeeNoOrderByMeetingRoomNoDesc(String employeeNo);

List<MeetingRoom> findByEmployeeNoOrderByMeetingRoomNoDesc(@Param(value = "employeeNo") String employeeNo);



    List<MeetingRoom> findByReserveDateOrderByRoomNameAscStartTimeAsc(String date);
    List<MeetingRoom> findByEmployeeNoAndReserveDateContaining(String employeeNo, String formatedNow);
}
