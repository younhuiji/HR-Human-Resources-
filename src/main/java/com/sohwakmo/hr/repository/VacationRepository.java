package com.sohwakmo.hr.repository;

import com.sohwakmo.hr.domain.BusinessCard;
import com.sohwakmo.hr.domain.PaymentState;
import com.sohwakmo.hr.domain.Vacation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VacationRepository extends JpaRepository<Vacation, Integer> {

    // select * from vacation where employeeNo = loginUser order by no desc;
    List<Vacation> findByEmployeeNoOrderByNoDesc(String loginUser);

    // select * from vacation where (employeeNo = loginUser and state = '진행중') order by no desc;
    List<Vacation> findByEmployeeNoAndStateOrderByNoDesc(String no, PaymentState state);

    // select * from vacation where (employeeNo = loginUser) and (state = '승인' or state = '반려') order by no desc;
    @Query(value =
            "select * from VACATION v, VACATION_STATE vs "
                    + " where v.no = vs.vacation_no"
                    + " and v.employee_no = :no"
                    + " and (vs.state = '승인' or vs.state = '반려')"
                    + " order by v.no desc"
            , nativeQuery = true
    )
    List<Vacation> findByEmployeeNoAndStateOrState(@Param(value = "no") String no);

    // select * from vacation where (approverNo = loginUser and state = '진행중') order by no desc;
    List<Vacation> findByApproverNoAndStateOrderByNoDesc(String no, PaymentState state);

    // select * from vacation where (approverNo = loginUser and state = '승인' or state = '반려') order by no desc;
    List<Vacation> findByApproverNoAndStateOrState(String no, PaymentState state, PaymentState state2);

    // select * from vacation where (no = no); - 문서 반려, 승인 시
    @Query("select v from VACATION v where v.no = :no ")
    Vacation selectByNo(@Param(value = "no") Integer no);

    List<Vacation> findByEmployeeNoAndEffectiveDateContaining(String employeeNo, String formatedNow);

    @Query(value = "SELECT * from VACATION v, VACATION_STATE vs where v.no = vs.vacation_no and vs.state = '승인' and v.employee_no = :employee_no", nativeQuery = true)
    List<Vacation> findByVacationQuestion(@RequestParam("employee_no") String employee_no);



}
