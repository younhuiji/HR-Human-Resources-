package com.sohwakmo.hr.service;

import com.sohwakmo.hr.domain.BusinessCard;
import com.sohwakmo.hr.domain.BusinessTrip;
import com.sohwakmo.hr.repository.BusinessCardRepository;
import com.sohwakmo.hr.repository.BusinessTripRepository;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class BusinessTripService {

    private final BusinessTripRepository businessTripRepository;

    // 출장(Bs trip) create
    public BusinessTrip create(BusinessTrip businessTrip){
        return businessTripRepository.save(businessTrip);
    }

}
