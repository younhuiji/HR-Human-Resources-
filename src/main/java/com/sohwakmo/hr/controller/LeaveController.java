package com.sohwakmo.hr.controller;

import com.sohwakmo.hr.domain.Leave;
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
public class LeaveController {

    private final LeaveService leaveService;

    @GetMapping("/create")
    public String create(Model model){

        String name = "홍길동";
        List<Leave> leave = leaveService.selectByName(name);
        log.info("컨트롤={}", leave);
        model.addAttribute("leave", leave);

        return "/leave/create";
    }

    @PostMapping("/create")
    public String create(LeaveCreateDto dto){

        Leave leave = Leave.builder()
                .title(dto.getTitle()).category(dto.getCategory()).employeeNo(1L)
                .effectiveDate(dto.getEffectiveDate()).approverNo(0L).secondApproverNO(1L).reason(dto.getReason())
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
