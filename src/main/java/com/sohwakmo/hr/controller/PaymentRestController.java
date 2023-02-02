package com.sohwakmo.hr.controller;

import com.sohwakmo.hr.domain.Leave;
import com.sohwakmo.hr.service.LeaveService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/approver")
public class PaymentRestController {

    private final LeaveService leaveService;

    @GetMapping("/all")
    public void read() {
        log.info("rest 실행 시작");
    }

    @GetMapping("/readAll/{employeeNo}")
    public ResponseEntity<List<Leave>> readAll(@PathVariable Integer employeeNo) {

        log.info("컨틀뉴ㅓㅇ퓨ㅏ뉴ㅓㅇ");
        List<Leave> list = leaveService.selectByEmployeeNO(employeeNo);

        return ResponseEntity.ok(list);
    }

    @GetMapping("/compete/{leaveNo}")
    public ResponseEntity<Integer> compete(@PathVariable Integer leaveNo){

        log.info("수정하려는 PK={}", leaveNo);
        Integer result = leaveService.update(leaveNo);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/compete2/{leaveNo}")
    public ResponseEntity<Integer> compete2(@PathVariable Integer leaveNo){

        log.info("수정하려는 PK={}", leaveNo);
        Integer result = leaveService.update2(leaveNo);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/return/{leaveNo}")
    public ResponseEntity<Leave> leaveReturn (@PathVariable Integer leaveNo) {

        log.info("컨틀뉴ㅓㅇ퓨ㅏ뉴ㅓㅇ");
        Leave entity = leaveService.selectByNo(leaveNo);
        log.info("에이작스 ={}", entity);


        return ResponseEntity.ok(entity);
    }

    @PutMapping
    public ResponseEntity<Integer> updateReturnReason(@PathVariable Integer leaveNo, @RequestBody String returnReason){

        log.info("스트링? ={}", returnReason);
        Integer result = leaveService.updateReturn(leaveNo, returnReason);
        log.info("axios");

        return ResponseEntity.ok(result);

    }



}
