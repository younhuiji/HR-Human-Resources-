package com.sohwakmo.hr.repository;

import com.sohwakmo.hr.domain.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance,Long> {
    List<Attendance> findByMonth(String month);

    List<Attendance> findByEmployeeEmployeeNoAndMonth(String employeeNo, String currentMonth);
}
