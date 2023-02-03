package com.sohwakmo.hr.repository;

import com.sohwakmo.hr.domain.BusinessCard;
import com.sohwakmo.hr.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // JPQL
    // TODO: 상무 and 팀장 다 가져오거나 level3 파라미터 값으로 변경하기
    @Query("select e from Employee e where e.part.position = :a ")
    List<Employee> selectByPosition(@Param(value = "a") String a);

    @Query("select e from Employee e where e.part.team = :teamName")
    List<Employee> selectByPart(@Param(value = "teamName") String teamName);

    @Query("select e from Employee e where e.employeeNo = :no")
    Employee selectByNo(@Param(value = "no") String no);


}
