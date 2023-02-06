package com.sohwakmo.hr.service;

import com.sohwakmo.hr.domain.BusinessTrip;
import com.sohwakmo.hr.domain.Employee;
import com.sohwakmo.hr.domain.MeetingRoom;
import com.sohwakmo.hr.domain.Vacation;
import com.sohwakmo.hr.dto.BusinessTripReadDto;
import com.sohwakmo.hr.dto.MeetingReadDto;
import com.sohwakmo.hr.dto.OrgReadDto;
import com.sohwakmo.hr.dto.VacationListReadDto;
import com.sohwakmo.hr.repository.BusinessTripRepository;
import com.sohwakmo.hr.repository.MeetingRoomRepository;
import com.sohwakmo.hr.repository.OrgRepository;
import com.sohwakmo.hr.repository.VacationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrgService {

    private final OrgRepository orgRepository;
    private final MeetingRoomRepository meetingRoomRepository;
    private final BusinessTripRepository businessTripRepository;

    private final VacationRepository vacationRepository;



    public List<OrgReadDto> readAllOrgList() {
        log.info("readAllOrgList()");
        List<Employee> list = orgRepository.findByOrderByPartDepartmentAscPartTeamAscEmployeePositionDesc();

        return list.stream()
                .map(OrgReadDto::fromEntity)
                .toList();
    }

    public List<OrgReadDto> readMemberInfo(Long memberNo) {
        log.info("readMemberInfo(member={})", memberNo);

        List<Employee> memberInfo = orgRepository.findById(memberNo);
        log.info("memberInfo={}", memberInfo);

        return memberInfo.stream()
                .map(OrgReadDto::fromEntity)
                .toList();
    }

    public List<MeetingReadDto> readMeetingList(String loginUser) {
        log.info("readAllCalList(loginUser={})", loginUser);


        List<MeetingRoom> meetingSchedule = meetingRoomRepository.findByAttendeeOrEmployeeNo(loginUser, loginUser);
        log.info("meetingSchedule={}", meetingSchedule);

        return meetingSchedule.stream()
                .map(MeetingReadDto::fromEntity)
                .toList();
    }

    public List<BusinessTripReadDto> readBusinessTripList(String loginUser) {
        log.info("readBusinessTripList(loginUser={})", loginUser);

        List<BusinessTrip> businessTripSchedule = businessTripRepository.findByEmployeeNoOrCompanionNO(loginUser, loginUser);
        log.info("businessTripSchedule={}", businessTripSchedule);

        return businessTripSchedule.stream()
                .map(BusinessTripReadDto::fromEntity)
                .toList();
    }

    public List<VacationListReadDto> readVacationList(String loginUser) {
        log.info("readVacationList(loginUser={})", loginUser);

        List<Vacation> vacationSchedule = vacationRepository.findByEmployeeNo(loginUser);
        log.info("vacationSchedule={}", vacationSchedule);

        return vacationSchedule.stream()
                .map(VacationListReadDto::fromEntity)
                .toList();
    }
}
