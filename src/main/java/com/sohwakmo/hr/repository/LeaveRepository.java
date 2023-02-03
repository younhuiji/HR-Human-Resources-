package com.sohwakmo.hr.repository;

import com.sohwakmo.hr.domain.Leave;
import com.sohwakmo.hr.domain.PaymentState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LeaveRepository extends JpaRepository<Leave, Integer> {

    @Query("select l from LEAVE l where l.employeeNo = :no ")
    List<Leave> selectByEmployeeNO(@Param(value = "no")String no);

    @Query("select l from LEAVE l where l.no = :no ")
    Leave selectByNo(@Param(value = "no")Integer no);

    @Modifying
    @Query("update LEAVE l SET l.state = :state where l.no = :no")
    int updateState(@Param(value = "no")Integer no, @Param(value = "state")PaymentState state);


}
