package com.sohwakmo.hr.service;

import com.sohwakmo.hr.domain.BusinessCard;
import com.sohwakmo.hr.domain.Employee;
import com.sohwakmo.hr.repository.BusinessCardRepository;
import com.sohwakmo.hr.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    // 결재자 지정할 때에 임시방편으로 모든 리스트 불러옴
    public List<Employee> readPart(String teamName) {

        return employeeRepository.selectByPart(teamName);
    }

    public Employee selectByNo(Long no) {
            log.info("no={}", no);
        return employeeRepository.selectByNo(no);
    }

}
