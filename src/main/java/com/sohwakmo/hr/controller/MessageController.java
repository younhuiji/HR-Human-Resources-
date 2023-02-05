package com.sohwakmo.hr.controller;

import com.sohwakmo.hr.domain.Message;
import com.sohwakmo.hr.dto.MessageSearchDto;
import com.sohwakmo.hr.dto.MessageSendDto;
import com.sohwakmo.hr.service.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/message")
public class MessageController {

    private final MessageService messageService;

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
     * 쪽지 보내기 기능
     * @param dto
     * @param files
     * @return
     * @throws IOException
     */
    @PostMapping("/sendMessage")
    public String sendMessage(MessageSendDto dto, @RequestParam("files") List<MultipartFile> files) throws IOException {
        log.info("sendMessage(dto = {}, files = {})", dto, files);

        messageService.create(dto, files);

        return "redirect:/message/receiveList";
    }

    /**
     * 받은쪽지함으로 이동시키기
     * @param employeeNo
     * @param model
     * @return
     */
    @GetMapping("/receiveList")
    public String receiveList(String employeeNo, Model model, String messageType, String contentType, String keyword,
                              @PageableDefault(page = 0, size = 2, sort = "messageNo", direction = Sort.Direction.DESC) Pageable pageable) {
        log.info("receiveList(messageType = {}, contentType = {}, keyword = {})", messageType, contentType, keyword);

        // 로그인한 사원 번호 임시 값
        employeeNo = "2";
        log.info("employeeNo = {}", employeeNo);

        // 리스트로 바로 들어온 경우(검색하지 않은 경우)
        if(messageType == null && contentType == null && keyword == null) {
            log.info("검색하지 않은 경우");
            Page<Message> messageList = messageService.read(employeeNo, pageable);

            log.info("messageList = {}", messageList);
//            log.info("messageCount = {}", messageList.size());
            model.addAttribute("messageList", messageList);
//            model.addAttribute("messageCount", messageList.size());
            model.addAttribute("messageType", messageType);
            model.addAttribute("contentType", contentType);

        } else {
            log.info("검색한 경우");
            List<MessageSearchDto> messageList = messageService.searchMessage(employeeNo, messageType, contentType, keyword);

            log.info("messageList = {}", messageList);
            log.info("messageCount = {}", messageList.size());
            model.addAttribute("messageList", messageList);
            model.addAttribute("messageCount", messageList.size());
            model.addAttribute("messageType", messageType);
            model.addAttribute("contentType", contentType);
        }

        return "/message/receiveList";
    }


}
