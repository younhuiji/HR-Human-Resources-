package com.sohwakmo.hr.controller;

import com.sohwakmo.hr.domain.BusinessCard;
import com.sohwakmo.hr.domain.Employee;
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
        // 회원 테이블에서 팀장, 상무의 이름 리스트 읽어오기
        String Level3 = "LEVEL_3";
        List<Employee> list = employeeService.read(Level3);
        model.addAttribute("list", list);

        log.info("list={}", list);
        log.info("listsize={}",list.size());

        return "/businessCard/create";
    }

    @PostMapping ("/create")
    public String create(BusinessCardCreateDto dto){

        // TODO: employee에서 시행자 이름, 사번, 결재자 사번, 직급, 직책 가져오기
        // Employee employee = employeeService.findEmployeeByNo(no);

        BusinessCard businessCard = BusinessCard.builder()
                .title(dto.getTitle()).state(dto.getState()).employeeName(dto.getEmployeeName()).employeeNo(1L).category(dto.getCategory())
                .email(dto.getEmail()).phone(dto.getPhone()).approverNo(0L).reason(dto.getReason()).writeDate(dto.getWriteDate()).build();

        BusinessCard businessCards = businessCardService.create(businessCard);
        log.info("명함 신청 양식:", businessCards);

        return "/businessCard/create";
    }

}
