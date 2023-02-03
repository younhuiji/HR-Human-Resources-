package com.sohwakmo.hr.repository;

import com.sohwakmo.hr.domain.MeetingRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MeetingRoomRepository extends JpaRepository<MeetingRoom, Integer> {
    List<MeetingRoom> findAll();

    List<MeetingRoom> findByAttendeeOrReservationNo(Long attendee, Long reservationNo);
}
