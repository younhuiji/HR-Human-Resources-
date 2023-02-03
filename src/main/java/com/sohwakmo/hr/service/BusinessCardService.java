package com.sohwakmo.hr.service;

import com.sohwakmo.hr.domain.BusinessCard;
import com.sohwakmo.hr.repository.BusinessCardRepository;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class BusinessCardService {

    private final BusinessCardRepository businessCardRepository;

    // 명함(Bs card) create
    public BusinessCard create(BusinessCard businessCard){
        return businessCardRepository.save(businessCard);
    }

    // TODO: 명함, 사용자 no 가져와서 리스트 출력하기 -> list 출력
    public List<BusinessCard> selectByCategory(String card) {
        return businessCardRepository.selectByCard(card);
    }

    // 명함(Bs card) detail
    public BusinessCard selectByNo(Integer cardNo){
        return businessCardRepository.findById(cardNo).orElse(null);
    }

}
