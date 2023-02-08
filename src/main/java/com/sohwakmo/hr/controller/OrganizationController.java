package com.sohwakmo.hr.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequestMapping("/organization")
@Controller
public class OrganizationController {

    @GetMapping("/orgList")
    public void orgList(){
        log.info("orgList()");
    }

    @GetMapping("/calendar")
    public void calendar(String loginUser, Model model){
        log.info("calendar(loginUser={})",loginUser);

        model.addAttribute("loginUser",loginUser);
    }
}
