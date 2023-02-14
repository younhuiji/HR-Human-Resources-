package com.sohwakmo.hr.repository;

import com.sohwakmo.hr.domain.PaymentState;
import com.sohwakmo.hr.domain.Vacation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface VacationRepository extends JpaRepository<Vacation, Integer> {

    @Query(value = "SELECT * from VACATION v, VACATION_STATE vs where v.no = vs.vacation_no and vs.state = '승인' and v.employee_no = :employee_no", nativeQuery = true)
    List<Vacation> findByVacationQuestion(@RequestParam("employee_no") String employee_no);

    // 진행중 and EmployeeNo List
    List<Vacation> findByEmployeeNoAndState(String no, PaymentState state);

    List<Vacation> findByEmployeeNoAndEffectiveDateContaining(String employeeNo, String formatedNow);

    List<Vacation> findByEmployeeNoOrderByNoDesc(String employeeNo);

    List<Vacation> findByEmployeeNo(String no);
}
