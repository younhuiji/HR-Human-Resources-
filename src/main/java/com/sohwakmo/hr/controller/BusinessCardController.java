package com.sohwakmo.hr.controller;

import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/businessCard")
public class BusinessCardController {

    @GetMapping("/create")
    public String create(){
        return "/businessCard/create";
    }

}
