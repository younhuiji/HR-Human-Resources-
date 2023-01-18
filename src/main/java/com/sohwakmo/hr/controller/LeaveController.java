package com.sohwakmo.hr.controller;

import com.sohwakmo.hr.domain.Leave;
import com.sohwakmo.hr.dto.LeaveCreateDto;
import com.sohwakmo.hr.service.LeaveService;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/leave")
public class LeaveController {

    private final LeaveService leaveService;

    @GetMapping("/create")
    public String create(){

        return "/leave/create";
    }

    @PostMapping("/create")
    public String create(LeaveCreateDto dto){

        Leave leave = Leave.builder()
                .title(dto.getTitle()).category(dto.getCategory()).employeeName(dto.getEmployeeName()).employeeNo(1L)
                .effectiveDate(dto.getEffectiveDate()).approverNo(0L).secondApproverNO(1L).reason(dto.getReason())
                .build();
        Leave leaves = leaveService.create(leave);

        return "/leave/create";
    }

}
