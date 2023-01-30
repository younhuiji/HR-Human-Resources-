package com.sohwakmo.hr.service;

import com.sohwakmo.hr.domain.BusinessCard;
import com.sohwakmo.hr.domain.Leave;
import com.sohwakmo.hr.repository.BusinessCardRepository;
import com.sohwakmo.hr.repository.LeaveRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class LeaveService {

    private final LeaveRepository leaveRepository;

    public Leave create(Leave leave){
        return leaveRepository.save(leave);
    }

    public List<Leave> selectByName(String employeeName){
        log.info("서비스 까지 넘어옴={}", employeeName);
        return leaveRepository.selectByName(employeeName);
    }

    public Leave selectByNo(Integer no){
        return leaveRepository.selectByNo(no);
    }

}
