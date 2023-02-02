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
import java.time.LocalTime;
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
        LocalDate nowDate = LocalDate.now();

        // 포맷 정의
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd");
        // 포맷 적용
        String formatedNow = nowDate.format(dateFormatter);

        Long attendanceNo = employeeService.checkAttendance(employeeNo,formatedNow);

        // 현재 시간
        LocalTime now = LocalTime.now();

        // 포맷 정의하기
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        // 포맷 적용하기
        String formatedTime = now.format(timeFormatter);


        if (attendanceNo != -1L) {
            Attendance attendance = employeeService.getAttendance(attendanceNo);
            String workingTime = employeeService.countWorkingTime(attendance.getStartTime(),formatedTime);
            model.addAttribute("attendance", attendance);
            model.addAttribute("workingTime", workingTime);
        }else{
            model.addAttribute("attendance","notAttendance");
        }
        return "/home";
    }
}
