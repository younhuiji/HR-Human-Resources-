package com.sohwakmo.hr.repository;

import com.sohwakmo.hr.domain.BusinessCard;
import com.sohwakmo.hr.domain.Leave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LeaveRepository extends JpaRepository<Leave, Integer> {

    @Query("select l from LEAVE l where l.employeeName = :name ")
    List<Leave> selectByName(@Param(value = "name")String name);

    @Query("select l from LEAVE l where l.no = :no ")
    Leave selectByNo(@Param(value = "no")Integer no);


}
