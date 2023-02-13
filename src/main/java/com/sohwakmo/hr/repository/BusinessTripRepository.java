package com.sohwakmo.hr.repository;

import com.sohwakmo.hr.domain.BusinessTrip;
import com.sohwakmo.hr.domain.PaymentState;
import com.sohwakmo.hr.domain.Vacation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BusinessTripRepository extends JpaRepository<BusinessTrip, Integer> {
    public List<BusinessTrip> findByEmployeeNoOrCompanionNO(String employeeNo, String companionNo);
    public List<BusinessTrip> findByEmployeeNo(String employeeNo);
    public List<BusinessTrip> findByEmployeeNoAndState(String no, PaymentState state);
    @Query("select b from BUSINESSTRIP b where b.no = :no ")
    public BusinessTrip selectByNo(@Param(value = "no") Integer no);
}

