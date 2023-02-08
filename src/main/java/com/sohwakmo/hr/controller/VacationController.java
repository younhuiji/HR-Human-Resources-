package com.sohwakmo.hr.controller;

import com.sohwakmo.hr.domain.Employee;
import com.sohwakmo.hr.domain.Vacation;
import com.sohwakmo.hr.dto.VacationCreateDto;
import com.sohwakmo.hr.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/vacation")
public class VacationController {

    private final VacationService vacationService;
    private final EmployeeService employeeService;

    // 휴가(vacation) create list
    @GetMapping("/create")
    public String create(){
        return "/vacation/create";
    }

    // 휴가(vacation) create
    @PostMapping("/create")
    public String create(VacationCreateDto dto){

        String no = "1";

        Vacation vacation = Vacation.builder()
                .employeeNo(no).approverNo(no).title(dto.getTitle()).reason(dto.getReason())
                .category(dto.getCategory()).effectiveDate(dto.getEffectiveDate()).expirationDate(dto.getExpirationDate())
                .build();

        Vacation vacations = vacationService.create(vacation);
        log.info("vacation={}", vacations);

        return "/payment/create";
    }

    @GetMapping("/detail")
    public void detail(Model model, @RequestParam Integer no) {

        Vacation vacation = vacationService.selectByNo(no);
        model.addAttribute("vacation", vacation);
        log.info("vacation={}", vacation);

        Employee employee = employeeService.selectByNo(vacation.getEmployeeNo());
        model.addAttribute("employee", employee);
        log.info("employee={}", employee);

        Employee approver = employeeService.selectByNo(vacation.getApproverNo());
        model.addAttribute("approver", approver);


    }

}
