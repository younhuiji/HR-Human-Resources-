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

    // 명함(bs card) create list
    @GetMapping("/create")
    public  String create(Model model){

        String category = "명함";
        List<BusinessCard> list = businessCardService.selectByCategory(category);
        model.addAttribute("card", list);

        return "/businessCard/create";
    }

    // 명함(bs card) create
    @PostMapping ("/create")
    public String create(BusinessCardCreateDto dto){

        // TODO: employee에서 시행자 이름, 사번, 결재자 사번, 직급, 직책 가져오기
        String no = "1";

        BusinessCard businessCard = BusinessCard.builder()
                .employeeNo(no).approverNo(no).title(dto.getTitle()).reason(dto.getReason())
                .category(dto.getCategory()).build();

        BusinessCard businessCards = businessCardService.create(businessCard);

        return "/businessCard/create";
    }

    // 명함(bs card) create 결재자 지정
    @GetMapping("/approver")
    public String approver( Model model ){

        String teamName = "인사팀";
        // TODO: 조직도 클릭 시 값을 받아와서 필터링해서 보여주기
//        List<Employee> list = employeeService.readPart(teamName);
//        model.addAttribute("list", list);

        return "/businessCard/approver";
    }

    // 명함(bs card) detail
    @GetMapping("/detail")
    public String detail(Model model, @RequestParam Integer no) {

        BusinessCard card = businessCardService.selectByNo(no);
        model.addAttribute("card", card);

        return "/businessCard/detail";
    }

    // 명함(bs card) 반려
    @GetMapping("/returns")
    public String returns() {
        return "/businessCard/returns";
    }
}
