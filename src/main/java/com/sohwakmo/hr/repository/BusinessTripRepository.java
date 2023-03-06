package com.sohwakmo.hr.repository;

import com.sohwakmo.hr.domain.BusinessTrip;
import com.sohwakmo.hr.domain.PaymentState;
import com.sohwakmo.hr.domain.Vacation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface BusinessTripRepository extends JpaRepository<BusinessTrip, Integer> {

    // select * from BusinessTrip where employeeNo = loginUser order by no desc;
    List<BusinessTrip> findByEmployeeNoOrderByNoDesc(String employeeNo);

    // select * from BusinessTrip where (employeeNo = loginUser and state = '진행중') order by no desc;
    List<BusinessTrip> findByEmployeeNoAndStateOrderByNoDesc(String loginUser, PaymentState state);

    // select * from BusinessTrip where (approverNo = loginUser and state = '진행중') order by no desc;
    List<BusinessTrip> findByApproverNoAndStateOrderByNoDesc(String no, PaymentState state);

    // select * from BusinessTrip where (approverNo = loginUser and state = '승인' or state = '반려') order by no desc;
    List<BusinessTrip> findByApproverNoAndStateOrStateOrderByNoDesc(String no, PaymentState state, PaymentState state2);


    @Query(value =
            "select * from BUSINESSTRIP b, BUSINESSTRIP_STATE bs "
                    + " where  b.no = bs.businesstrip_no"
                    + " and b.employee_no = :no"
                    + " and (bs.state = '승인' or bs.state = '반려')"
                    + " order by b.no desc"
            , nativeQuery = true
    )
    List<BusinessTrip> findByEmployeeNoAndStateOrState(@Param(value = "no") String no);

    @Query("select b from BUSINESSTRIP b where b.no = :no ")
    public BusinessTrip selectByNo(@Param(value = "no") Integer no);

    @Query(value = "SELECT * from BUSINESSTRIP b, BUSINESSTRIP_STATE bs where b.no = bs.businesstrip_no and bs.state = '승인' and (b.employee_no = :employee_no or b.companionno = :companionno) ", nativeQuery = true)
    public List<BusinessTrip> findByBusinessTripQuestion(@RequestParam("employee_no") String employee_no, @RequestParam("companionno") String companionno);

    public List<BusinessTrip> findByEmployeeNoAndEffectiveDateContaining(String emplyeeNo, String formatedNow);

}

