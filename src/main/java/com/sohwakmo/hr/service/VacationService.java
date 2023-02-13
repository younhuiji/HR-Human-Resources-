package com.sohwakmo.hr.service;

import com.sohwakmo.hr.domain.BusinessCard;
import com.sohwakmo.hr.domain.BusinessTrip;
import com.sohwakmo.hr.domain.PaymentState;
import com.sohwakmo.hr.domain.Vacation;
import com.sohwakmo.hr.repository.BusinessTripRepository;
import com.sohwakmo.hr.repository.VacationRepository;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class VacationService {

    private final VacationRepository vacationRepository;

    // 휴가(vacation) list
    public List<Vacation> selectByEmployeeNo(String no){
        return vacationRepository.findByEmployeeNo(no);
    }

    // 휴가(vacation) create
    public Vacation create(Vacation vacation){
        vacation.addRole(PaymentState.진행중);
        return vacationRepository.save(vacation);
    }

    public List<Vacation> selectByEmployeeNoAndState(String no, PaymentState state){
        return vacationRepository.findByEmployeeNoAndState(no, state);
    }

    public Vacation selectByNo(Integer no) {
        return vacationRepository.findById(no).orElse(null);
    }

    @Transactional
    public Integer update(Integer no){

//        Vacation entity = VacationRepository.selectByNo(no);
//        entity.setState(Collections.singleton(PaymentState.승인));
//        entity.add(LocalDateTime.now());

        return no;
    }

    @Transactional
    public Integer delete(Integer no){

        vacationRepository.deleteById(no);

        return no;
    }

    @Transactional
    public Integer updateReturn(Integer no, String returnReason){

//        Vacation entity = VacationRepository.selectByNo(no);
//        entity.setState(Collections.singleton(PaymentState.반려));
//        entity.add(LocalDateTime.now()); // 반려 시간
//        entity.returnReason(returnReason); // 반려 사유

        return no;
    }
}
