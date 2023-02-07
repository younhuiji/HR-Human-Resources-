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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
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
    public String join(EmployeeJoinDto joinDto, Part part, MultipartFile photo,@DateTimeFormat(pattern= "yyyy-MM-dd")Date joinedDate) throws Exception {
        log.info("joinDto = {}", joinDto);
        log.info("part={}",part.toString());
        log.info("photo={}",photo.getSize());
        employeeService.join(joinDto,part,photo,joinedDate);

        return "redirect:/";
    }

    @GetMapping("/findPw")
    public String findPw(){
        return "/employee/findPw";
    }

    @PostMapping("/getPw")
    public String getEmployeeNo(String employeeNo, Model model,RedirectAttributes attrs){
        String result = employeeService.checkEmployeeNo(employeeNo);
        if (result.equals("yes")) {
            model.addAttribute("employeeNo", employeeNo);
            return "/employee/setPw";
        }else{
            model.addAttribute("result", result);
            return "/employee/findPw";
        }
    }

    @PostMapping("/setPw")
    public String setPw(String password,String employeeNo) {
        employeeService.resetPassword(password,employeeNo);
        return "redirect:/login";
    }

    @GetMapping("/myPage")
    public String myPage(Model model, String employeeNo, @RequestParam(required = false,defaultValue = "")String month){
        Employee employee = employeeService.findEmployee(employeeNo);

        log.info("month={}", month);
        // 검색한 달이 있는경우의 list 와 없는 경우의 list를 나눈다.
        List<Attendance> list = new ArrayList<>();
        if(month.equals("")){
            list = employeeService.getCurrentMonth(employee.getAttendances());
            setAttendanceList(list,model);
        }else{
            list = employeeService.getSearchMonth(month);
            if (list.size() == 0){
                model.addAttribute("searchResult", 0);
                model.addAttribute("monthLength", setMonthLength(month));
            }
            else setAttendanceList(list, model);
        }
        model.addAttribute("employee", employee);
        return "/employee/myPage";
    }

    /**
     * 검색된 달에 검색 조건이 없을때 검색 조건이 없다는 페이지를 위하여 colspan을 하기위한 길이를 리턴
     * @param month 검색 받은 달
     * @return 검색받은 달의 길이를 리턴
     */
    private int setMonthLength(String month) {
        if(month.equals("02")) return 28;
        else if (month.equals("01")||month.equals("03")||month.equals("05")||month.equals("07")||month.equals("08")
            ||month.equals("10")||month.equals("12"))return 31;
        else return 30;
    }

    private void setAttendanceList(List<Attendance> list, Model model) {
        model.addAttribute("startTimeDays", employeeService.setStartTimeDays(list)); // 출근시간 배열
        model.addAttribute("endTimeDays",employeeService.setEndTimeDays(list)); // 퇴근 시간 배열
        model.addAttribute("workState",employeeService.setWorkState(list)); // 출근 현황 O,X 세모인 배열
    }

    @PostMapping("/myPage/update")
    public String myPageUpdate(EmployeeUpdateDto dto, Part part) {

        log.info(dto.toString());
        log.info(part.toString());
        employeeService.update(dto, part);
        return "redirect:/myPage?employeeNo=" + dto.getEmployeeNo();
    }
}
