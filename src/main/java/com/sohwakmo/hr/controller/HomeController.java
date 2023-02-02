package com.sohwakmo.hr.controller;

import com.sohwakmo.hr.domain.Attendance;
import com.sohwakmo.hr.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {
    private final EmployeeService employeeService;

    @GetMapping("/")
    @PreAuthorize("isAuthenticated()")
    public String index(Model model, Principal principal){
        String employeeNo = principal.getName();
        // 현재 날짜 구하기
        LocalDate now = LocalDate.now();
        // 포맷 정의
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd");
        // 포맷 적용
        String formatedNow = now.format(formatter);
        Long attendanceNo = employeeService.checkAttendance(employeeNo,formatedNow);

        if (attendanceNo != -1L) {
            Attendance attendance = employeeService.getAttendance(attendanceNo);
            model.addAttribute("attendance", attendance);
        }else{
            model.addAttribute("attendance","notAttendance");
        }
        return "/home";
    }
}
