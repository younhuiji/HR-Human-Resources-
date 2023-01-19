package com.sohwakmo.hr.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/meetingRoom")
public class MeetingRoomController {

    @GetMapping("list")
    public String list() {
        log.info("list()");

        return "/meetingRoom/list";
    }

    @GetMapping("myReserve")
    public String myReserve() {
        log.info("myReserve()");

        return "/meetingRoom/myReserve";
    }

    @GetMapping("create")
    public String create() {
        log.info("create()");

        return "/meetingRoom/create";
    }

//    @PostMapping("/create")
//    public String create() {
//        log.info("create() post");
//    }

}
