package com.sohwakmo.hr.controller;

import com.sohwakmo.hr.domain.*;
import com.sohwakmo.hr.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {
    private final EmployeeService employeeService;
    private final PostService postService;
    private final VacationService vacationService;
    private final BusinessTripService businessTripService;
    private final BusinessCardService businessCardService;
    private final LeaveService leaveService;
    private final MeetingRoomService meetingRoomService;

    @GetMapping("/")
    @PreAuthorize("isAuthenticated()")
    public String index(Model model, Principal principal,@RequestParam(defaultValue = "vacation")String payment){
        String employeeNo = principal.getName();
        // 오늘 날짜를 구하는 메서드
        String formatedNow = getToday();
        // 현재 시간을 구하는 메서드
        String formatedTime = getNowTime();
        // DB에 저장되어 있는 최근 출근기록을 체크후 id값 출력
        Long attendanceNo = employeeService.checkAttendance(employeeNo,formatedNow);


        if (attendanceNo != -1L) {
            Attendance attendance = employeeService.getAttendance(attendanceNo);
            String workingTime = employeeService.countWorkingTime(attendance.getStartTime(),formatedTime);
            model.addAttribute("attendance", attendance);
            model.addAttribute("workingTime", workingTime);
            model.addAttribute("postList", getPostList());
            setModelDoc(payment, employeeNo,model);
            setSchedule(employeeNo, formatedNow,model);

        }else{
            model.addAttribute("attendance","notAttendance");
            model.addAttribute("postList", getPostList());
            setModelDoc(payment, employeeNo,model);
            setSchedule(employeeNo, formatedNow,model);
        }
        return "/home";
    }

    private void setSchedule(String employeeNo, String formatedNow, Model model) {
        model.addAttribute("businessTripList", businessTripService.getTodayBusinessTripList(employeeNo,formatedNow));
        model.addAttribute("vacationList", vacationService.getTodayVacationList(employeeNo, formatedNow));
        model.addAttribute("reservationList", meetingRoomService.getTodayReservation(employeeNo, formatedNow));
        model.addAttribute("todayMonth", formatedNow.substring(0, 2));
        model.addAttribute("todayDate", formatedNow.substring(3));
    }

    /**
     * 카테고리 별 문서결제 서류 리스트 불러오기
     * @param payment 카테고리 설정
     * @param employeeNo 사원번호
     * @param model
     */
    private void setModelDoc(String payment, String employeeNo,Model model) {
        switch (payment) {
            case "vacation" -> {
                model.addAttribute("docList", vacationService.getVacationListSeven(employeeNo));
                model.addAttribute("vacation", "vacation");
            }
            case "trip" -> {
                model.addAttribute("docList", businessTripService.getBusinessTripSeven(employeeNo));
                model.addAttribute("trip", "trip");
            }
            case "leave" -> {
                model.addAttribute("docList", leaveService.selectByEmployeeNo(employeeNo));
                model.addAttribute("leave", "leave");
            }
            default -> {
                model.addAttribute("docList",  businessCardService.getBusinessCardSeven(employeeNo));
                model.addAttribute("card", "card");
            }
        }
    }

    /**
     * 게시판 리스트를 가져오는 메서드
     * @return 모든 게시핀을 가져와서 리턴.
     */
    private List<Post> getPostList() {return postService.readPostSeven();}

    private String getNowTime() {
        // 현재 시간
        LocalTime now = LocalTime.now();
        // 포맷 정의하기
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        return now.format(timeFormatter);
    }

    private String getToday() {
        // 현재 날짜 구하기
        LocalDate nowDate = LocalDate.now();
        // 포맷 정의
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd");
        return nowDate.format(dateFormatter);
    }
}
