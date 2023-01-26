package com.sohwakmo.hr.service;

import com.sohwakmo.hr.domain.BusinessCard;
import com.sohwakmo.hr.repository.BusinessCardRepository;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class BusinessCardService {

    private final BusinessCardRepository businessCardRepository;

    public BusinessCard create(BusinessCard businessCard){

        return businessCardRepository.save(businessCard);
    }

    public List<BusinessCard> selectByCategory(String card) {
        return businessCardRepository.selectByCard(card);
    }

}
