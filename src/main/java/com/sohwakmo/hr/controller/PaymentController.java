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

import java.security.Principal;
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

    // 기안 문서 list -- (하위 직책 : 사원, 과장)
    @GetMapping("/list")
    public void list(Model model, @RequestParam(defaultValue = "vacation")String payment, Principal principal) {

        String loginUserNo = principal.getName();

        switch (payment) {
            case "vacation" -> {
                model.addAttribute("list", vacationService.selectByEmployeeNo(loginUserNo));
                model.addAttribute("vacation", "vacation");
            }
            case "trip" -> {
                model.addAttribute("list", businessTripService.selectByEmployeeNo(loginUserNo));
                model.addAttribute("trip", "trip");
            }
            case "leave" -> {
                model.addAttribute("list", leaveService.selectByEmployeeNo(loginUserNo));
                model.addAttribute("leave", "leave");
            }
            default -> {
                model.addAttribute("list",  businessCardService.selectByEmployeeNo(loginUserNo));
                model.addAttribute("card", "card");
            }
        }

    }

    // 기안 문서 create
    @GetMapping("/create")
    public void create() {
    }

    // 결재 대기 list -- (하위 직책 : 사원, 과장)
    @GetMapping("/process")
    public void process(Model model, @RequestParam(defaultValue = "vacation")String payment, Principal principal) {

        String loginUserNo = principal.getName();
        PaymentState state = PaymentState.진행중;

        if(payment.equals("vacation")){
            List<Vacation> list = vacationService.selectByEmployeeNoAndState(loginUserNo, state);
            model.addAttribute("list", list);
            model.addAttribute("vacation", "vacation");
        } else if(payment.equals("trip")) {
            List<BusinessTrip> list = businessTripService.selectByEmployeeNoAndState(loginUserNo, state);
            model.addAttribute("list", list);
            model.addAttribute("trip", "trip");
        } else if(payment.equals("leave")) {
            List<Leave> list = leaveService.selectByEmployeeNoAndState(loginUserNo, state);
            model.addAttribute("list", list);
            model.addAttribute("leave", "leave");
        } else {
            List<BusinessCard> list = businessCardService.selectByEmployeeNoAndState(loginUserNo, state);
            model.addAttribute("list", list);
            model.addAttribute("card", "card");
        }

    }

    // 결재 완료 list -- (하위 직책 : 사원, 과장)
    @GetMapping("/complete")
    public void complete(Model model, @RequestParam(defaultValue = "vacation")String payment, Principal principal) {

        String loginUserNo = principal.getName();

        if(payment.equals("vacation")){
            List<Vacation> list = vacationService.selectByEmployeeNoAndStateOrState(loginUserNo);
            model.addAttribute("list", list);
            model.addAttribute("vacation", "vacation");

        } else if(payment.equals("trip")) {
            List<BusinessTrip> list = businessTripService.selectByEmployeeNoAndStateOrState(loginUserNo);
            model.addAttribute("list", list);
            model.addAttribute("trip", "trip");

        } else if(payment.equals("leave")) {
            List<Leave> list = leaveService.selectByEmployeeNoAndStateOrState(loginUserNo);
            model.addAttribute("list", list);
            model.addAttribute("leave", "leave");

        } else {
            List<BusinessCard> list = businessCardService.selectByEmployeeNoAndStateOrState(loginUserNo);
            model.addAttribute("list", list);
            model.addAttribute("card", "card");
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

        Vacation vacation = Vacation.builder()
                .employeeNo(dto.getEmployeeNo()).title(dto.getTitle()).reason(dto.getReason()).approverNo(dto.getApproverNo())
                .category(dto.getCategory()).effectiveDate(dto.getEffectiveDate()).expirationDate(dto.getExpirationDate())
                .build();

        Vacation vacations = vacationService.create(vacation);
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

        BusinessTrip businessTrip = BusinessTrip.builder()
                .employeeNo(dto.getEmployeeNo()).title(dto.getTitle()).reason(dto.getReason()).approverNo(dto.getApproverNo())
                .category(dto.getCategory()).effectiveDate(dto.getEffectiveDate()).expirationDate(dto.getExpirationDate())
                .place(dto.getPlace()).companionNO(dto.getCompanionNo()).build();


        BusinessTrip businessTrips = businessTripService.create(businessTrip);

    }

    // 출장(trip) detail
    @GetMapping("/businessTrip/detail")
    public void detailTrip(Model model, @RequestParam Integer no) {

        BusinessTrip trip = businessTripService.selectByNo(no);
        model.addAttribute("trip", trip);

        Employee employee = employeeService.selectByNo(trip.getEmployeeNo());
        model.addAttribute("employee", employee);

        Employee approver = employeeService.selectByNo(trip.getApproverNo());
        model.addAttribute("approver", approver);

        // 출장(trip)의 동반 출장자가 있을 경우
        if(employeeService.selectByNo(trip.getCompanionNO()) != null){
            Employee companion = employeeService.selectByNo(trip.getCompanionNO());
            model.addAttribute("companion", companion);

        // 출장(trip)의 동반 출장자가 없을 경우
        } else {
            Employee companion = null;
            model.addAttribute("companion", companion);
        }
    }

    // ----------- card -------------
    // 명함(bs card) create list
    @GetMapping("/businessCard/create")
    public  void createGetCard(){
    }

    // 명함(bs card) create
    @PostMapping ("/businessCard/create")
    public void createPostCard(BusinessCardCreateDto dto){

        BusinessCard businessCard = BusinessCard.builder()
                .employeeNo(dto.getEmployeeNo()).title(dto.getTitle()).reason(dto.getReason()).approverNo(dto.getApproverNo())
                .category(dto.getCategory()).build();

        BusinessCard businessCards = businessCardService.create(businessCard);

    }

    // 명함(bs card) detail
    @GetMapping("/businessCard/detail")
    public void detailCard(Model model, @RequestParam Integer no) {

        BusinessCard card = businessCardService.selectByNo(no);
        model.addAttribute("card", card);

        Employee employee = employeeService.selectByNo(card.getEmployeeNo());
        model.addAttribute("employee", employee);

        Employee approver = employeeService.selectByNo(card.getApproverNo());
        model.addAttribute("approver", approver);

    }

    // ----------- leave -------------
    // 퇴사(leave) create
    @GetMapping("/leave/create")
    public void createGetLeave(){
    }

    // 퇴사(leave) create
    @PostMapping("/leave/create")
    public void createPostLeave(LeaveCreateDto dto){

        Leave leave = Leave.builder()
                .employeeNo(dto.getEmployeeNo()).approverNo(dto.getApproverNo()).secondApproverNo(dto.getSecondApproverNo())
                .title(dto.getTitle()).reason(dto.getReason()).category(dto.getCategory())
                .effectiveDate(dto.getEffectiveDate())
                .build();
        Leave leaves = leaveService.create(leave);
    }

    // 퇴사(leave) dtail
    @GetMapping("/leave/detail")
    public void detailLeave(@RequestParam Integer no, Model model) {

            Leave leave = leaveService.selectByNo(no);
            model.addAttribute("leave", leave);

            Employee employee = employeeService.selectByNo(leave.getEmployeeNo());
            model.addAttribute("employee", employee);

            Employee approver = employeeService.selectByNo(leave.getApproverNo());
            model.addAttribute("approver", approver);

            Employee secondApprover = employeeService.selectByNo(leave.getSecondApproverNo());
            model.addAttribute("secondApprover", secondApprover);
    }

    // 결재 요청 list -- (상위 직책 : 차장, 팀장)
    @GetMapping("/request")
    public void request(Model model, @RequestParam(defaultValue = "vacation")String payment, Principal principal){

        String loginUserNo = principal.getName();
        PaymentState state = PaymentState.진행중;

        if(payment.equals("vacation")){
            List<Vacation> list = vacationService.selectByApproverNoAndState(loginUserNo, state);
            model.addAttribute("list", list);
            model.addAttribute("vacation", "vacation");

        } else if(payment.equals("trip")) {
            List<BusinessTrip> list = businessTripService.selectByApproverNoAndState(loginUserNo, state);
            model.addAttribute("list", list);
            model.addAttribute("trip", "trip");

        } else if(payment.equals("leave")) {
            List<Leave> list = leaveService.selectByApproverNoOrSecondNoAndState(loginUserNo);
            model.addAttribute("list", list);
            model.addAttribute("leave", "leave");

        } else {
            List<BusinessCard> list = businessCardService.selectByApproverNoAndState(loginUserNo, state);
            model.addAttribute("list", list);
            model.addAttribute("card", "card");
        }
    }

    // 결재 완료 list -- (상위 직책 : 차장, 팀장)
    @GetMapping("/response")
    public void response(Model model, @RequestParam(defaultValue = "vacation")String payment,  Principal principal){

        String loginUserNo = principal.getName();
        PaymentState state = PaymentState.승인;
        PaymentState state2 = PaymentState.반려;

        if(payment.equals("vacation")){
            List<Vacation> list = vacationService.selectByApproverNoAndStateOrState(loginUserNo, state, state2);
            model.addAttribute("list", list);
            model.addAttribute("vacation", "vacation");

        } else if(payment.equals("trip")) {
            List<BusinessTrip> list = businessTripService.selectByApproverNoAndStateOrState(loginUserNo, state, state2);
            model.addAttribute("list", list);
            model.addAttribute("trip", "trip");

        } else if(payment.equals("leave")) {
            List<Leave> list = leaveService.selectByApproverNoOrSecondApproverNoAndStateOrState(loginUserNo);
            model.addAttribute("list", list);
            model.addAttribute("leave", "leave");

        } else {
            List<BusinessCard> list = businessCardService.selectByApproverNoAndStateOrState(loginUserNo, state, state2);
            model.addAttribute("list", list);
            model.addAttribute("card", "card");
        }
    }


}
