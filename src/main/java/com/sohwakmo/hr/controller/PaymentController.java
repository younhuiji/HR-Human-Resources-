package com.sohwakmo.hr.controller;

import com.sohwakmo.hr.domain.*;
import com.sohwakmo.hr.dto.BusinessCardCreateDto;
import com.sohwakmo.hr.dto.BusinessTripCreateDto;
import com.sohwakmo.hr.dto.LeaveCreateDto;
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
            log.info("명함 = {}", list);
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

    // -------------- vacation ----------------
    @GetMapping("/approver")
    public void approver() {
    }

    // 휴가(vacation) list
    @GetMapping("/vacation/create")
    public void createGetVacation(){
    }

    // 휴가(vacation) create
    @PostMapping("/vacation/create")
    public void createPostVacation(VacationCreateDto dto){

        String no = "1";

        Vacation vacation = Vacation.builder()
                .employeeNo(no).approverNo(no).title(dto.getTitle()).reason(dto.getReason())
                .category(dto.getCategory()).effectiveDate(dto.getEffectiveDate()).expirationDate(dto.getExpirationDate())
                .build();

        Vacation vacations = vacationService.create(vacation);
        log.info("vacation={}", vacations);

    }

    // 휴가(vacation) detail
    @GetMapping("/vacation/detail")
    public void detailVacation(Model model, @RequestParam Integer no) {

        Vacation vacation = vacationService.selectByNo(no);
        model.addAttribute("vacation", vacation);
        log.info("vacation={}", vacation);

        Employee employee = employeeService.selectByNo(vacation.getEmployeeNo());
        model.addAttribute("employee", employee);
        log.info("employee={}", employee);

        Employee approver = employeeService.selectByNo(vacation.getApproverNo());
        model.addAttribute("approver", approver);

    }

    // ------------ trip --------------
    // 출장(trip) get create
    @GetMapping("/businessTrip/create")
    public void createGetTrip(){
    }

    // 출장(trip) post create
    @PostMapping("/businessTrip/create")
    public void createPostTrip(BusinessTripCreateDto dto){

        String no = "1";

        BusinessTrip businessTrip = BusinessTrip.builder()
                .employeeNo(no).approverNo(no).title(dto.getTitle()).reason(dto.getReason())
                .category(dto.getCategory()).effectiveDate(dto.getEffectiveDate()).expirationDate(dto.getExpirationDate())
                .place(dto.getPlace()).companionNO(no).build();


        BusinessTrip businessTrips = businessTripService.create(businessTrip);

    }

    // ----------- card -------------
    // 명함(bs card) create list
    @GetMapping("/businessCard/create")
    public  void createGetCard(Model model){

        String category = "명함";
        List<BusinessCard> list = businessCardService.selectByCategory(category);
        model.addAttribute("card", list);

    }

    // 명함(bs card) create
    @PostMapping ("/businessCard/create")
    public void createPostCard(BusinessCardCreateDto dto){

        // TODO: employee에서 시행자 이름, 사번, 결재자 사번, 직급, 직책 가져오기
        String no = "1";

        BusinessCard businessCard = BusinessCard.builder()
                .employeeNo(no).approverNo(no).title(dto.getTitle()).reason(dto.getReason())
                .category(dto.getCategory()).build();

        BusinessCard businessCards = businessCardService.create(businessCard);

    }

    // 명함(bs card) detail
    @GetMapping("/businessCard/detail")
    public void detailCard(Model model, @RequestParam Integer no) {

        BusinessCard card = businessCardService.selectByNo(no);
        model.addAttribute("card", card);
        log.info("card={}", card);

        Employee employee = employeeService.selectByNo(card.getEmployeeNo());
        model.addAttribute("employee", employee);
        log.info("employee={}", employee);

        Employee approver = employeeService.selectByNo(card.getApproverNo());
        model.addAttribute("approver", approver);

    }

    // ----------- leave -------------
    // 퇴사(list) create
    @GetMapping("/leave/create")
    public void createGetLeave(Model model){

        // TODO: 임원 테이블 연결 시에 수정하기
        String no = "1";
        List<Leave> leave = leaveService.selectByEmployeeNO(no);
        model.addAttribute("leave", leave);

    }

    // 퇴사(list) create
    @PostMapping("/leave/create")
    public void createPostLeave(LeaveCreateDto dto){

        String no = "1";

        Leave leave = Leave.builder()
                .employeeNo(no).approverNo(no).secondApproverNO(no)
                .title(dto.getTitle()).reason(dto.getReason()).category(dto.getCategory())
                .effectiveDate(dto.getEffectiveDate())
                .build();
        Leave leaves = leaveService.create(leave);
    }

    // 퇴사(list) dtail
    @GetMapping("/leave/detail")
    public void detailLeave(@RequestParam Integer no, Model model) {

        Leave leave = leaveService.selectByNo(no);
        Employee employee = employeeService.selectByNo(leave.getEmployeeNo());

        model.addAttribute("leave",leave);
        model.addAttribute("employee", employee);

    }

    @GetMapping("/leave/sample")
    public void sample(){
    }



}
