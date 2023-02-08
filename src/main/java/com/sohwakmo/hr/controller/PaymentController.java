package com.sohwakmo.hr.controller;

import com.sohwakmo.hr.domain.*;
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
    private final EmployeeService employeeService;

    // 기안 문서 list
    @GetMapping("/list")
    public void list(Model model, @RequestParam(defaultValue = "vacation")String payment) {


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

    // 결재 대기 list
    @GetMapping("/process")
    public void process(Model model, @RequestParam(defaultValue = "vacation")String payment) {

        String no = "1";
        PaymentState state = PaymentState.진행중;

        if(payment.equals("vacation")){
            List<Vacation> list = vacationService.selectByEmployeeNoAndState(no, state);
            log.info(list.toString());
            model.addAttribute("list", list);
        } else if(payment.equals("trip")) {
            List<BusinessTrip> list = businessTripService.selectByEmployeeNoAndState(no, state);
            log.info(list.toString());
            model.addAttribute("list", list);
        } else if(payment.equals("leave")) {
            List<Leave> list = leaveService.selectByEmployeeNoAndState(no, state);
            model.addAttribute("list", list);
        } else {
            List<BusinessCard> list = businessCardService.selectByEmployeeNoAndState(no, state);
            model.addAttribute("list", list);
        }

    }

    // 결재 완료 list
    @GetMapping("/complete")
    public void complete(Model model, @RequestParam(defaultValue = "vacation")String payment) {

        String no = "1";
        PaymentState state = PaymentState.승인;

        if(payment.equals("vacation")){
            List<Vacation> list = vacationService.selectByEmployeeNoAndState(no, state);
            log.info(list.toString());
            model.addAttribute("list", list);
        } else if(payment.equals("trip")) {
            List<BusinessTrip> list = businessTripService.selectByEmployeeNoAndState(no, state);
            log.info(list.toString());
            model.addAttribute("list", list);
        } else if(payment.equals("leave")) {
            List<Leave> list = leaveService.selectByEmployeeNoAndState(no, state);
            model.addAttribute("list", list);
        } else {
            List<BusinessCard> list = businessCardService.selectByEmployeeNoAndState(no, state);
            model.addAttribute("list", list);
        }

    }

    @GetMapping("/approver")
    public void approver() {

    }

    @GetMapping("/detail")
    public void detail(Model model,  @RequestParam Integer no) {

        BusinessCard card = businessCardService.selectByNo(no);
        model.addAttribute("card", card);
        log.info("card={}", card);

        Employee employee = employeeService.selectByNo(card.getEmployeeNo());
        model.addAttribute("employee", employee);
        log.info("employee={}", employee);

        Employee approver = employeeService.selectByNo(card.getApproverNo());
        model.addAttribute("approver", approver);


    }


}
