package com.sohwakmo.hr.service;

import com.sohwakmo.hr.domain.BusinessCard;
import com.sohwakmo.hr.domain.BusinessTrip;
import com.sohwakmo.hr.domain.PaymentState;
import com.sohwakmo.hr.domain.Vacation;
import com.sohwakmo.hr.repository.BusinessCardRepository;
import com.sohwakmo.hr.repository.BusinessTripRepository;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class BusinessTripService {

    private final BusinessTripRepository businessTripRepository;

    // 출장(Bs trip) create
    public BusinessTrip create(BusinessTrip businessTrip){
        businessTrip.addRole(PaymentState.진행중);
        return businessTripRepository.save(businessTrip);
    }

    public List<BusinessTrip> selectByEmployeeNo(String employeeNo){
        return businessTripRepository.findByEmployeeNo(employeeNo);
    }

    public List<BusinessTrip> selectByEmployeeNoAndState(String no, PaymentState state){
        return businessTripRepository.findByEmployeeNoAndState(no, state);
    }

    public BusinessTrip selectByNo(Integer no){
        return businessTripRepository.findById(no).orElse(null);
    }

}
