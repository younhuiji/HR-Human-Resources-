package com.sohwakmo.hr.controller;

import com.sohwakmo.hr.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
public class EmployeeRestController {
    private final EmployeeService employeeService;

    @GetMapping("/checkNo")
    public ResponseEntity<String> checkEmployeeNo(Integer employeeNoValue) {
        log.info("employeeNoValue={}", employeeNoValue);
        // 아이디가 있는지 중복확인
        boolean doubleCheckResult = employeeService.doubleCheck(employeeNoValue);
        if(doubleCheckResult){
            return ResponseEntity.ok("employeeNoNotOk");
        }else{
            return ResponseEntity.ok("employeeNoOk");
        }
    }
}
