package com.sohwakmo.hr.controller;

import com.sohwakmo.hr.domain.BusinessCard;
import com.sohwakmo.hr.dto.BusinessCardCreateDto;
import com.sohwakmo.hr.service.BusinessCardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/businessCard")
public class BusinessCardController {

    private final BusinessCardService businessCardService;

    @GetMapping("/create")
    public  String create(){
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
