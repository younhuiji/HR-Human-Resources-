package com.sohwakmo.hr.controller;

import com.sohwakmo.hr.domain.BaseTimeEntity;
import com.sohwakmo.hr.domain.Leave;
import com.sohwakmo.hr.domain.PaymentState;
import com.sohwakmo.hr.dto.LeaveCreateDto;
import com.sohwakmo.hr.service.LeaveService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/leave")
public class LeaveController  {

    private final LeaveService leaveService;

    @GetMapping("/create")
    public String create(Model model){

        // TODO: 임원 테이블 연결 시에 수정하기
        Integer no = 1;
        List<Leave> leave = leaveService.selectByName(no);
        log.info("컨트롤={}", leave);
        model.addAttribute("leave", leave);

        return "/leave/create";
    }

    @PostMapping("/create")
    public String create(LeaveCreateDto dto){

        // TODO: payment enum 값으로 가져오기

        Leave leave = Leave.builder()
                // TODO: 시행자, 승인자 번호 임원 테이블 연결 시에 수정하기
//                .employeeNo(dto.getEmployeeNo()).approverNo(dto.getApproverNo()).secondApproverNO(dto.getSecondApproverNO())
//                .title(dto.getTitle()).reason(dto.getReason()).category(dto.getCategory()).state(dto.getState())
//                .returnReason(dto.getReturnReason()).writeDate(dto.getWriteDate()).effectiveDate(dto.getEffectiveDate()).competeDate(dto.getCompeteDate())
                .employeeNo(1L).approverNo(1L).secondApproverNO(1L)
                .title(dto.getTitle()).reason(dto.getReason()).category(dto.getCategory())
                .effectiveDate(dto.getEffectiveDate())
                .build();
        Leave leaves = leaveService.create(leave);

        return "/leave/create";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam Integer no, Model model) {

        Leave leave = leaveService.selectByNo(no);
        model.addAttribute("leave",leave);
        log.info("leave 받은 데이터={}", leave);

        return "/leave/detail";
    }

}
