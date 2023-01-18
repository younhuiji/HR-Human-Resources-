package com.sohwakmo.hr.repository;

import com.sohwakmo.hr.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    boolean existsByEmployeeNo(Integer employeeNo);

}
