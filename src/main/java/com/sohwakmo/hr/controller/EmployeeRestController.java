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

    /**
     * 내선 전화 중복확인
     * @param phoneValue 회원가입창에서 입력 받은 내선 번호
     * @return
     */
    @GetMapping("/checkPhone")
    public ResponseEntity<String> checkPone(String phoneValue) {
        log.info("phoneValue={}", phoneValue);
        boolean doubleCheckPhoneValue = employeeService.phoneDoubleCheck(phoneValue);
        if (doubleCheckPhoneValue) return ResponseEntity.ok("phoneNotOk");
        else return ResponseEntity.ok("phoneOk");
    }

    /**
     * 사내 이메일 중복확인
     * @param emailValue 회원가입창에서 입력 받은 사내 이메일
     * @return
     */
    @GetMapping("/checkEmail")
    public ResponseEntity<String> checkEmail(String emailValue) {
        log.info("email={}", emailValue);
        boolean doubleCheckResult = employeeService.emailDoubleCheck(emailValue);
        if (doubleCheckResult) return ResponseEntity.ok("emailNotOk");
        else return ResponseEntity.ok("emailOk");
    }

    /**
     * 업무 시작 시간 저장 출근 저장
     * @param dto 업무시작 시간,날짜 받아오기
     */
    @PostMapping("/attendance")
    public void attendance(@RequestBody AttendanceDto dto){
        log.info(dto.toString());
        employeeService.startWork(dto);
    }

    /**
     * 업무 종료 버튼을 눌렀으므로 퇴근시간 저장하고 다시 업무시작 버튼을 못누르게
     * @param hours 업무 종료를 눌렀을때 받은 시간
     * @param minutes 업무 종료를 눌렀을때 받은 분
     */
    @GetMapping("/endWork")
    public void endWork(Integer hours, Integer minutes,String employeeNo) {
        employeeService.setEndTime(hours, minutes, employeeNo);
    }
}
