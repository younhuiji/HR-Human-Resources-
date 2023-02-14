package com.sohwakmo.hr.repository;

import com.sohwakmo.hr.domain.BusinessTrip;
import com.sohwakmo.hr.domain.PaymentState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface BusinessTripRepository extends JpaRepository<BusinessTrip, Integer> {

    @Query(value = "SELECT * from BUSINESSTRIP b, BUSINESSTRIP_STATE bs where b.no = bs.businesstrip_no and bs.state = '승인' and (b.employee_no = :employee_no or b.companionno = :companionno) ", nativeQuery = true)
    public List<BusinessTrip> findByBusinessTripQuestion(@RequestParam("employee_no") String employee_no, @RequestParam("companionno") String companionno);
    public List<BusinessTrip> findByEmployeeNo(String employeeNo);
    public List<BusinessTrip> findByEmployeeNoAndState(String no, PaymentState state);

    public List<BusinessTrip> findByEmployeeNoAndEffectiveDateContaining(String emplyeeNo, String formatedNow);

    List<BusinessTrip> findByEmployeeNoOrderByNoDesc(String employeeNo);
}

