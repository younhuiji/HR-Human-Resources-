package com.sohwakmo.hr.service;

import com.sohwakmo.hr.domain.BusinessCard;
import com.sohwakmo.hr.domain.Leave;
import com.sohwakmo.hr.domain.PaymentState;
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
        leave.addRole(PaymentState.진행중);

        log.info("상태 값 확인하기={}", leave);

        return leaveRepository.save(leave);
    }

    public List<Leave> selectByName(Integer no){
        log.info("서비스 까지 넘어옴={}", no);
        return leaveRepository.selectByName(no);
    }

    public Leave selectByNo(Integer no){
        return leaveRepository.selectByNo(no);
    }

}
