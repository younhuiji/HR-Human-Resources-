package com.sohwakmo.hr.service;

import com.sohwakmo.hr.domain.Leave;
import com.sohwakmo.hr.domain.PaymentState;
import com.sohwakmo.hr.repository.LeaveRepository;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class LeaveService {

    private final LeaveRepository leaveRepository;

    /**
     * 퇴사(Leave) 모든 기안 문서 list
     * @param loginUserNo 로그인한 유저 No
     * @return 로그인한 유저 No가 작성한 Leave all list
     */
    public List<Leave> selectByEmployeeNo(String loginUserNo){
        return leaveRepository.selectByEmployeeNoOrderByNoDesc(loginUserNo);
    }

    /**
     * 퇴사(Leave) 진행중인 기안 문서 list
     * @param loginUserNo 로그인한 유저 No
     * @param state 진행중
     * @return 로그인한 유저의 진행중인 Leave 카테고리 list
     */
    public List<Leave> selectByEmployeeNoAndState(String loginUserNo, PaymentState state){
        return leaveRepository.findByEmployeeNoAndStateOrderByNoDesc(loginUserNo, state);
    }

    /**
     * 퇴사(Leave) 처리된 기안 문서 list
     * @param loginUserNo 로그인한 유저 No
     * @return 로그인한 유저의 처리된 Leave 카테고리 list
     */
    public List<Leave> selectByEmployeeNoAndStateOrState(String loginUserNo){
        return leaveRepository.findByEmployeeNoAndStateOrState(loginUserNo);
    }

    /**
     * 퇴사(Leave) 클릭한 문서 detail view
     * @param no 문서 No
     * @return 클릭한 Leave 문서 정보
     */
    public Leave selectByNo(Integer no){

        return leaveRepository.findById(no).orElse(null);
    }

    /**
     * 퇴사(Leave) 상위 직책자의 결재 요청 all List
     * @param loginUserNo 로그인한 회원 No
     * @return 상태가 '진행중' 이고 결재자가 자신으로 되어 있는 Leave 문서들
     */
    public List<Leave> selectByApproverNoOrSecondNoAndState(String loginUserNo){
        return leaveRepository.findByApproverNoOrSecondApproverNoAndStateOrderByNoDesc(loginUserNo);
    }

    /**
     * 퇴사(Leave) 상위 직책자의 결재 완료(승인, 반려) all List
     * @param loginUserNo 로그인한 회원 No
     * @return 상태가 '승인' 혹은 '반려'이고, 결재자가 자신으로 되어 있는 Leave 문서들
     */
    public List<Leave> selectByApproverNoOrSecondApproverNoAndStateOrState(String loginUserNo){
        return leaveRepository.findByApproverNoOrSecondApproverNoAndStateOrState(loginUserNo);
    }

    /**
     * 퇴사(Leave) 반려
     * @param no 문서 No
     * @param returnReason 반려 사유
     * @return 상위 직책자가 반려 시, (state, stateTime, returnReason)가 update
     */
    @Transactional
    public Integer updateReturn(Integer no, String returnReason){

        Leave entity = leaveRepository.selectByNo(no);
        entity.setState(Collections.singleton(PaymentState.반려));
        entity.add(LocalDateTime.now()); // 반려 시간
        entity.returnReason(returnReason); // 반려 사유

        return no;
    }


    /**
     * 퇴사(Leave) 1차 승인
     * @param no 문서 No
     * @return 상위 직책자가 1차 승인 시, 1차 (state, stateTime)가 update
     */
    @Transactional
    public Integer update(Integer no){

        Leave entity = leaveRepository.selectByNo(no);
        boolean true1 = true;
        entity.setSemiState(true1); // 1차 승인
        entity.semiAdd(LocalDateTime.now()); // 1차 승인 시간

        return no;
    }

    /**
     * 퇴사(Leave) 2차 승인
     * @param no 문서 No
     * @return 상위 직책자가 2차 승인 시, 2차 (state, stateTime)가 update
     */    @Transactional
    public Integer update2(Integer no){

        Leave entity = leaveRepository.selectByNo(no);
        entity.setState(Collections.singleton(PaymentState.승인)); // 2차 승인자
        entity.add(LocalDateTime.now()); // 2차 승인 시간

        return no;
    }

    /**
     * 퇴사(Leave) 삭제
     * @param no 문서 No
     * @return 해당 문서 삭제 기능
     */
    @Transactional
    public Integer delete(Integer no){
        leaveRepository.deleteById(no);
        return no;
    }

    /**
     * 퇴사(Leave) create
     * @param leave input 값
     * @return '진행중' 상태값 addRole
     */
    public Leave create(Leave leave){
        leave.addRole(PaymentState.진행중);
        return leaveRepository.save(leave);
    }

}
