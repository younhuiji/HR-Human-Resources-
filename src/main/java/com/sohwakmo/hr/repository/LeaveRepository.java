package com.sohwakmo.hr.repository;

import com.sohwakmo.hr.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LeaveRepository extends JpaRepository<Leave, Integer> {

    // select * from LEAVE where employeeNo = loginUser order by no desc;
    @Query("select l from LEAVE l where l.employeeNo = :no order by l.no desc ")
    List<Leave> selectByEmployeeNoOrderByNoDesc(@Param(value = "no")String no);

    // select * from LEAVE where (employeeNo = loginUser and state = '진행중') order by no desc;
    List<Leave> findByEmployeeNoAndStateOrderByNoDesc(String no, PaymentState state);

    // select * from LEAVE where (employeeNo = loginUser) and (state = '승인' or state = '반려') order by no desc;
    @Query(value =
            "select * from  LEAVE l, LEAVE_STATE ls "
                    + " where l.no = ls.leave_no"
                    + " and l.employee_no = :no"
                    + " and (ls.state = '승인' or ls.state = '반려')"
                    + " order by l.no desc"
            , nativeQuery = true
    )
    List<Leave> findByEmployeeNoAndStateOrState(@Param(value = "no") String no);

    // select * from leave where (approverNo = loginUser or secondApproverNo = loginUser and state = '진행중') order by no desc;
    @Query(value =
            "select * from  LEAVE l, LEAVE_STATE ls "
                    + " where l.no = ls.leave_no"
                    + " and (l.approver_no = :no or l.second_approver_no = :no)"
                    + " and (ls.state = '진행중')"
                    + " order by l.no desc"
            , nativeQuery = true
    )
    List<Leave> findByApproverNoOrSecondApproverNoAndStateOrderByNoDesc(@Param(value = "no") String no);

    // select * from leave where (approverNo = loginUser or secondApproverNo = loginUser) and (state = '승인' and state = '반려') order by no desc;
    @Query(value =
            "select * from  LEAVE l, LEAVE_STATE ls "
                    + " where l.no = ls.leave_no"
                    + " and (l.approver_no = :no or l.second_approver_no = :no)"
                    + " and (ls.state = '승인' or ls.state = '반려')"
                    + " order by l.no desc"
            , nativeQuery = true
    )
    List<Leave> findByApproverNoOrSecondApproverNoAndStateOrState(@Param(value = "no") String no);

    // select * from LEAVE where (no = no); - 문서 반려, 승인 시
    @Query("select l from LEAVE l where l.no = :no ")
    Leave selectByNo(@Param(value = "no")Integer no);


}
