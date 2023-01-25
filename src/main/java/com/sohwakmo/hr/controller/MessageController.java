package com.sohwakmo.hr.controller;

import com.sohwakmo.hr.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/message")
public class MessageController {

    private final MessageService messageService;

    /**
     * 받은쪽지함으로 이동시키기
     * @return
     */
    @GetMapping("/receiveList")
    public String receiveList() {
        log.info("receiveList()");

        return "/message/receiveList";
    }

    /**
     * 쪽지 작성 페이지로 이동
     * @return
     */
    @GetMapping("/writeMessage")
    public String writeMessage() {
        log.info("writeMessage()");

        return "/message/writeMessage";
    }

    /**
     * 쪽지 보내고 받은쪽지함으로 이동.
     * @return
     */
    @PostMapping("/sendMessage")
    public String sendMessage() {
        log.info("sendMessage()");


        return "redirect:/message/receiveList";
    }

}
