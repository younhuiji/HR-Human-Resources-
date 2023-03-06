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

    // select * from BusinessCard where employeeNo = loginUser order by no desc;
    List<BusinessCard> findByEmployeeNoOrderByNoDesc(String loginUser);

    // select * from BusinessCard where (employeeNo = loginUser and state = '진행중') order by no desc;
    List<BusinessCard> findByEmployeeNoAndStateOrderByNoDesc(String no, PaymentState state);

    // select * from BusinessCard where (employeeNo = loginUser) and (state = '승인' or state = '반려') order by no desc;
    @Query(value =
            "select * from BUSINESSCARD b, BUSINESSCARD_STATE bs "
                    + " where b.no = bs.businesscard_no"
                    + " and b.employee_no = :no"
                    + " and (bs.state = '승인' or bs.state = '반려')"
                    + " order by b.no desc"
            , nativeQuery = true
    )
    List<BusinessCard> findByEmployeeNoAndStateOrState(@Param(value = "no") String no);

    // select * from BusinessCard where (approverNo = loginUser and state = '진행중') order by no desc;
    List<BusinessCard> findByApproverNoAndStateOrderByNoDesc(String no, PaymentState state);

    // select * from BusinessCard where (approverNo = loginUser and state = '승인' or state = '반려' ) order by no desc;
    List<BusinessCard> findByApproverNoAndStateOrStateOrderByNoDesc(String no, PaymentState state, PaymentState state2);

    // select * from BusinessCard where (no = no); - 문서 반려, 승인 시
    @Query("select b from BUSINESSCARD b where b.no = :no ")
    BusinessCard selectByNo(@Param(value = "no")Integer no);

}
