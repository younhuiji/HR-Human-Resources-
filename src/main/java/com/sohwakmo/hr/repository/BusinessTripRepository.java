package com.sohwakmo.hr.repository;

import com.sohwakmo.hr.domain.BusinessTrip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BusinessTripRepository extends JpaRepository<BusinessTrip, Integer> {
    public List<BusinessTrip> findByEmployeeNoOrCompanionNO(String employeeNo, String companionNo);
}
