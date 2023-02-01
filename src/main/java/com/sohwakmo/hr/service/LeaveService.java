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

    @Transactional
    public Integer update(Integer no){

        Leave entity = leaveRepository.selectByNo(no);
        boolean true1 = true;
        entity.setSemiState(true1); // 1차 승인자
//        entity.setState(Collections.singleton(PaymentState.승인)); // 2차 승인자
        entity.add(LocalDateTime.now());
        log.info("승인이됐을까?={}", entity);

        return no;
    }

}
