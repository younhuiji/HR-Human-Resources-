package com.sohwakmo.hr.repository;

import com.sohwakmo.hr.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    boolean existsByEmployeeNo(String employeeNo);

    boolean existsByEmail(String email);

    boolean existsByPhone(String phoneValue);

    Employee findByEmployeeNo(String employeeNo);
}
