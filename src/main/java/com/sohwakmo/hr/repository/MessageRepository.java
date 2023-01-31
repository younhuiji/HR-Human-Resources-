package com.sohwakmo.hr.repository;

import com.sohwakmo.hr.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {

    /**
     * 로그인한 사용자 번호로 받은 메일을 select
     * @param employeeNo 로그인한 사용자 번호
     * @return
     */
    List<Message> findByReceiveNoOrderByMessageNoDesc(Integer employeeNo);

    /**
     * 보낸사람의 no로 select해서 이름 찾기
     * @param senderNo 보낸사람의 no값
     * @return
     */
    List<String> findBySenderNoOrderByMessageNoDesc(Integer senderNo);
}
