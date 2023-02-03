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
        // 오늘 날짜를 구하는 메서드
        String formatedNow = getToday();
        // DB에 저장되어 있는 최근 출근기록을 체크후 id값 출력
        Long attendanceNo = employeeService.checkAttendance(employeeNo,formatedNow);
        // 현재 시간을 구하는 메서드
        String formatedTime = getNowTime();

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

    private String getNowTime() {
        // 현재 시간
        LocalTime now = LocalTime.now();
        // 포맷 정의하기
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        return now.format(timeFormatter);
    }

    private String getToday() {
        // 현재 날짜 구하기
        LocalDate nowDate = LocalDate.now();
        // 포맷 정의
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd");
        return nowDate.format(dateFormatter);
    }
}
