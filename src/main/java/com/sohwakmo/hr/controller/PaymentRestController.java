package com.sohwakmo.hr.controller;

import com.sohwakmo.hr.domain.BusinessCard;
import com.sohwakmo.hr.domain.Leave;
import com.sohwakmo.hr.dto.LeaveUpdateDto;
import com.sohwakmo.hr.service.BusinessCardService;
import com.sohwakmo.hr.service.LeaveService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/payment")
public class PaymentRestController {

    private final LeaveService leaveService;
    private final BusinessCardService businessCardService;

    @GetMapping("/readAll/{employeeNo}")
    public ResponseEntity<List<Leave>> readAll(@PathVariable String employeeNo) {
        List<Leave> list = leaveService.selectByEmployeeNO(employeeNo);
        return ResponseEntity.ok(list);
    }

    // 퇴사 (leave) 1차 승인
    @GetMapping("/leave/compete/{leaveNo}")
    public ResponseEntity<Integer> compete(@PathVariable Integer leaveNo){
        Integer result = leaveService.update(leaveNo);
        return ResponseEntity.ok(result);
    }

    // 퇴사 (leave) 2차 승인
    @GetMapping("/leave/compete2/{leaveNo}")
    public ResponseEntity<Integer> compete2(@PathVariable Integer leaveNo){
        Integer result = leaveService.update2(leaveNo);
        return ResponseEntity.ok(result);
    }

    // 퇴사(leave) 반려 modal
    @GetMapping("/leave/return/{leaveNo}")
    public ResponseEntity<Leave> leaveReturn (@PathVariable Integer leaveNo) {
        Leave entity = leaveService.selectByNo(leaveNo);
        return ResponseEntity.ok(entity);
    }

    // 퇴사(leave) 반려 신청
    @PutMapping("/leave/{leaveNo}")
    public ResponseEntity<Integer> updateReturnReason(@PathVariable Integer leaveNo, @RequestBody LeaveUpdateDto dto){
        Integer result = leaveService.updateReturn(leaveNo, dto.getReturnReason());
        return ResponseEntity.ok(result);

    }

    // bs card list 출력
    @GetMapping("/cardList/{employeeNo}")
    public ResponseEntity<List<BusinessCard>> cardList (@PathVariable String employeeNo){
        List<BusinessCard> list = businessCardService.selectByEmployeeNo(employeeNo);
        log.info("명함 컨트롤 ={}", list);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/card/compete/{cardNo}")
    public ResponseEntity<Integer> competeCard(@PathVariable Integer cardNo){
        Integer result = businessCardService.update(cardNo);
        return ResponseEntity.ok(result);
    }

}
