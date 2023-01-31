package com.sohwakmo.hr.controller;

import com.sohwakmo.hr.domain.Employee;
import com.sohwakmo.hr.domain.MeetingRoom;
import com.sohwakmo.hr.dto.MeetingRoomCreateDto;
import com.sohwakmo.hr.dto.MeetingRoomUpdateDto;
import com.sohwakmo.hr.service.EmployeeService;
import com.sohwakmo.hr.service.MeetingRoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/meetingRoom")
public class MeetingRoomController {

    private final MeetingRoomService meetingRoomService;

    private final EmployeeService employeeService;

    @GetMapping("/list")
    public String list(Model model) {
        log.info("list()");

        List<MeetingRoom> list = meetingRoomService.read();

        model.addAttribute("list", list);

        return "/meetingRoom/list";
    }

//    @GetMapping("myReserve")
//    public String myReserve() {
//        log.info("myReserve()");
//
//        return "/meetingRoom/myReserve";
//    }

    @GetMapping("/create")
    public String create() {
        log.info("create()");

        return "/meetingRoom/create";
    }

    @PostMapping("/create")
    public String create(MeetingRoomCreateDto dto, RedirectAttributes attrs)  {
        log.info("create(dto={})", dto);

        MeetingRoom entity = meetingRoomService.create(dto);



        attrs.addFlashAttribute("createdNo", entity.getMeetingRoomNo());

        return "redirect:/meetingRoom/list";
    }

    // 예약 상세
    @GetMapping("/detail")
    public String detail(Integer meetingRoomNo, Model model) {
        log.info("detail(meetingRoomNo={})", meetingRoomNo);

        MeetingRoom meetingRoom = meetingRoomService.read(meetingRoomNo);

        model.addAttribute("meetingRoom", meetingRoom);
        model.addAttribute("employee", meetingRoom.getEmployee());

        return "/meetingRoom/detail";
    }

    @GetMapping("/modify")
    public String modify(Integer meetingRoomNo, Model model) {
        log.info("modify(meetingRoomNo={})", meetingRoomNo);

        MeetingRoom meetingRoom = meetingRoomService.read(meetingRoomNo);
        model.addAttribute("meetingRoom", meetingRoom);

        return "/meetingRoom/modify";
    }

    // 예약 수정
    @PostMapping("/update")
    public String update(MeetingRoomUpdateDto dto) {
        log.info("update(dto={})", dto);

        Integer meetingRoomNo = meetingRoomService.update(dto);

        return "redirect:/meetingRoom/detail?meetingRoomNo=" + dto.getMeetingRoomNo();
    }


    @PostMapping("/delete")
    public String delete(Integer meetingRoomNo) {
        log.info("delete(meetingRoomNo={})", meetingRoomNo);

        meetingRoomService.delete(meetingRoomNo);

        return "redirect:/meetingRoom/list";
    }
}

