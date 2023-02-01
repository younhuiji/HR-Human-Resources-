package com.sohwakmo.hr.repository;

import com.sohwakmo.hr.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {

    /**
     * 로그인한 사용자 번호로 받은 메일을 select
     * select * from message where receiveNo = ? order by messageNo desc;
     * @param employeeNo 로그인한 사용자 번호
     * @return
     */
    List<Message> findByReceiveNoOrderByMessageNoDesc(Integer employeeNo);

    /**
     * 보낸사람의 no로 select해서 이름 찾기
     * select * from message where senderNo = ? order by messageNo desc;
     * @param senderNo 보낸사람의 no값
     * @return
     */
    List<String> findBySenderNoOrderByMessageNoDesc(Integer senderNo);

    /**
     * 제목으로 검색하기
     * select * from message where receiveNo = ? and lower(title) like lower('%%') order by message_no desc;
     */
    List<Message> findByReceiveNoAndTitleIgnoreCaseContainingOrderByMessageNoDesc(Integer employeeNo, String title);

    /**
     * 보낸사람으로 검색하기
     * select *
     * from message m
     *     inner join employee e on m.sender_no = e.employee_no
     * where m.receive_no = ?;
     */
    @Query(
            "select m from MESSAGE m "
            + " inner join Employee e on m.senderNo = e.employeeNo "
            + " where m.receiveNo = :employeeNo AND"
            + " lower(e.name) like lower('% || :keyword ||%')"
    )
    List<Message> searchBySenderName(
            @Param(value = "employeeNo") Integer employeeNo,
            @Param(value = "keyword") String keyword
            );

}
