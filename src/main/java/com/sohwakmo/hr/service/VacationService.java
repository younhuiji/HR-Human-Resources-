package com.sohwakmo.hr.service;

import com.sohwakmo.hr.domain.BusinessCard;
import com.sohwakmo.hr.domain.BusinessTrip;
import com.sohwakmo.hr.domain.PaymentState;
import com.sohwakmo.hr.domain.Post;
import com.sohwakmo.hr.domain.Vacation;
import com.sohwakmo.hr.repository.BusinessTripRepository;
import com.sohwakmo.hr.repository.VacationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class VacationService {

    private final VacationRepository vacationRepository;

    /**
     * 휴가(vacation) 모든 기안 문서 list
     * @param loginUserNo 로그인한 유저 No
     * @return 로그인한 유저 No가 작성한 vacation all list
     */
    public List<Vacation> selectByEmployeeNo(String loginUserNo){
        return vacationRepository.findByEmployeeNoOrderByNoDesc(loginUserNo);
    }

    // 휴가(vacation) create
    public Vacation create(Vacation vacation){
        vacation.addRole(PaymentState.진행중);
        return vacationRepository.save(vacation);
    }

    // 결재자

    public List<Vacation> selectByEmployeeNoAndStateOrState(String no){
       log.info("회원={}", no);
        return vacationRepository.findByEmployeeNoAndStateOrState(no);
    }

    public List<Vacation> selectByApproverNoAndStateOrState(String no, PaymentState state, PaymentState state2){
        return vacationRepository.findByApproverNoAndStateOrState(no, state, state2);
    }

    public List<Vacation> selectByEmployeeNoAndState(String no, PaymentState state){
        return vacationRepository.findByEmployeeNoAndState(no, state);
    }

    public List<Vacation>  getTodayVacationList(String employeeNo, String formatedNow) {
        formatedNow = formatedNow.substring(0, 2) + "-" + formatedNow.substring(3);
        return vacationRepository.findByEmployeeNoAndEffectiveDateContaining(employeeNo,formatedNow);
    }

    public List<Vacation> getVacationListSeven(String employeeNo) {
        List<Vacation> allList = vacationRepository.findByEmployeeNoOrderByNoDesc(employeeNo);
        if (allList.size() <= 7) {
            return allList;
        }else {
            List<Vacation> list = new ArrayList<>();
            for (int i = 0; i < 7; i++) {
                list.add(allList.get(i));
            }
            return list;
        }
    }
    public List<Vacation> selectByApproverNoAndState(String no, PaymentState state){
        return vacationRepository.findByApproverNoAndState(no, state);
    }

    public Vacation selectByNo(Integer no) {
        return vacationRepository.findById(no).orElse(null);
    }

    @Transactional
    public Integer update(Integer no){

        Vacation entity = vacationRepository.selectByNo(no);
        entity.setState(Collections.singleton(PaymentState.승인));
        entity.add(LocalDateTime.now());

        return no;
    }

    @Transactional
    public Integer delete(Integer no){

        vacationRepository.deleteById(no);

        return no;
    }

    @Transactional
    public Integer updateReturn(Integer no, String returnReason){

        Vacation entity = vacationRepository.selectByNo(no);
        entity.setState(Collections.singleton(PaymentState.반려));
        entity.add(LocalDateTime.now()); // 반려 시간
        entity.returnReason(returnReason); // 반려 사유

        return no;
    }
}
