package com.sohwakmo.hr.controller;

import com.sohwakmo.hr.domain.Attendance;
import com.sohwakmo.hr.domain.Employee;
import com.sohwakmo.hr.domain.Part;
import com.sohwakmo.hr.dto.EmployeeJoinDto;
import com.sohwakmo.hr.dto.EmployeeUpdateDto;
import com.sohwakmo.hr.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Date;
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


    @GetMapping("/join")
    public String join(){
        return "/employee/join";
    }

    @PostMapping("/join")
    public String join(EmployeeJoinDto joinDto, Part part, MultipartFile photo, @DateTimeFormat(pattern= "yyyy-MM-dd")Date joinedDate) throws Exception {
        log.info("joinDto = {}", joinDto);
        log.info("part={}",part.toString());
        log.info("photo={}",photo.getSize());
        employeeService.join(joinDto,part,photo,joinedDate);

        return "redirect:/";
    }

    @GetMapping("/myPage")
    public String myPage(Model model, String employeeNo){
        Employee employee = employeeService.findEmployee(employeeNo);
        List<Attendance> list = employee.getAttendances();
        Attendance attendance = list.get(list.size()-1);
        int month = Integer.parseInt(attendance.getMonth());
        // 2월인경우
        if (month==2) {
            String[] startTimeDays = new String[28];
            setTimes(startTimeDays, list);
            System.out.println(Arrays.toString(startTimeDays));
            model.addAttribute("startTimeDays", startTimeDays);
        } else if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) { // 31일까지 있는 달
            String[] startTimeDays = new String[31];
            setTimes(startTimeDays, list);
            System.out.println(Arrays.toString(startTimeDays));
            model.addAttribute("startTimeDays", startTimeDays);
        }else{ // 그외 30일 까지 있는달
            String[] startTimeDays = new String[30];
            setTimes(startTimeDays, list);
            System.out.println(Arrays.toString(startTimeDays));
            model.addAttribute("startTimeDays", startTimeDays);
        }
        model.addAttribute("list", list);
        model.addAttribute("employee", employee);
        return "/employee/myPage";
    }

    /**
     * 배열을 넘길때 비어있는 부분은 채우고 들어가야할 부붐은 채우는 메서드
     * @param startTimeDays 채워서 보낼 배열
     * @param list 그달에 있는 출결 리스트
     */
    private void setTimes(String[]startTimeDays, List<Attendance> list) {
        for (Attendance a : list) {
            int day = Integer.parseInt(a.getDay());
            startTimeDays[day-1] = a.getStartTime();
        }
        // 빈칸에 "-" 로 채우기
        for (int i = 0; i < startTimeDays.length; i++) {
            if (startTimeDays[i] == null) startTimeDays[i] = "-";
        }
    }

    @PostMapping("/myPage/update")
    public String myPageUpdate(EmployeeUpdateDto dto,Part part) {
        log.info(dto.toString());
        log.info(part.toString());
        employeeService.update(dto, part);
        return "redirect:/myPage?employeeNo="+dto.getEmployeeNo();
     }
}
