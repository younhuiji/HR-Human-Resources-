package com.sohwakmo.hr.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/message")
public class MessageController {

    @GetMapping("/receiveList")
    public String receiveList() {
        log.info("receiveList()");

        return "/message/receiveList";
    }

    @GetMapping("/writeMessage")
    public String writeMessage() {
        log.info("writeMessage()");

        return "/message/writeMessage";
    }

}
