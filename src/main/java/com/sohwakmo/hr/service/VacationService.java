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

    /**
     * 휴가(vacation) 진행중인 기안 문서 list
     * @param loginUserNo 로그인한 유저 No
     * @param state 진행중
     * @return 로그인한 유저의 진행중인 vacation 카테고리 list
     */
    public List<Vacation> selectByEmployeeNoAndState(String loginUserNo, PaymentState state){
        return vacationRepository.findByEmployeeNoAndStateOrderByNoDesc(loginUserNo, state);
    }

    /**
     * 휴가(vacation) 처리된 기안 문서 list
     * @param loginUserNo 로그인한 유저 No
     * @return 로그인한 유저의 처리된 vacation 카테고리 list
     */
    public List<Vacation> selectByEmployeeNoAndStateOrState(String loginUserNo){
        return vacationRepository.findByEmployeeNoAndStateOrState(loginUserNo);
    }

    /**
     * 휴가(vacation) 클릭한 문서 detail view
     * @param no 문서 No
     * @return 클릭한 vacation 문서 정보
     */
    public Vacation selectByNo(Integer no) {
        return vacationRepository.findById(no).orElse(null);
    }

    /**
     * 휴가(vacation) 상위 직책자의 결재 요청 all List
     * @param loginUserNo 로그인한 회원 No
     * @param state 상태가 '진행중'
     * @return 상태가 '진행중' 이고 결재자가 자신으로 되어 있는 vacation 문서들
     */
    public List<Vacation> selectByApproverNoAndState(String loginUserNo, PaymentState state){
        return vacationRepository.findByApproverNoAndStateOrderByNoDesc(loginUserNo, state);
    }

    /**
     * 휴가(vacation) 상위 직책자의 결재 완료(승인, 반려) all List
     * @param loginUserNo 로그인한 회원 No
     * @param state 상태가 '승인'
     * @param state 상태가 '반려'
     * @return 상태가 '승인' 혹은 '반려'이고, 결재자가 자신으로 되어 있는 vacation 문서들
     */
    public List<Vacation> selectByApproverNoAndStateOrState(String loginUserNo, PaymentState state, PaymentState state2){
        return vacationRepository.findByApproverNoAndStateOrState(loginUserNo, state, state2);
    }

    /**
     * 휴가(vacation) 반려
     * @param no 문서 No
     * @param returnReason 반려 사유
     * @return 상위 직책자가 반려 시, (state, stateTime, returnReason)가 update
     */
    @Transactional
    public Integer updateReturn(Integer no, String returnReason){

        Vacation entity = vacationRepository.selectByNo(no);
        entity.setState(Collections.singleton(PaymentState.반려));
        entity.add(LocalDateTime.now()); // 반려 시간
        entity.returnReason(returnReason); // 반려 사유

        return no;
    }

    /**
     * 휴가(vacation) 승인
     * @param no 문서 No
     * @return 상위 직책자가 승인 시, (state, stateTime)가 update
     */
    @Transactional
    public Integer update(Integer no){

        Vacation entity = vacationRepository.selectByNo(no);
        entity.setState(Collections.singleton(PaymentState.승인));
        entity.add(LocalDateTime.now());

        return no;
    }

    /**
     * 휴가(vacation) 삭제
     * @param no 문서 No
     * @return 해당 문서 삭제 기
     */
    @Transactional
    public Integer delete(Integer no){

        vacationRepository.deleteById(no);

        return no;
    }

    /**
     * 휴가(vacation) create
     * @param vacation input 값
     * @return '진행중' 상태값 addRole
     */
    // 휴가(vacation) create
    public Vacation create(Vacation vacation){
        vacation.addRole(PaymentState.진행중);
        return vacationRepository.save(vacation);
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
}
