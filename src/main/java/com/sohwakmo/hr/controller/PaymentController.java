package com.sohwakmo.hr.controller;

import com.sohwakmo.hr.domain.Leave;
import com.sohwakmo.hr.domain.Vacation;
import com.sohwakmo.hr.dto.VacationCreateDto;
import com.sohwakmo.hr.service.VacationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/payment")
public class PaymentController {

    private final VacationService vacationService;

    // 기안 문서 list
    @GetMapping("/list")
    public void list(Model model) {

        // TODO: 임원 테이블 연결 시에 수정하기
        String no = "1";
        List<Vacation> list = vacationService.selectByEmployeeNo(no);
//        model.addAttribute("vacation", list);
        log.info("휴가 리스트 ={}", list);

    }

    // 기안 문서 create
    @GetMapping("/create")
    public void create() {
    }

    @PostMapping("/create")
    public void create(VacationCreateDto dto){

        String no = "1";

//        Vacation vacation = Vacation.builder()
//                .title(dto.getTitle()).state(dto.getState()).employeeName(dto.getEmployeeName()).employeeNo(1L).category(dto.getCategory())
//                .returnReason(dto.getReturnReason()).writeDate(dto.getWriteDate()).effectiveDate(dto.getEffectiveDate())
//                .expirationDate(dto.getExpirationDate()).approverNo(0L).reason(dto.getReason()).competeDate(dto.getCompeteDate()).build();

    }



}
