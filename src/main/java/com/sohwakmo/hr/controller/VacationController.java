package com.sohwakmo.hr.controller;

import com.sohwakmo.hr.domain.BusinessTrip;
import com.sohwakmo.hr.domain.Vacation;
import com.sohwakmo.hr.dto.VacationCreateDto;
import com.sohwakmo.hr.service.VacationService;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/vacation")
public class VacationController {

    private final VacationService vacationService;

    @GetMapping("/create")
    public String create(){

        return "/vacation/create";
    }
    @PostMapping("/create")
    public String create(VacationCreateDto dto){

        Vacation vacation = Vacation.builder()
                .title(dto.getTitle()).state(dto.getState()).employeeName(dto.getEmployeeName()).employeeNo(1L).category(dto.getCategory())
                .returnReason(dto.getReturnReason()).writeDate(dto.getWriteDate()).effectiveDate(dto.getEffectiveDate())
                .expirationDate(dto.getExpirationDate()).approverNo(0L).reason(dto.getReason()).competeDate(dto.getCompeteDate()).build();

        Vacation vacations = vacationService.create(vacation);

        return "/vacation/create";
    }

}
