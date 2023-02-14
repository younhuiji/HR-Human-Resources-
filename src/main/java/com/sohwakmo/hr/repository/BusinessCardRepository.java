package com.sohwakmo.hr.repository;

import com.sohwakmo.hr.domain.BusinessCard;
import com.sohwakmo.hr.domain.Employee;
import com.sohwakmo.hr.domain.Leave;
import com.sohwakmo.hr.domain.PaymentState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BusinessCardRepository extends JpaRepository<BusinessCard, Integer> {

    @Query("select b from BUSINESSCARD b where b.category = :card ")
    List<BusinessCard> selectByCard(@Param(value = "card") String card);

    @Query("select b from BUSINESSCARD b where b.employeeNo = :no order by b.no desc ")
    List<BusinessCard> selectByEmployeeNo(@Param(value = "no") String no);

    @Query("select b from BUSINESSCARD b where b.no = :no ")
    BusinessCard selectByNo(@Param(value = "no")Integer no);

    List<BusinessCard> findByEmployeeNoAndState(String no, PaymentState state);

    List<BusinessCard> findByEmployeeNoAndStateOrState(String no, PaymentState state, PaymentState state2);

    List<BusinessCard> findByApproverNoAndState(String no, PaymentState state);

    List<BusinessCard> findByApproverNoAndStateOrState(String no, PaymentState state, PaymentState state2);
}
