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
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
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
                .employee(employee).startTime(hours + ":" + minutes).expectEndTime(getExpectEndTime(dto.getHours(),dto.getMinutes())).month(month).day(day).state(0).build();

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
    public String getExpectEndTime(Integer hours, Integer minutes) {
        String endHours = String.valueOf(hours + 9);
        String endMinutes = String.valueOf(minutes);
        if (endMinutes.length() == 1) {
            endMinutes = "0" + String.valueOf(minutes);
        }
        return endHours + ":" + endMinutes;
    }

    /**
     * 오늘 날짜를 받고 업무시작을 눌렀는지 안눌렀는지 확인한다.
     * @param employeeNo 자신의 사원번호
     * @param formatedNow 오늘 날짜
     * @return 출근했으면 0 아니면 2를 리턴.
     */
    public Long checkAttendance(String employeeNo,String formatedNow) {
        Employee employee = employeeRepository.findByEmployeeNo(employeeNo);
        List<Attendance> list = employee.getAttendances();
        if (list.size() == 0) {
            return -1L;
        } else {
            Collections.reverse(list);
            Attendance attendance = list.stream().findAny().orElse(null);
            String recentAttendace = attendance.getMonth() + "/" + attendance.getDay();
            if (!recentAttendace.equals(formatedNow)) { // 오늘 날짜랑 데이테베이스의 가장 최근 데이터랑 비교해서 날짜가 다르면 업무시작 버튼 같으면 이미 업무시작 버튼을 누름.
                return -1L;
            }
            return attendance.getId();
        }
    }

    /**
     * 업무 종료 시간을 DB에 저장
     * @param hours 업무 종료를 누른 시간
     * @param minutes 업무 종료를 누른 분
     * @param employeeNo 사원번호
     */
    public void setEndTime(Integer hours, Integer minutes, String employeeNo) {
        Long attendanceNo = getRecentAttendance(employeeNo);
        Attendance attendance = attendanceRepository.findById(attendanceNo).get();
        String endHours = String.valueOf(hours);
        String endMinutes;
        if (String.valueOf(minutes).length() == 1) {
            endMinutes = "0" + String.valueOf(minutes);
        }else{
            endMinutes = String.valueOf(minutes);
        }
        attendance.setEndTime(endHours + ":" + endMinutes);
        attendance.setState(checkEndTime(endHours,endMinutes,attendance));
        attendanceRepository.save(attendance);
    }

    /**
     * 예상 시간보다 늦게 즉 알맞은 시간에 업무 종료 버튼을 눌렀는지 확인하고 눌렀으면 상태를 0로변경, 아니면 1로 변경
     *
     * @param endHours   업무종료를 누른 시
     * @param endMinutes 업무종료를 누른 분
     * @param attendance 예상종료 업무시간을 가져오기위한 객체
     * @return 알맞은 시간에 퇴근해씅면 0, 아니면 1
     */
    private Integer checkEndTime(String endHours, String endMinutes, Attendance attendance) {
        int check = 0;
        // 예상퇴근 시간 가져오기
        String expectEndTime = attendance.getExpectEndTime();
        String expectEndHours = expectEndTime.substring(0,2);
        String expectEndMinutes = expectEndTime.substring(3);
        // 업무종료를 누른시간과예상 퇴근시간 비교하기
        if (Integer.parseInt(endHours) < Integer.parseInt(expectEndHours)) {
            check = 1;
        } else if (Integer.parseInt(endHours) == Integer.parseInt(expectEndHours)) {
            if(Integer.parseInt(endMinutes) < Integer.parseInt(expectEndMinutes)){
                check = 1;
            }
        }
        return check;
    }

    /**
     * 가장 최근에 한 출근 내용의 ID 불러오는 메서드
     * @param employeeNo 사원번호
     * @return 어제나 오늘한 가장 최근 출근 객체의 Id 를 리턴
     */
    private Long getRecentAttendance(String employeeNo) {
        Employee employee = employeeRepository.findByEmployeeNo(employeeNo);
        List<Attendance> list = employee.getAttendances();
        if (list.size() == 0) {
            return -1L;
        } else {
            Collections.reverse(list);
            Attendance attendance = list.stream().findAny().orElse(null);
            assert attendance != null;
            return attendance.getId();
        }
    }

    /**
     * 출근 퇴근 기록 가져오기
     * @param attendanceNo 가장최근 출근 기록 id 번호
     * @return 가징 최근 출근기록 객체
     */
    public Attendance getAttendance(Long attendanceNo) {
        return attendanceRepository.findById(attendanceNo).get();
    }

    /**
     * 자리비움으로 인한 Attendance 값 1로 바꾸기
     * @param employeeNo 사원번호
     */
    @Transactional
    public void setStateOne(String employeeNo) {
        Long recentAttendanceNo = getRecentAttendance(employeeNo); // 오늘 또는 가장 최근 출근 기록의 Id
        Attendance attendance = attendanceRepository.findById(recentAttendanceNo).get();
        attendance.setState(1); // 자리비움, 조퇴, 외출 등의 상태.
    }

    /**
     * 자리비움해제를 통한 Attendance 값 0으로 바꾸기
     * @param employeeNo 사원번호
     */
    @Transactional
    public void setStateWork(String employeeNo) {
        Long recentAttendanceNo = getRecentAttendance(employeeNo); // 오늘 또는 가장 최근 출근 기록의 Id
        Attendance attendance = attendanceRepository.findById(recentAttendanceNo).get();
        attendance.setState(0); // 자리비움, 조퇴, 외출 등의 상태.
    }

    /**
     * 현재시간과 DB에 저장된 시간을 비교해서 지금까지한 업무 시간을 계산해서 리턴
     * @param startTime DB에 저장된 시작시간
     * @param nowTime 현재 시간
     * @return 현재시간 - DB에 저장된 시간을 계산하여 총 업무시간을 리턴.
     */
    public String countWorkingTime(String startTime,String nowTime) {
        // 시
        Integer startWorkHour = Integer.parseInt(startTime.substring(0,2));
        Integer nowTimeHour = Integer.parseInt(nowTime.substring(0,2));

        // 분
        int startTimeMinutes = Integer.parseInt(startTime.substring(3));
        int nowTimeMinutes = Integer.parseInt(nowTime.substring(3));

        // 현재 분이 시작 분보다 작은 경우와 그렇지 않은경우를 나눠서 업무시간 계산
        String resultHour;
        String resultMinutes;
        if (startTimeMinutes > nowTimeMinutes) {
            resultMinutes = String.valueOf(60 - startTimeMinutes + nowTimeMinutes);
            if(nowTimeHour==startWorkHour) resultHour="0";
            else if (nowTimeHour-startWorkHour==1) resultHour = "0";
            else resultHour = String.valueOf(nowTimeHour - startWorkHour - 1);
        }else{
            resultHour = String.valueOf(nowTimeHour - startWorkHour);
            resultMinutes = String.valueOf(nowTimeMinutes - startTimeMinutes);
        }
        return resultHour + "시간 " + resultMinutes +"분";
    }

    /**
     * 리스트로 받은 출석기록을 달마다 일수가 다르므로 일수에 맞게 배열로 변환후 리턴
     * @param list 입력받은 달의 출석 리스트
     * @return 28일, 30일, 31일 에 따라 배열 길이를 다르게해서 변환.
     */
    public String[] setStartTimeDays(List<Attendance> list) {
        Attendance attendance = list.get(list.size()-1);
        int month = Integer.parseInt(attendance.getMonth());
        // 2월인경우
        if (month==2) {
            String[] startTimeDays = new String[28];
            setStartTimes(startTimeDays, list);
            return startTimeDays;
        } else if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) { // 31일까지 있는 달
            String[] startTimeDays = new String[31];
            setStartTimes(startTimeDays, list);
            return startTimeDays;
        }else{ // 그외 30일 까지 있는달
            String[] startTimeDays = new String[30];
            setStartTimes(startTimeDays, list);
            return startTimeDays;
        }
    }

    /**
     * 배열을 넘길때 비어있는 부분은 채우고 들어가야할 부분을 채우는 메서드
     * @param startTimeDays 채워서 보낼 배열
     * @param list 그달에 있는 출결 리스트
     */
    private void setStartTimes(String[]startTimeDays, List<Attendance> list) {
        for (Attendance a : list) {
            int day = Integer.parseInt(a.getDay());
            startTimeDays[day-1] = a.getStartTime();
        }
        // 빈칸에 "-" 로 채우기
        for (int i = 0; i < startTimeDays.length; i++) {
            if (startTimeDays[i] == null) startTimeDays[i] = "-";
        }
    }

    /**
     * 리스트로 받은 출석기록을 달마다 일수가 다르므로 일수에 맞게 배열로 변환후 리턴
     * @param list 입력받은 달의 출석 리스트
     * @return 28일, 30일, 31일 에 따라 배열 길이를 다르게해서 변환.
     */
    public String[] setEndTimeDays(List<Attendance> list) {
        Attendance attendance = list.get(list.size()-1);
        int month = Integer.parseInt(attendance.getMonth());
        // 2월인경우
        if (month==2) {
            String[] endTimeDays = new String[28];
            setEndTimes(endTimeDays, list);
            return endTimeDays;
        } else if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) { // 31일까지 있는 달
            String[] endTimeDays = new String[31];
            setEndTimes(endTimeDays, list);
            return endTimeDays;
        }else{ // 그외 30일 까지 있는달
            String[] endTimeDays = new String[30];
            setEndTimes(endTimeDays, list);
            return endTimeDays;
        }
    }

    /**
     * 리스트에서 퇴근 시간이 정해져있으면 배열에 저장 아니면 ' - ' 를 저장
     * @param endTimeDays 저장해야할 배열
     * @param list 지정받은 달의 출석기록의 객체를 담은 리스트
     */
    private void setEndTimes(String[] endTimeDays, List<Attendance> list) {
        for (Attendance a : list) {
            int day = Integer.parseInt(a.getDay());
            endTimeDays[day-1] = a.getEndTime();
        }
        // 빈칸에 "-" 로 채우기
        for (int i = 0; i < endTimeDays.length; i++) {
            if (endTimeDays[i] == null) endTimeDays[i] = "-";
        }
    }

    /**
     * 검색한 달의 출석현황을 담은 배열을 리턴 출근시 0 조퇴, 외출, 반차시 1, 결근시 2를 저장해서 리턴
     * @param list 입력받은 달의 출석기록 객체를 담은 리시트
     * @return 출석현황을 배열에 담아서 리턴
     */
    public String[] setWorkState(List<Attendance> list) {
        Attendance attendance = list.get(list.size()-1);
        int month = Integer.parseInt(attendance.getMonth());
        // 2월인경우
        if (month==2) {
            String[] workState = new String[28];
            setWorkStates(workState, list);
            return workState;
        } else if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) { // 31일까지 있는 달
            String[] workState = new String[31];
            setWorkStates(workState, list);
            return workState;
        }else{ // 그외 30일 까지 있는달
            String[] workState = new String[30];
            setWorkStates(workState, list);
            return workState;
        }
    }

    /**
     * 지정받은 달의 출근 현황을 리스트로 변환
     * @param workState 반환할 값을 담을 배열
     * @param list 지정받은 달의 출근 기록 리스트
     */
    private void setWorkStates(String[] workState, List<Attendance> list) {
        for (Attendance a : list) {
            int day = Integer.parseInt(a.getDay());
            if(a.getState()==0) workState[day-1] = "O";
            else if (a.getState()==1) workState[day - 1] = "△";
            else workState[day - 1] = "X";
        }
        // 빈칸에 "-" 로 채우기
        for (int i = 0; i < workState.length; i++) {
            if (workState[i] == null) workState[i] = "-";
        }
    }

    /**
     * 로그인한 멤버의 총 출근기록을 가져와서 가장 최근 달의 출근기록을 리턴
     * @param employeeAttendanceList 지금까지의 출근기록
     * @return 지정한 달의 출근 기록 리스트
     */
    public List<Attendance> getCurrentMonth(List<Attendance> employeeAttendanceList) {
        Collections.reverse(employeeAttendanceList);
        String currentMonth = employeeAttendanceList.get(0).getMonth();
        return attendanceRepository.findByMonth(currentMonth);
    }

    /**
     * 로그인한 멤버의 총 출근기록을 가져와서 검색한 달의 출근 기록의 달을 반환한다.
     * @param month 마이페이지에서 전달받은 검색하고 싶은 달
     * @return 검색한 달의 달만 리턴
     */
    public List<Attendance> getSearchMonth(String month) {
        return attendanceRepository.findByMonth(month);
    }
    // 결재자 지정할 때에 임시방편으로 모든 리스트 불러옴
    public List<Employee> readPart(String teamName) {
        return employeeRepository.selectByPart(teamName);
    }

    // 퇴사(leave) detail의 회원 정보 보냄
    public Employee selectByNo(String no) {
        return employeeRepository.selectByNo(no);
    }

    /**
     * employeeNo가 존재하는지 안하는지 체크하고 존재하면 yes 존재 하지않으면 no를 한다.
     * @param employeeNo 비밀번호 찾기에서 입력받은 사원번호
     * @return 존재하면 yes 없으면 no
     */
    public String checkEmployeeNo(String employeeNo) {
        String result;
        if (employeeRepository.existsByEmployeeNo(employeeNo)) result = "yes";
        else result = "no";
        return result;
    }

    /**
     * 비밀번호 재설정 페이지에서 받은 비밀번호를 재설정
     *
     * @param password   비밀번호 재설정 페이지에서 받은 비밀번호
     * @param employeeNo
     */
    @Transactional
    public void resetPassword(String password, String employeeNo) {
        Employee employee = employeeRepository.findByEmployeeNo(employeeNo);
        employee.setPassword(passwordEncoder.encode(password));
    }
}
