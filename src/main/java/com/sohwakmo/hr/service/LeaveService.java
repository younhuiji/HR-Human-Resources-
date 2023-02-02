package com.sohwakmo.hr.service;

import com.sohwakmo.hr.domain.Leave;
import com.sohwakmo.hr.domain.PaymentState;
import com.sohwakmo.hr.repository.LeaveRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class LeaveService {

    private final LeaveRepository leaveRepository;

    public Leave create(Leave leave){

        leave.addRole(PaymentState.진행중);

        return leaveRepository.save(leave);
    }

    public List<Leave> selectByEmployeeNO(Integer no){
        log.info("서비스 까지 넘어옴={}", no);
        return leaveRepository.selectByEmployeeNO(no);
    }

    public Leave selectByNo(Integer no){

        return leaveRepository.selectByNo(no);
    }

    @Transactional
    public Integer update(Integer no){

        Leave entity = leaveRepository.selectByNo(no);
        boolean true1 = true;
        entity.setSemiState(true1); // 1차 승인자
        entity.semiAdd(LocalDateTime.now()); // 1차 승인 시간
        log.info("승인이됐을까?={}", entity);

        return no;
    }



    @Transactional
    public Integer update2(Integer no){

        Leave entity = leaveRepository.selectByNo(no);
        boolean true1 = true;
        entity.setState(Collections.singleton(PaymentState.승인)); // 2차 승인자
        entity.add(LocalDateTime.now()); // 2차 승인 시간
        log.info("승인이됐을까?={}", entity);

        return no;
    }

    @Transactional
    public Integer updateReturn(Integer no, String returnReason){

        Leave entity = leaveRepository.selectByNo(no);
        entity.addRole(PaymentState.반려);
        entity.returnReason(returnReason);
        log.info("service");

        return no;
    }

}
