package com.sohwakmo.hr.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/approver")
public class PaymentRestController {

    @GetMapping("/all")
    public void readAllReplies() {
        log.info("rest 실행 시작");
    }


}
