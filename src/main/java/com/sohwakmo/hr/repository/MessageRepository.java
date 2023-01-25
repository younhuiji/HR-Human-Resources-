package com.sohwakmo.hr.repository;

import com.sohwakmo.hr.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<Message, Integer> {

}
