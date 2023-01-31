package com.sohwakmo.hr.controller;

import com.sohwakmo.hr.domain.BusinessCard;
import com.sohwakmo.hr.domain.Employee;
import com.sohwakmo.hr.domain.PaymentState;
import com.sohwakmo.hr.dto.BusinessCardCreateDto;
import com.sohwakmo.hr.service.BusinessCardService;
import com.sohwakmo.hr.service.EmployeeService;
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
@RequestMapping("/businessCard")
public class BusinessCardController {

    private final BusinessCardService businessCardService;
    private final EmployeeService employeeService;

    @GetMapping("/create")
    public  String create(Model model){

        String category = "명함";
        List<BusinessCard> list = businessCardService.selectByCategory(category);
        model.addAttribute("card", list);

        log.info("list={}", list);
        log.info("listsize={}",list.size());

        return "/businessCard/create";
    }

    @PostMapping ("/create")
    public String create(BusinessCardCreateDto dto){

        // TODO: employee에서 시행자 이름, 사번, 결재자 사번, 직급, 직책 가져오기
        // Employee employee = employeeService.findEmployeeByNo(no);;

        BusinessCard businessCard = BusinessCard.builder()
                .title(dto.getTitle()).employeeName(dto.getEmployeeName()).employeeNo(1L).category(dto.getCategory())
                .email(dto.getEmail()).phone(dto.getPhone()).approverNo(0L).reason(dto.getReason()).writeDate(dto.getWriteDate()).build();

        BusinessCard businessCards = businessCardService.create(businessCard);
        log.info("명함 신청 양식={}", businessCard);

        return "/businessCard/create";
    }

    @GetMapping("/approver")
    public String approver( Model model ){

        String teamName = "인사팀";
        // TODO: 조직도 클릭 시 값을 받아와서 필터링해서 보여주기
        List<Employee> list = employeeService.readPart(teamName);
        model.addAttribute("list", list);

        log.info("list={}", list);
        log.info("listsize={}",list.size());

        return "/businessCard/approver";
    }

    @GetMapping("/detail")
    public String detail(Model model, @RequestParam Integer no) {
        log.info("리스트 번호={}", no);


        BusinessCard card = businessCardService.selectByNo(no);

        model.addAttribute("card", card);

        return "/businessCard/detail";
    }

    @GetMapping("/returns")
    public String returns() {

        return "/businessCard/returns";
    }
}
