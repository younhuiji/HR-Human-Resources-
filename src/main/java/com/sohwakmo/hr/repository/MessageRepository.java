package com.sohwakmo.hr.repository;

import com.sohwakmo.hr.domain.Message;
import com.sohwakmo.hr.dto.MessageSearchDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {

    /**
     * 받은쪽지함
     * select * from message where receiveNo = ? order by messageNo desc;
     * @param employeeNo 로그인한 사용자 번호
     * @return
     */
    @Query(
            value = "select new com.sohwakmo.hr.dto.MessageSearchDto(m.messageNo, m.messageType, m.receiveReadCheck, m.title, m.sendTime, m.senderNo, m.employee) "
                    + "from MESSAGE m "
                    + "where m.receiveNo = :employeeNo "
                    + "order by m.messageNo desc"
    )
    Page<MessageSearchDto> findByReceiveNoOrderByMessageNoDesc(@Param(value = "employeeNo") String employeeNo, Pageable pageable);
    /**
     * 받은쪽지함 전체 검색
     * select 컬럼명
     *     from message m
     *     inner join employee e on m.sender_no = e.employee_no
     *     where m.receive_no = ?
     *     (lower(m.title) like lower('% :keyword %') OR
     *     lower(e.name) like lower('% :keyword %')
     *     order by m.messageNo desc
     */
    @Query(
            value = "select new com.sohwakmo.hr.dto.MessageSearchDto(m.messageNo, m.messageType, m.receiveReadCheck, m.title, m.sendTime, m.senderNo, e) "
                    + "from MESSAGE m "
                    + "inner join Employee e on m.senderNo = e.employeeNo "
                    + "where m.receiveNo = :employeeNo AND "
                    + "(lower(m.title) like lower('%' || :keyword || '%') OR "
                    + "lower(e.name) like lower('%' || :keyword || '%')) "
                    + "order by m.messageNo desc"
    )
    Page<MessageSearchDto> findByReceiveNoAll(@Param(value = "employeeNo") String employeeNo,
                                              @Param(value = "keyword") String keyword,
                                              Pageable pageable);
    /**
     * 받은쪽지함 제목으로 검색
     * select 컬럼명
     *     from message m
     *     inner join employee e on m.sender_no = e.employee_no
     *     where m.receive_no = ?
     *     lower(m.title) like lower('% :keyword %')
     *     order by m.messageNo desc
     */
    @Query(
            value = "select new com.sohwakmo.hr.dto.MessageSearchDto(m.messageNo, m.messageType, m.receiveReadCheck, m.title, m.sendTime, m.senderNo, e) "
                    + "from MESSAGE m "
                    + "inner join Employee e on m.senderNo = e.employeeNo "
                    + "where m.receiveNo = :employeeNo AND "
                    + "lower(m.title) like lower('%' || :keyword || '%') "
                    + "order by m.messageNo desc"
    )
    Page<MessageSearchDto> findByReceiveNoAndTitle(@Param(value = "employeeNo") String employeeNo,
                                                   @Param(value = "keyword") String keyword,
                                                   Pageable pageable);
    /**
     * 받은쪽지함 보낸사람으로 검색
     * select * from message m
     *     inner join employee e on m.sender_no = e.employee_no
     *     where m.receive_no = ?
     *     lower(e.name) like lower('% :keyword %')
     *     order by m.messageNo desc
     */
    @Query(
           value = "select new com.sohwakmo.hr.dto.MessageSearchDto(m.messageNo, m.messageType, m.receiveReadCheck, m.title, m.sendTime, m.senderNo, e)"
                    + " from MESSAGE m "
                    + " inner join Employee e on m.senderNo = e.employeeNo "
                    + " where m.receiveNo = :employeeNo AND"
                    + " lower(e.name) like lower('%' || :keyword || '%') "
                    + "order by m.messageNo desc"
    )
    Page<MessageSearchDto> findByReceiveNoAndSenderName(@Param(value = "employeeNo") String employeeNo,
                                                        @Param(value = "keyword") String keyword,
                                                        Pageable pageable);
    /**
     * 받은쪽지함 메세지 타입이 있는 전체 검색
     * select 컬럼명
     *     from message m
     *     inner join employee e on m.sender_no = e.employee_no
     *     where m.receive_no = ? AND
     *     m.message_Type = '긴급' AND
     *     (lower(m.title) like lower('% :keyword %') OR
     *     lower(e.name) like lower('% :keyword %'))
     *     order by m.messageNo desc
     */
    @Query(
            value = "select new com.sohwakmo.hr.dto.MessageSearchDto(m.messageNo, m.messageType, m.receiveReadCheck, m.title, m.sendTime, m.senderNo, e) "
                    + "from MESSAGE m "
                    + "inner join Employee e on m.senderNo = e.employeeNo "
                    + "where m.receiveNo = :employeeNo AND "
                    + "m.messageType = :messageType AND "
                    + "(lower(m.title) like lower('%' || :keyword || '%') OR "
                    + "lower(e.name) like lower('%' || :keyword || '%')) "
                    + "order by m.messageNo desc"
    )
    Page<MessageSearchDto> findByMessageTypeAndReceiveNoAll(@Param(value = "employeeNo") String employeeNo,
                                                            @Param(value = "keyword") String keyword,
                                                            @Param(value = "messageType") String messageType,
                                                            Pageable pageable);
    /**
     * 받은쪽지함 메세지 타입이 있는 제목 검색
     * select 컬럼명
     *     from message m
     *     inner join employee e on m.sender_no = e.employee_no
     *     where m.receive_no = ? AND
     *     m.message_Type = '긴급' AND
     *     lower(m.title) like lower('% :keyword %')
     *     order by m.messageNo desc
     */
    @Query(
            value = "select new com.sohwakmo.hr.dto.MessageSearchDto(m.messageNo, m.messageType, m.receiveReadCheck, m.title, m.sendTime, m.senderNo, e) "
                    + "from MESSAGE m "
                    + "inner join Employee e on m.senderNo = e.employeeNo "
                    + "where m.receiveNo = :employeeNo AND "
                    + "m.messageType = :messageType AND "
                    + "lower(m.title) like lower('%' || :keyword || '%') "
                    + "order by m.messageNo desc"
    )
    Page<MessageSearchDto> findByMessageTypeAndReceiveNoAndTitle(@Param(value = "employeeNo") String employeeNo,
                                                                 @Param(value = "keyword") String keyword,
                                                                 @Param(value = "messageType") String messageType,
                                                                 Pageable pageable);
    /**
     * 받은쪽지함 메세지 타입이 있는 보낸사람 검색
     * select 컬럼명
     *     from message m
     *     inner join employee e on m.sender_no = e.employee_no
     *     where m.receive_no = ? AND
     *     m.message_Type = '긴급' AND
     *     lower(e.name) like lower('% :keyword %')
     *     order by m.messageNo desc
     */
    @Query(
            value = "select new com.sohwakmo.hr.dto.MessageSearchDto(m.messageNo, m.messageType, m.receiveReadCheck, m.title, m.sendTime, m.senderNo, e) "
                    + "from MESSAGE m "
                    + "inner join Employee e on m.senderNo = e.employeeNo "
                    + "where m.receiveNo = :employeeNo AND "
                    + "m.messageType = :messageType AND "
                    + "lower(e.name) like lower('%' || :keyword || '%') "
                    + "order by m.messageNo desc"
    )
    Page<MessageSearchDto> findByMessageTypeAndReceiveNoAndName(@Param(value = "employeeNo") String employeeNo,
                                                                @Param(value = "keyword") String keyword,
                                                                @Param(value = "messageType") String messageType,
                                                                Pageable pageable);

}
