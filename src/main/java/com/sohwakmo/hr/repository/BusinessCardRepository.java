package com.sohwakmo.hr.repository;

import com.sohwakmo.hr.domain.BusinessCard;
import com.sohwakmo.hr.domain.Employee;
import com.sohwakmo.hr.domain.Leave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BusinessCardRepository extends JpaRepository<BusinessCard, Integer> {

    @Query("select b from BUSINESSCARD b where b.category = :card ")
    List<BusinessCard> selectByCard(@Param(value = "card") String card);

    @Query("select b from BUSINESSCARD b where b.employeeNo = :no ")
    List<BusinessCard> selectByEmployeeNo(@Param(value = "no")String no);

}
