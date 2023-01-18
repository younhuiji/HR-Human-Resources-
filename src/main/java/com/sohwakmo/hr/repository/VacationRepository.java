package com.sohwakmo.hr.repository;

import com.sohwakmo.hr.domain.Leave;
import com.sohwakmo.hr.domain.Vacation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacationRepository extends JpaRepository<Vacation, Integer> {



}
