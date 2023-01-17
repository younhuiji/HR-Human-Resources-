package com.sohwakmo.hr.controller;

import com.sohwakmo.hr.domain.Part;
import com.sohwakmo.hr.dto.EmployeeJoinDto;
import com.sohwakmo.hr.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class EmployeeController {

    private EmployeeService employeeService;

    @GetMapping("/join")
    public String join(){
        return "/member/join";
    }

    @PostMapping("/join")
    public String join(EmployeeJoinDto joinDto, Part part){
        log.info("joinDto = {}", joinDto);
        log.info("part={}",part.toString());
        return "index";
    }
}
