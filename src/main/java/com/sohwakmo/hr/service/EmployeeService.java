package com.sohwakmo.hr.service;

import com.sohwakmo.hr.domain.Attendance;
import com.sohwakmo.hr.domain.Employee;
import com.sohwakmo.hr.domain.EmployeePosition;
import com.sohwakmo.hr.domain.Part;
import com.sohwakmo.hr.dto.AttendanceDto;
import com.sohwakmo.hr.dto.EmployeeJoinDto;
import com.sohwakmo.hr.dto.EmployeeUpdateDto;
import com.sohwakmo.hr.repository.AttendanceRepository;
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
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final AttendanceRepository attendanceRepository;
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

    /**
     * 업무시작 버튼 누를시 해당 달,날짜,시간 DB에 저장
     * @param dto 자바스크립트에서 받아온 오늘 날짜.
     */
    public void startWork(AttendanceDto dto) {
        String month;
        String day;
        String hours;
        String minutes;

        // 달 String 으로 변환
        if (dto.getMonth() / 10 == 0)  month = "0" + String.valueOf(dto.getMonth());
        else month = String.valueOf(dto.getMonth());

        // 날짜 String 으로 변환
        if (dto.getDay() / 10 == 0)  day = "0" + String.valueOf(dto.getDay());
        else day = String.valueOf(dto.getDay());

        if (dto.getHours() / 10 == 0)  hours = "0" + String.valueOf(dto.getHours());
        else hours = String.valueOf(dto.getHours());

        if (dto.getMinutes() / 10 == 0)  minutes = "0" + String.valueOf(dto.getMinutes());
        else minutes = String.valueOf(dto.getMinutes());

        Employee employee = employeeRepository.findByEmployeeNo(dto.getEmployeeNo());

        Attendance attendance = Attendance.builder()
                .employee(employee).startTime(hours + ":" + minutes).endTime(getEndTime(dto.getHours(),dto.getMinutes())).month(month).day(day).state(0).build();

        log.info(attendance.toString());
        attendanceRepository.save(attendance);
    }

    /**
     * 예상 퇴근 시간을 구해서 리턴
     * @param hours
     * @param minutes
     * @return 예상 퇴근 시간
     *
     */
    public String getEndTime(Integer hours, Integer minutes) {
        String endHours = String.valueOf(hours + 9);
        String endMinutes = String.valueOf(minutes);
        return endHours + ":" + endMinutes;
    }

    /**
     * 오늘 날짜를 받고 업무시작을 눌렀는지 안눌렀는지 확인한다.
     * @param employeeNo 자신의 사원번호
     * @param formatedNow 오늘 날짜
     * @return 출근했으면 0 아니면 2를 리턴.
     */
    public Attendance checkAttendance(String employeeNo,String formatedNow) {
        Attendance attendance = getRecentAttendance(employeeNo); // 가장 최근에 한 출근 내용을 불러오는 메서드
        String checkAttendance = attendance.getMonth() + "/" + attendance.getDay();
        if(formatedNow.equals(checkAttendance)) return attendance;
        else return null;
    }

    /**
     * 업무 종료 시간을 DB에 저장
     * @param hours 업무 종료를 누른 시간
     * @param minutes 업무 종료를 누른 분
     * @param employeeNo 사원번호
     */
    public void setEndTime(Integer hours, Integer minutes, String employeeNo) {
        Attendance attendance = getRecentAttendance(employeeNo);
        String endHours = String.valueOf(hours);
        String endMinutes;
        if (String.valueOf(minutes).length() == 1) {
            endMinutes = "0" + String.valueOf(minutes);
        }else{
            endMinutes = String.valueOf(minutes);
        }
        attendance = attendance.builder()
                .endTime(endHours + ":" + endMinutes).build();
        attendanceRepository.save(attendance);
    }

    /**
     * 가장 최근에 한 출근 내용을 불러오는 메서드
     * @param employeeNo 사원번호
     * @return 어제나 오늘한 가장 최근 출근 객체를 리턴
     */
    private Attendance getRecentAttendance(String employeeNo) {
        Employee employee = employeeRepository.findByEmployeeNo(employeeNo);
        List<Attendance> list = employee.getAttendances();
        Collections.reverse(list);
        Attendance attendance = list.stream().findAny().get();
        return attendance;
    }
}
