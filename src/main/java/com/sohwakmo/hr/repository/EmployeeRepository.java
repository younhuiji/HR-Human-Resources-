package com.sohwakmo.hr.repository;

import com.sohwakmo.hr.domain.Employee;
import com.sohwakmo.hr.domain.BusinessCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    boolean existsByEmployeeNo(String employeeNo);

    boolean existsByEmail(String email);

    boolean existsByPhone(String phoneValue);

    Employee findByEmployeeNo(String employeeNo);

    // select * from employee where employeeNo = getEmployeeNo;
    @Query("select e from Employee e where e.employeeNo = :no")
    Employee selectByNo(@Param(value = "no") String no);
}
