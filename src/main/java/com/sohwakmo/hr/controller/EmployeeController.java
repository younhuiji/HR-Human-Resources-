package com.sohwakmo.hr.controller;

import com.sohwakmo.hr.domain.Attendance;
import com.sohwakmo.hr.domain.Employee;
import com.sohwakmo.hr.domain.Part;
import com.sohwakmo.hr.dto.EmployeeJoinDto;
import com.sohwakmo.hr.dto.EmployeeUpdateDto;
import com.sohwakmo.hr.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
@Slf4j
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/login")
    public String login() {

        return "/employee/login";
    }


    @GetMapping("/join")
    public String join(){
        return "/employee/join";
    }

    @PostMapping("/join")
    public String join(EmployeeJoinDto joinDto, Part part, MultipartFile photo, @DateTimeFormat(pattern= "yyyy-MM-dd")Date joinedDate) throws Exception {
        log.info("joinDto = {}", joinDto);
        log.info("part={}",part.toString());
        log.info("photo={}",photo.getSize());
        employeeService.join(joinDto,part,photo,joinedDate);

        return "redirect:/";
    }

    @GetMapping("/myPage")
    public String myPage(Model model, String employeeNo){
        Employee employee = employeeService.findEmployee(employeeNo);

        List<Attendance> list = employeeService.getCurrentMonth(employee.getAttendances());
        model.addAttribute("employee", employee);
        model.addAttribute("startTimeDays", employeeService.setStartTimeDays(list)); // 출근시간 배열
        model.addAttribute("endTimeDays",employeeService.setEndTimeDays(list)); // 퇴근 시간 배열
        model.addAttribute("workState",employeeService.setWorkState(list)); // 출근 현황 O,X 세모인 배열
        return "/employee/myPage";
    }

    @PostMapping("/myPage/update")
    public String myPageUpdate(EmployeeUpdateDto dto,Part part) {
        log.info(dto.toString());
        log.info(part.toString());
        employeeService.update(dto, part);
        return "redirect:/myPage?employeeNo="+dto.getEmployeeNo();
     }
}
