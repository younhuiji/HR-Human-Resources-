package com.sohwakmo.hr.service;

import com.sohwakmo.hr.domain.BusinessTrip;
import com.sohwakmo.hr.domain.PaymentState;
import com.sohwakmo.hr.domain.Vacation;
import com.sohwakmo.hr.repository.BusinessTripRepository;
import com.sohwakmo.hr.repository.VacationRepository;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    public List<Vacation>  getTodayVacationList(String employeeNo, String formatedNow) {
        formatedNow = formatedNow.substring(0, 2) + "-" + formatedNow.substring(3);
        return vacationRepository.findByEmployeeNoAndEffectiveDateContaining(employeeNo,formatedNow);
    }
}
