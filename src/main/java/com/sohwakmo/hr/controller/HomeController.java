package com.sohwakmo.hr.controller;

import com.sohwakmo.hr.domain.BusinessCard;
import com.sohwakmo.hr.service.BusinessCardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
public class HomeController {

    private final BusinessCardService businessCardService;

    @GetMapping("/")
    public String index() {
        log.info("index()");
        return "index";
    }


}
