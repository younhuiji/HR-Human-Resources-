package com.sohwakmo.hr.repository;

import com.sohwakmo.hr.domain.MeetingRoomReservationTime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MeetingRoomReservationTimeRepository extends JpaRepository<MeetingRoomReservationTime, Integer> {

    Optional<MeetingRoomReservationTime> findByMeetingRoomNo(Integer meetingRoomNo);
}
