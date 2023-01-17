package com.sohwakmo.hr.service;

import com.sohwakmo.hr.domain.Employee;
import com.sohwakmo.hr.domain.EmployeePosition;
import com.sohwakmo.hr.domain.Part;
import com.sohwakmo.hr.dto.EmployeeJoinDto;
import com.sohwakmo.hr.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static groovyjarjarantlr4.v4.gui.GraphicsSupport.saveImage;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public void join(EmployeeJoinDto joinDto, Part part, MultipartFile photo) throws Exception {
        // 입사일 날짜 포맷 변경 후 저장
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String joinedDate = simpleDateFormat.format(joinDto.getJoinedDate());
        Date convertJoinDate = new SimpleDateFormat("yyyy-MM-dd").parse(joinedDate);
        joinDto.setJoinedDate(convertJoinDate);
        // 사진 주소저장
        String photoPath = saveImage(photo);

        // 직책 기본값 설정.
        part.setPosition("사원");

        Employee employee = Employee.builder()
                .employeeNo(joinDto.getEmployeeNo())
                .password(joinDto.getPassword())
                .name(joinDto.getName())
                .phone(joinDto.getPhone())
                .email(joinDto.getEmail())
                .part(part)
                .photo("/employeeImage/"+photoPath)
                .joinedDate(joinDto.getJoinedDate())
                .build();
        employee.setEmployeePosition(EmployeePosition.LEVEL_1);
        log.info("employee={}", employee.toString());
        employeeRepository.save(employee);
    }

    private String saveImage(MultipartFile photo) throws IOException {
        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\images\\employeeImage";
        String fileName = photo.getOriginalFilename();
        File saveFile = new File(projectPath, fileName);
        photo.transferTo(saveFile);
        return fileName;
    }
}
