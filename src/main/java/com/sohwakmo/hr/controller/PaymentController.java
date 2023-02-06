package com.sohwakmo.hr.controller;

import com.sohwakmo.hr.domain.BusinessCard;
import com.sohwakmo.hr.domain.BusinessTrip;
import com.sohwakmo.hr.domain.Leave;
import com.sohwakmo.hr.domain.Vacation;
import com.sohwakmo.hr.dto.VacationCreateDto;
import com.sohwakmo.hr.service.BusinessCardService;
import com.sohwakmo.hr.service.BusinessTripService;
import com.sohwakmo.hr.service.LeaveService;
import com.sohwakmo.hr.service.VacationService;
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
@RequestMapping("/payment")
public class PaymentController {

    private final VacationService vacationService;
    private final BusinessTripService businessTripService;
    private final BusinessCardService businessCardService;
    private final LeaveService leaveService;

    // 기안 문서 list
    @GetMapping("/list")
    public void list(Model model, @RequestParam(defaultValue = "vacation")String payment,
                     @RequestParam(required = false,defaultValue = "")String keyword) {


        // TODO: 임원 테이블 연결 시에 수정하기
        String no = "1";

        if(payment.equals("vacation")){
            List<Vacation> list = vacationService.selectByEmployeeNo(no);
            log.info(list.toString());
            model.addAttribute("list", list);
        } else if(payment.equals("trip")) {
            List<BusinessTrip> list = businessTripService.selectByEmployeeNo(no);
            log.info(list.toString());
            model.addAttribute("list", list);
        } else if(payment.equals("leave")) {
            List<Leave> list = leaveService.selectByEmployeeNO(no);
            model.addAttribute("list", list);
        } else {
            List<BusinessCard> list = businessCardService.selectByEmployeeNo(no);
            model.addAttribute("list", list);
        }

    }


    // 기안 문서 create
    @GetMapping("/create")
    public void create() {
    }


}
