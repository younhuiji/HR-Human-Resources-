package com.sohwakmo.hr.controller;

import com.sohwakmo.hr.domain.BusinessTrip;
import com.sohwakmo.hr.dto.BusinessTripReadDto;
import com.sohwakmo.hr.dto.MeetingReadDto;
import com.sohwakmo.hr.dto.OrgReadDto;
import com.sohwakmo.hr.dto.VacationListReadDto;
import com.sohwakmo.hr.service.OrgService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/org")
public class OrgRestController {
    private final OrgService orgService;

    @GetMapping("/allList")
    public ResponseEntity<List<OrgReadDto>> readAllOrgList(){
        log.info("readAllOrgList()");

        List<OrgReadDto> list = orgService.readAllOrgList();
        log.info("# of list={}", list.size());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/memberInfo/{memberNo}")
    public ResponseEntity<List<OrgReadDto>> getMemberInfo(@PathVariable Long memberNo){
        log.info("getMemberInfo(member={})", memberNo);

        List<OrgReadDto> memberEntity = orgService.readMemberInfo(memberNo);
        log.info("memberEntity ok");

        return ResponseEntity.ok(memberEntity);
    }

    @GetMapping("/meetingList/{loginUser}")
    public ResponseEntity<List<MeetingReadDto>> readMeetingList(@PathVariable Long loginUser ){
        log.info("readAllCalList(loginUser={})", loginUser);

        List<MeetingReadDto> list = orgService.readMeetingList(loginUser);
        log.info("# of list={}", list.size());

        return ResponseEntity.ok(list);
    }

    @GetMapping("/businessTripList/{loginUser}")
    public ResponseEntity<List<BusinessTripReadDto>> readBusinessTripList(@PathVariable Long loginUser){
        log.info("readBusinessTripList(loginUser={})", loginUser);

        List<BusinessTripReadDto> list = orgService.readBusinessTripList(loginUser);
        log.info("# of list={}", list.size());

        return ResponseEntity.ok(list);
    }

    @GetMapping("/vacationList/{loginUser}")
    public ResponseEntity<List<VacationListReadDto>> readVacationList(@PathVariable Long loginUser){
        log.info("readVacationList(loginUser={})", loginUser);

        List<VacationListReadDto> list = orgService.readVacationList(loginUser);
        log.info("# of list={}", list.size());

        return ResponseEntity.ok(list);
    }

}
