package com.sohwakmo.hr.service;

import com.sohwakmo.hr.domain.BusinessCard;
import com.sohwakmo.hr.domain.Leave;
import com.sohwakmo.hr.domain.PaymentState;
import com.sohwakmo.hr.domain.Vacation;
import com.sohwakmo.hr.repository.BusinessCardRepository;
import groovy.util.logging.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class BusinessCardService {

    private final BusinessCardRepository businessCardRepository;

    /**
     * 휴가(BusinessCard) 모든 기안 문서 list
     * @param loginUserNo 로그인한 유저 No
     * @return 로그인한 유저 No가 작성한 BusinessCard all list
     */
    public List<BusinessCard> selectByEmployeeNo(String loginUserNo){
        return businessCardRepository.findByEmployeeNoOrderByNoDesc(loginUserNo);
    }

    /**
     * 명함(BusinessCard) 진행중인 기안 문서 list
     * @param loginUserNo 로그인한 유저 No
     * @param state 진행중
     * @return 로그인한 유저의 진행중인 BusinessCard 카테고리 list
     */
    public List<BusinessCard> selectByEmployeeNoAndState(String loginUserNo, PaymentState state){
        return businessCardRepository.findByEmployeeNoAndStateOrderByNoDesc(loginUserNo, state);
    }

    /**
     * 명함(BusinessCard) 처리된 기안 문서 list
     * @param loginUserNo 로그인한 유저 No
     * @return 로그인한 유저의 처리된 BusinessCard 카테고리 list
     */
    public List<BusinessCard> selectByEmployeeNoAndStateOrState(String loginUserNo){
        return businessCardRepository.findByEmployeeNoAndStateOrState(loginUserNo);
    }

    /**
     * 명함(BusinessCard) 클릭한 문서 detail view
     * @param no 문서 No
     * @return 클릭한 BusinessCard 문서 정보
     */
    public BusinessCard selectByNo(Integer no){
        return businessCardRepository.findById(no).orElse(null);
    }

    /**
     * 명함(BusinessCard) 상위 직책자의 결재 요청 all List
     * @param loginUserNo 로그인한 회원 No
     * @param state 상태가 '진행중'
     * @return 상태가 '진행중' 이고 결재자가 자신으로 되어 있는 BusinessCard 문서들
     */
    public List<BusinessCard> selectByApproverNoAndState(String loginUserNo, PaymentState state){
        return businessCardRepository.findByApproverNoAndStateOrderByNoDesc(loginUserNo, state);
    }

    /**
     * 명함(BusinessCard) 상위 직책자의 결재 완료(승인, 반려) all List
     * @param loginUserNo 로그인한 회원 No
     * @param state 상태가 '승인'
     * @param state 상태가 '반려'
     * @return 상태가 '승인' 혹은 '반려'이고, 결재자가 자신으로 되어 있는 BusinessCard 문서들
     */
    public List<BusinessCard> selectByApproverNoAndStateOrState(String loginUserNo, PaymentState state, PaymentState state2){
        return businessCardRepository.findByApproverNoAndStateOrStateOrderByNoDesc(loginUserNo, state, state2);
    }

    /**
     * 명함(BusinessCard) 반려
     * @param no 문서 No
     * @param returnReason 반려 사유
     * @return 상위 직책자가 반려 시, (state, stateTime, returnReason)가 update
     */
    @Transactional
    public Integer updateReturn(Integer no, String returnReason){

        BusinessCard entity = businessCardRepository.selectByNo(no);
        entity.setState(Collections.singleton(PaymentState.반려));
        entity.add(LocalDateTime.now()); // 반려 시간
        entity.returnReason(returnReason); // 반려 사유

        return no;
    }

    /**
     * 명함(BusinessCard) 승인
     * @param no 문서 No
     * @return 상위 직책자가 승인 시, (state, stateTime)가 update
     */
    @Transactional
    public Integer update(Integer no){

        BusinessCard entity = businessCardRepository.selectByNo(no);
        entity.setState(Collections.singleton(PaymentState.승인));
        entity.add(LocalDateTime.now());

        return no;
    }

    /**
     * 명함(BusinessCard) 삭제
     * @param no 문서 No
     * @return 해당 문서 삭제 기능
     */
    @Transactional
    public Integer delete(Integer no){
        businessCardRepository.deleteById(no);
        return no;
    }

    /**
     * 명함(BusinessCard) create
     * @param businessCard input 값
     * @return '진행중' 상태값 addRole
     */
    public BusinessCard create(BusinessCard businessCard){
        businessCard.addRole(PaymentState.진행중);

        return businessCardRepository.save(businessCard);
    }

    public List<BusinessCard> getBusinessCardSeven(String employeeNo) {
        List<BusinessCard> allList = businessCardRepository.findByEmployeeNoOrderByNoDesc(employeeNo);
        if (allList.size() <=7) {
            return allList;
        }else{
            List<BusinessCard> list = new ArrayList<>();
            for (int i = 0; i < 7; i++) {
                list.add(allList.get(i));
            }
            return list;
        }
    }
}
