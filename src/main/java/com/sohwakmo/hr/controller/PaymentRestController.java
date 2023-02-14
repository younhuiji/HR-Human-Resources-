package com.sohwakmo.hr.controller;

import com.sohwakmo.hr.domain.BusinessCard;
import com.sohwakmo.hr.domain.Leave;
import com.sohwakmo.hr.dto.updateDto;
import com.sohwakmo.hr.service.BusinessCardService;
import com.sohwakmo.hr.service.BusinessTripService;
import com.sohwakmo.hr.service.LeaveService;
import com.sohwakmo.hr.service.VacationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/payment")
public class PaymentRestController {

    private final LeaveService leaveService;
    private final BusinessCardService businessCardService;
    private final VacationService vacationService;
    private final BusinessTripService businessTripService;

    @GetMapping("/readAll/{employeeNo}")
    public ResponseEntity<List<Leave>> readAll(@PathVariable String employeeNo) {
        List<Leave> list = leaveService.selectByEmployeeNO(employeeNo);
        return ResponseEntity.ok(list);
    }


    // 퇴사(leave) 반려 modal
    @PutMapping ("/leave/return/{no}")
    public ResponseEntity<Integer> leaveReturnReason(@PathVariable Integer no, @RequestBody updateDto dto){
        Integer result = leaveService.updateReturn(no, dto.getReturnReason());
        return ResponseEntity.ok(result);
    }

    @PutMapping ("/businessCard/return/{no}")
    public ResponseEntity<Integer> businessCardReturnReason(@PathVariable Integer no, @RequestBody updateDto dto){
        Integer result = businessCardService.updateReturn(no, dto.getReturnReason());
        return ResponseEntity.ok(result);
    }

    @PutMapping ("/businessTrip/return/{no}")
    public ResponseEntity<Integer> businessTripReturnReason(@PathVariable Integer no, @RequestBody updateDto dto){
        Integer result = businessTripService.updateReturn(no, dto.getReturnReason());
        return ResponseEntity.ok(result);
    }

    @PutMapping ("/vacation/return/{no}")
    public ResponseEntity<Integer> vacationReturnReason(@PathVariable Integer no, @RequestBody updateDto dto){
        Integer result = vacationService.updateReturn(no, dto.getReturnReason());
        return ResponseEntity.ok(result);
    }

    // bs card list 출력
    @GetMapping("/cardList/{employeeNo}")
    public ResponseEntity<List<BusinessCard>> cardList (@PathVariable String employeeNo){
        List<BusinessCard> list = businessCardService.selectByEmployeeNo(employeeNo);
        log.info("명함 컨트롤 ={}", list);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/card/complete/{no}")
    public ResponseEntity<Integer> completeCard(@PathVariable Integer no){
        Integer result = businessCardService.update(no);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/vacation/complete/{no}")
    public ResponseEntity<Integer> completeVacation(@PathVariable Integer no){
        Integer result = vacationService.update(no);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/trip/complete/{no}")
    public ResponseEntity<Integer> completeTrip(@PathVariable Integer no){
        Integer result = businessTripService.update(no);
        return ResponseEntity.ok(result);
    }

    // 퇴사 (leave) 1차 승인
    @GetMapping("/leave/complete/{leaveNo}")
    public ResponseEntity<Integer> complete(@PathVariable Integer leaveNo){
        Integer result = leaveService.update(leaveNo);
        return ResponseEntity.ok(result);
    }

    // 퇴사 (leave) 2차 승인
    @GetMapping("/leave/complete2/{leaveNo}")
    public ResponseEntity<Integer> complete2(@PathVariable Integer leaveNo){
        Integer result = leaveService.update2(leaveNo);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/vacation/delete/{no}")
    public ResponseEntity<Integer> deleteVacation(@PathVariable Integer no){

        // 승인 처리 안됐을 경우, 삭제 가능
        if(vacationService.selectByNo(no).getCompleteDate() == null) {
            Integer result = vacationService.delete(no);
            return ResponseEntity.ok(result);

        // 승인 처리가 진행된 경우, 삭제 불가능
        } else {
            Integer result = 0;
            return ResponseEntity.ok(result);
        }
    }

    @DeleteMapping("/businessCard/delete/{no}")
    public ResponseEntity<Integer> deleteBusinessCard(@PathVariable Integer no){

        // 승인 처리 안됐을 경우, 삭제 가능
        if(businessCardService.selectByNo(no).getCompleteDate() == null) {
            Integer result = businessCardService.delete(no);
            return ResponseEntity.ok(result);

            // 승인 처리가 진행된 경우, 삭제 불가능
        } else {
            Integer result = 0;
            return ResponseEntity.ok(result);
        }
    }

    @DeleteMapping("/businessTrip/delete/{no}")
    public ResponseEntity<Integer> deleteBusinessTrip(@PathVariable Integer no){

        // 승인 처리 안됐을 경우, 삭제 가능
        if(businessTripService.selectByNo(no).getCompleteDate() == null) {
            Integer result = businessTripService.delete(no);
            return ResponseEntity.ok(result);

            // 승인 처리가 진행된 경우, 삭제 불가능
        } else {
            Integer result = 0;
            return ResponseEntity.ok(result);
        }
    }

    @DeleteMapping("/leave/delete/{no}")
    public ResponseEntity<Integer> deleteLeave(@PathVariable Integer no){

        // 승인 처리 안됐을 경우, 삭제 가능
        if(leaveService.selectByNo(no).getSemiCompleteDate() == null) {
            Integer result = leaveService.delete(no);
            return ResponseEntity.ok(result);

            // 승인 처리가 진행된 경우, 삭제 불가능
        } else {
            Integer result = 0;
            return ResponseEntity.ok(result);
        }
    }


}
