package com.sohwakmo.hr.repository;

import com.sohwakmo.hr.domain.BusinessCard;
import com.sohwakmo.hr.domain.PaymentState;
import com.sohwakmo.hr.domain.Vacation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VacationRepository extends JpaRepository<Vacation, Integer> {
    List<Vacation> findByEmployeeNoOrderByNoDesc(String loginUser);

    // 진행중 and EmployeeNo List
    List<Vacation> findByEmployeeNoAndStateOrState(String no, PaymentState state, PaymentState state2);

    List<Vacation> findByEmployeeNoAndState(String no, PaymentState state);

    List<Vacation> findByApproverNoAndStateOrState(String no, PaymentState state, PaymentState state2);

    List<Vacation> findByApproverNoAndState(String no, PaymentState state);

    @Query("select v from VACATION v where v.no = :no ")
    Vacation selectByNo(@Param(value = "no") Integer no);

}
