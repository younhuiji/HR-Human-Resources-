package com.sohwakmo.hr.controller;

import com.sohwakmo.hr.domain.Part;
import com.sohwakmo.hr.dto.EmployeeJoinDto;
import com.sohwakmo.hr.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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

//    @PreAuthorize("isAuthenticated()")
    @GetMapping("/join")
    public String join(){
        return "/employee/join";
    }

    @PostMapping("/join")
    public String join(EmployeeJoinDto joinDto, Part part, MultipartFile photo) throws Exception {
        log.info("joinDto = {}", joinDto);
        log.info("part={}",part.toString());
        log.info("photo={}",photo.getSize());
        employeeService.join(joinDto,part,photo);

        return "redirect:/";
    }
}
