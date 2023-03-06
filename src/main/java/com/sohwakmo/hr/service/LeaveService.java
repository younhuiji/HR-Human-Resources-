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

    // 퇴사(leave) create 할 때에 state 값 "진행중"으로 입력
    public Leave create(Leave leave){
        leave.addRole(PaymentState.진행중);
        return leaveRepository.save(leave);
    }



    // 퇴사(leave) leaveNO로 select
    public Leave selectByNo(Integer no){
        return leaveRepository.selectByNo(no);
    }

    // 퇴사(leave) 1차 승인
    @Transactional
    public Integer update(Integer no){

        Leave entity = leaveRepository.selectByNo(no);
        boolean true1 = true;
        entity.setSemiState(true1); // 1차 승인자
        entity.semiAdd(LocalDateTime.now()); // 1차 승인 시간

        return no;
    }

    // 퇴사(leave) 2차 승인
    @Transactional
    public Integer update2(Integer no){

        Leave entity = leaveRepository.selectByNo(no);
        entity.setState(Collections.singleton(PaymentState.승인)); // 2차 승인자
        entity.add(LocalDateTime.now()); // 2차 승인 시간

        return no;
    }

    // 퇴사(leave) 반려
    @Transactional
    public Integer updateReturn(Integer no, String returnReason){

        Leave entity = leaveRepository.selectByNo(no);
        entity.setState(Collections.singleton(PaymentState.반려));
        entity.add(LocalDateTime.now()); // 반려 시간
        entity.returnReason(returnReason); // 반려 사유

        return no;
    }

    public List<Leave> selectByEmployeeNoAndStateOrState(String no){
        return leaveRepository.findByEmployeeNoAndStateOrState(no);
    }

    public List<Leave> selectByEmployeeNoAndState(String no, PaymentState state){
        return leaveRepository.findByEmployeeNoAndState(no, state);
    }

    public List<Leave> selectByApproverNoOrSecondApproverNoAndStateOrState(String no, String no2, PaymentState state, PaymentState state2){
        return leaveRepository.findByApproverNoOrSecondApproverNoAndStateOrState(no, no2, state, state2);
    }

    public List<Leave> selectByApproverNoOrSecondNoAndState(String no, String no2, PaymentState state){
        return leaveRepository.findByApproverNoOrSecondApproverNoAndState(no, no2, state);
    }

    @Transactional
    public Integer delete(Integer no){
        leaveRepository.deleteById(no);
        return no;
    }

}
