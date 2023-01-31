package com.sohwakmo.hr.controller;

import com.sohwakmo.hr.dto.AttendanceDto;
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

    /**
     * 아이디 중복확인
     * @param employeeNoValue 회원가입페이지에서 전달받은
     * @return
     */
    @GetMapping("/checkNo")
    public ResponseEntity<String> checkEmployeeNo(String employeeNoValue) {
        log.info("employeeNoValue={}", employeeNoValue);
        // 아이디가 있는지 중복확인
        boolean doubleCheckResult = employeeService.employeeNoDoubleCheck(employeeNoValue);
        if(doubleCheckResult){
            return ResponseEntity.ok("employeeNoNotOk");
        }else{
            return ResponseEntity.ok("employeeNoOk");
        }
    }

    @GetMapping("/checkPhone")
    public ResponseEntity<String> checkPone(String phoneValue) {
        log.info("phoneValue={}", phoneValue);
        boolean doubleCheckPhoneValue = employeeService.phoneDoubleCheck(phoneValue);
        if (doubleCheckPhoneValue) return ResponseEntity.ok("phoneNotOk");
        else return ResponseEntity.ok("phoneOk");
    }

    @GetMapping("/checkEmail")
    public ResponseEntity<String> checkEmail(String emailValue) {
        log.info("email={}", emailValue);
        boolean doubleCheckResult = employeeService.emailDoubleCheck(emailValue);
        if (doubleCheckResult) return ResponseEntity.ok("emailNotOk");
        else return ResponseEntity.ok("emailOk");
    }

    /**
     * 업무 시작 시간 받아오기
     * @param dto 업무시작 시간,날짜 받아오기
     * @return 예상퇴근시간 리턴
     */
    @PostMapping("/attendance")
    public ResponseEntity<String> attendance(@RequestBody AttendanceDto dto){
        log.info(dto.toString());
        employeeService.startWork(dto);
        String endTime = employeeService.getEndTime(dto.getHours(), dto.getMinutes());
        return ResponseEntity.ok(endTime);
    }

}
