package com.sohwakmo.hr.service;

import com.sohwakmo.hr.domain.*;
import com.sohwakmo.hr.repository.BusinessCardRepository;
import com.sohwakmo.hr.repository.BusinessTripRepository;
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
public class BusinessTripService {

    private final BusinessTripRepository businessTripRepository;

    // 출장(Bs trip) create
    public BusinessTrip create(BusinessTrip businessTrip){
        businessTrip.addRole(PaymentState.진행중);
        return businessTripRepository.save(businessTrip);
    }

    public List<BusinessTrip> selectByEmployeeNo(String employeeNo){
        return businessTripRepository.findByEmployeeNoOrderByNoDesc(employeeNo);
    }

    public List<BusinessTrip> selectByEmployeeNoAndState(String no, PaymentState state){
        return businessTripRepository.findByEmployeeNoAndState(no, state);
    }

    public List<BusinessTrip> selectByEmployeeNoAndStateOrState(String no, PaymentState state, PaymentState state2){
        return businessTripRepository.findByEmployeeNoAndStateOrState(no, state, state2);
    }

    public List<BusinessTrip> selectByApproverNoAndState(String no, PaymentState state){
        return businessTripRepository.findByApproverNoAndState(no, state);
    }

    public List<BusinessTrip> selectByApproverNoAndStateOrState(String no, PaymentState state, PaymentState state2){
        return businessTripRepository.findByApproverNoAndStateOrState(no, state, state2);
    }

    public BusinessTrip selectByNo(Integer no){
        return businessTripRepository.findById(no).orElse(null);
    }

    @Transactional
    public Integer update(Integer no){

        BusinessTrip entity = businessTripRepository.selectByNo(no);
        entity.setState(Collections.singleton(PaymentState.승인));
        entity.add(LocalDateTime.now());

        return no;
    }

    @Transactional
    public Integer delete(Integer no){
        businessTripRepository.deleteById(no);
        return no;
    }

    @Transactional
    public Integer updateReturn(Integer no, String returnReason){

        BusinessTrip entity = businessTripRepository.selectByNo(no);
        entity.setState(Collections.singleton(PaymentState.반려));
        entity.add(LocalDateTime.now()); // 반려 시간
        entity.returnReason(returnReason); // 반려 사유

        return no;
    }

}
