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

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class BusinessTripService {

    private final BusinessTripRepository businessTripRepository;

    public List<BusinessTrip> getTodayBusinessTripList(String employeeNo, String formatedNow) {
        formatedNow = formatedNow.substring(0, 2) + "-" + formatedNow.substring(3);
        return businessTripRepository.findByEmployeeNoAndEffectiveDateContaining(employeeNo,formatedNow);
    }

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

    public List<BusinessTrip> getBusinessTripSeven(String employeeNo) {
        List<BusinessTrip> allList = businessTripRepository.findByEmployeeNoOrderByNoDesc(employeeNo);
        if (allList.size() == 0) {
            return allList;
        }else {
            List<BusinessTrip> list = new ArrayList<>();
            for (int i = 0; i < 7; i++) {
                list.add(allList.get(i));
            }
            return list;
        }
    }
}
