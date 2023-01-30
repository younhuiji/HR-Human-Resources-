package com.sohwakmo.hr.service;

import com.sohwakmo.hr.domain.Employee;
import com.sohwakmo.hr.domain.EmployeePosition;
import com.sohwakmo.hr.domain.Part;
import com.sohwakmo.hr.dto.EmployeeJoinDto;
import com.sohwakmo.hr.dto.EmployeeUpdateDto;
import com.sohwakmo.hr.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 회원가입
     *
     * @param joinDto
     * @param part    사원 부서, 팀, 맡은일 컬럼만 모아놓음
     * @param photo   사원이미지
     * @throws Exception
     */
    public void join(EmployeeJoinDto joinDto, Part part, MultipartFile photo,Date joinedDate) throws Exception {
        // 입사일 날짜 포맷 변경 후 저장
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String joinedDateToString = simpleDateFormat.format(joinedDate);
        log.info("joinDate = {}", joinedDateToString);

        // 사진 주소저장
        String photoPath;
        if (photo.getSize() != 0) {
            photoPath = saveImage(photo);
        }else {
            photoPath = "사진미정";
        }
        // 사내번호 문자열 처리하기
        String companyPhone = joinDto.getPhone();
        companyPhone = joinDto.getPhone().replaceAll("-", "");

        // 비밀번호 암호화
        joinDto.setPassword(passwordEncoder.encode(joinDto.getPassword()));

        // 설정을 완료하고 사원 객체로 변환후 저장.
        Employee employee = Employee.builder()
                .employeeNo(joinDto.getEmployeeNo())
                .password(joinDto.getPassword())
                .name(joinDto.getName())
                .phone(companyPhone)
                .email(joinDto.getEmail())
                .part(part)
                .photo("/images/employeeImage/" + photoPath)
                .joinedDate(joinedDateToString)
                .build();
        employee.addRole(EmployeePosition.LEVEL_1);
        log.info("employee={}", employee.toString());
        employeeRepository.save(employee);
    }

    /**
     * 회원가입시 사원 이미지를 받고 저장.
     *
     * @param photo input type file로 받은 이미지를 저장
     * @return 파일경로
     * @throws IOException
     */
    private String saveImage(MultipartFile photo) throws IOException {
        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\images\\employeeImage";
        String fileName = photo.getOriginalFilename();
        File saveFile = new File(projectPath, fileName);
        photo.transferTo(saveFile);
        return fileName;
    }

    /**
     * 사원번호가 데이터베이스에 존재하는지 확인
     * @param employeeNoValue 회원가입 페이지에서 작성한 사원번호
     * @return 존재하면 true, 존재하지 않으면 false
     */
    public boolean employeeNoDoubleCheck(String employeeNoValue) {
        return employeeRepository.existsByEmployeeNo(employeeNoValue);
    }

    /**
     * 이메일이 데이터베이스에 존재하는지 확인
     * @param email 회원가입 페이지에서 작성한 이메일
     * @return 존재하면 true, 존재하지 않으면 false
     */
    public boolean emailDoubleCheck(String email) {
        return employeeRepository.existsByEmail(email);
    }

    /**
     * 전화 번호가 데이터베이스에 존재하는지 확인
     * @param phoneValue 회원가입 페이지에서 작성한 사내전화
     * @return 존재하면 true 존재하지 않으면 false
     */
    public boolean phoneDoubleCheck(String phoneValue) {
        return employeeRepository.existsByPhone(phoneValue);
    }

    /**
     * 회원 정보 수정
     * @param dto 이름, 전화번호
     * @param part 부서,팀 , 맡은일 설정
     */
    @Transactional
    public void update(EmployeeUpdateDto dto, Part part) {
        Employee employee = employeeRepository.findByEmployeeNo(dto.getEmployeeNo());
        employee = employee.update(dto.getName(),dto.getPhone(),part);
    }

    /**
     * 사원번호로 사원찾기
     * @param employeeNo 고유한 사원 번호
     * @return 사원객체
     */
    public Employee findEmployee(String employeeNo) {
        return employeeRepository.findByEmployeeNo(employeeNo);
    }
}
