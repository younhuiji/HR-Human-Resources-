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
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class BusinessTripService {

    private final BusinessTripRepository businessTripRepository;

    /**
     * 출장(BusinessTrip) 모든 기안 문서 list
     * @param loginUserNo 로그인한 유저 No
     * @return 로그인한 유저 No가 작성한 BusinessTrip all list
     */
    public List<BusinessTrip> selectByEmployeeNo(String loginUserNo){
        return businessTripRepository.findByEmployeeNoOrderByNoDesc(loginUserNo);
    }

    /**
     * 출장(BusinessTrip) 진행중인 기안 문서 list
     * @param loginUserNo 로그인한 유저 No
     * @param state 진행중
     * @return 로그인한 유저의 진행중인 BusinessTrip 카테고리 list
     */
    public List<BusinessTrip> selectByEmployeeNoAndState(String loginUserNo, PaymentState state){
        return businessTripRepository.findByEmployeeNoAndStateOrderByNoDesc(loginUserNo, state);
    }

    /**
     * 출장(BusinessTrip) 클릭한 문서 detail view
     * @param no 문서 No
     * @return 클릭한 BusinessTrip 문서 정보
     */
    public BusinessTrip selectByNo(Integer no){
        return businessTripRepository.findById(no).orElse(null);
    }

    /**
     * 출장(BusinessTrip) 상위 직책자의 결재 요청 all List
     * @param loginUserNo 로그인한 회원 No
     * @param state 상태가 '진행중'
     * @return 상태가 '진행중' 이고 결재자가 자신으로 되어 있는 BusinessTrip 문서들
     */
    public List<BusinessTrip> selectByApproverNoAndState(String loginUserNo, PaymentState state){
        return businessTripRepository.findByApproverNoAndStateOrderByNoDesc(loginUserNo, state);
    }

    /**
     * 출장(BusinessTrip) 상위 직책자의 결재 완료(승인, 반려) all List
     * @param loginUserNo 로그인한 회원 No
     * @param state 상태가 '승인'
     * @param state 상태가 '반려'
     * @return 상태가 '승인' 혹은 '반려'이고, 결재자가 자신으로 되어 있는 BusinessTrip 문서들
     */
    public List<BusinessTrip> selectByApproverNoAndStateOrState(String loginUserNo, PaymentState state, PaymentState state2){
        return businessTripRepository.findByApproverNoAndStateOrStateOrderByNoDesc(loginUserNo, state, state2);
    }

    public List<BusinessTrip> getTodayBusinessTripList(String employeeNo, String formatedNow) {
        formatedNow = formatedNow.substring(0, 2) + "-" + formatedNow.substring(3);
        return businessTripRepository.findByEmployeeNoAndEffectiveDateContaining(employeeNo,formatedNow);
    }

    // 출장(Bs trip) create
    public BusinessTrip create(BusinessTrip businessTrip){
        businessTrip.addRole(PaymentState.진행중);
        return businessTripRepository.save(businessTrip);
    }




    public List<BusinessTrip> selectByEmployeeNoAndStateOrState(String no){
        return businessTripRepository.findByEmployeeNoAndStateOrState(no);
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

    public List<BusinessTrip> getBusinessTripSeven(String employeeNo) {
        List<BusinessTrip> allList = businessTripRepository.findByEmployeeNoOrderByNoDesc(employeeNo);
        if (allList.size() <=7) {
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
