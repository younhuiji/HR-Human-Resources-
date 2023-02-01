package com.sohwakmo.hr.controller;

import com.sohwakmo.hr.service.LeaveService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/approver")
public class PaymentRestController {

    private final LeaveService leaveService;

    @GetMapping("/all")
    public void readAllReplies() {
        log.info("rest 실행 시작");
    }

    @GetMapping("/compete/{leaveNo}")
    public ResponseEntity<Integer> compete(@PathVariable Integer leaveNo){

        log.info("수정하려는 PK={}", leaveNo);
        Integer result = leaveService.update(leaveNo);
        return ResponseEntity.ok(result);
    }

}
