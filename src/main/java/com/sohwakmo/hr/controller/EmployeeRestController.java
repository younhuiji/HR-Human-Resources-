package com.sohwakmo.hr.controller;

import com.sohwakmo.hr.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class EmployeeRestController {
    private final EmployeeService employeeService;

    @PostMapping("/checkNo")
    public ResponseEntity<Integer> checkEmployeeNo(Integer employeeNoValue) {
        log.info("employeeNoValue={}", employeeNoValue);

        return ResponseEntity.ok(1);
    }
}
