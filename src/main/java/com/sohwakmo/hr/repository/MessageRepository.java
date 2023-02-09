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
     * select * from message where receiveNo = ? and receiveTrash = 0 order by messageNo desc;
     * @param employeeNo 로그인한 사용자 번호
     * @return
     */
    @Query(
            value = "select new com.sohwakmo.hr.dto.MessageSearchDto(m.messageNo, m.messageType, m.receiveReadCheck, m.title, m.sendTime, m.senderNo, m.senderEmployee, m.receiveEmployee) "
                    + "from MESSAGE m "
                    + "where m.receiveNo = :employeeNo AND "
                    + "m.receiveTrash = 0 "
                    + "order by m.messageNo desc"
    )
    Page<MessageSearchDto> findByReceiveNoOrderByMessageNoDesc(@Param(value = "employeeNo") String employeeNo, Pageable pageable);
    /**
     * 받은쪽지함 전체 검색
     */
    @Query(
            value = "select new com.sohwakmo.hr.dto.MessageSearchDto(m.messageNo, m.messageType, m.receiveReadCheck, m.title, m.sendTime, m.senderNo, m.senderEmployee, m.receiveEmployee) "
                    + "from MESSAGE m "
                    + "inner join Employee e on m.senderNo = e.employeeNo "
                    + "where m.receiveNo = :employeeNo AND "
                    + "m.receiveTrash = 0 AND"
                    + "(lower(m.title) like lower('%' || :keyword || '%') OR "
                    + "lower(e.name) like lower('%' || :keyword || '%')) "
                    + "order by m.messageNo desc"
    )
    Page<MessageSearchDto> findByReceiveNoAll(@Param(value = "employeeNo") String employeeNo,
                                              @Param(value = "keyword") String keyword,
                                              Pageable pageable);
    /**
     * 받은쪽지함 제목으로 검색
     */
    @Query(
            value = "select new com.sohwakmo.hr.dto.MessageSearchDto(m.messageNo, m.messageType, m.receiveReadCheck, m.title, m.sendTime, m.senderNo, m.senderEmployee, m.receiveEmployee) "
                    + "from MESSAGE m "
                    + "inner join Employee e on m.senderNo = e.employeeNo "
                    + "where m.receiveNo = :employeeNo AND "
                    + "m.receiveTrash = 0 AND "
                    + "lower(m.title) like lower('%' || :keyword || '%') "
                    + "order by m.messageNo desc"
    )
    Page<MessageSearchDto> findByReceiveNoAndTitle(@Param(value = "employeeNo") String employeeNo,
                                                   @Param(value = "keyword") String keyword,
                                                   Pageable pageable);
    /**
     * 받은쪽지함 보낸사람으로 검색
     */
    @Query(
           value = "select new com.sohwakmo.hr.dto.MessageSearchDto(m.messageNo, m.messageType, m.receiveReadCheck, m.title, m.sendTime, m.senderNo, m.senderEmployee, m.receiveEmployee) "
                   + "from MESSAGE m "
                   + "inner join Employee e on m.senderNo = e.employeeNo "
                   + "where m.receiveNo = :employeeNo AND "
                   + "m.receiveTrash = 0 AND "
                   + "lower(e.name) like lower('%' || :keyword || '%') "
                   + "order by m.messageNo desc"
    )
    Page<MessageSearchDto> findByReceiveNoAndSenderName(@Param(value = "employeeNo") String employeeNo,
                                                        @Param(value = "keyword") String keyword,
                                                        Pageable pageable);
    /**
     * 받은쪽지함 메세지 타입이 있는 전체 검색
     */
    @Query(
            value = "select new com.sohwakmo.hr.dto.MessageSearchDto(m.messageNo, m.messageType, m.receiveReadCheck, m.title, m.sendTime, m.senderNo, m.senderEmployee, m.receiveEmployee) "
                    + "from MESSAGE m "
                    + "inner join Employee e on m.senderNo = e.employeeNo "
                    + "where m.receiveNo = :employeeNo AND "
                    + "m.messageType = :messageType AND "
                    + "m.receiveTrash = 0 AND "
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
     */
    @Query(
            value = "select new com.sohwakmo.hr.dto.MessageSearchDto(m.messageNo, m.messageType, m.receiveReadCheck, m.title, m.sendTime, m.senderNo, m.senderEmployee, m.receiveEmployee) "
                    + "from MESSAGE m "
                    + "inner join Employee e on m.senderNo = e.employeeNo "
                    + "where m.receiveNo = :employeeNo AND "
                    + "m.messageType = :messageType AND "
                    + "m.receiveTrash = 0 AND "
                    + "lower(m.title) like lower('%' || :keyword || '%') "
                    + "order by m.messageNo desc"
    )
    Page<MessageSearchDto> findByMessageTypeAndReceiveNoAndTitle(@Param(value = "employeeNo") String employeeNo,
                                                                 @Param(value = "keyword") String keyword,
                                                                 @Param(value = "messageType") String messageType,
                                                                 Pageable pageable);
    /**
     * 받은쪽지함 메세지 타입이 있는 보낸사람 검색
     */
    @Query(
            value = "select new com.sohwakmo.hr.dto.MessageSearchDto(m.messageNo, m.messageType, m.receiveReadCheck, m.title, m.sendTime, m.senderNo, m.senderEmployee, m.receiveEmployee) "
                    + "from MESSAGE m "
                    + "inner join Employee e on m.senderNo = e.employeeNo "
                    + "where m.receiveNo = :employeeNo AND "
                    + "m.messageType = :messageType AND "
                    + "m.receiveTrash = 0 AND "
                    + "lower(e.name) like lower('%' || :keyword || '%') "
                    + "order by m.messageNo desc"
    )
    Page<MessageSearchDto> findByMessageTypeAndReceiveNoAndName(@Param(value = "employeeNo") String employeeNo,
                                                                @Param(value = "keyword") String keyword,
                                                                @Param(value = "messageType") String messageType,
                                                                Pageable pageable);

//---------------------------------------------------------------------------받은쪽지

    /**
     * 보낸쪽지함
     * select * from message where senderNo = ? and senderTrash = 0 order by messageNo desc;
     * @param employeeNo 로그인한 사용자 번호
     * @return
     */
    @Query(
            value = "select new com.sohwakmo.hr.dto.MessageSearchDto(m.messageNo, m.messageType, m.receiveReadCheck, m.title, m.sendTime, m.senderNo, m.senderEmployee, m.receiveEmployee) "
                    + "from MESSAGE m "
                    + "where m.senderNo = :employeeNo AND "
                    + "m.senderTrash = 0 "
                    + "order by m.messageNo desc"
    )
    Page<MessageSearchDto> findBySenderNoOrderByMessageNoDesc(@Param(value = "employeeNo") String employeeNo, Pageable pageable);
    /**
     * 보낸쪽지함 전체 검색
     */
    @Query(
            value = "select new com.sohwakmo.hr.dto.MessageSearchDto(m.messageNo, m.messageType, m.receiveReadCheck, m.title, m.sendTime, m.senderNo, m.senderEmployee, m.receiveEmployee) "
                    + "from MESSAGE m "
                    + "inner join Employee e on m.receiveNo = e.employeeNo "
                    + "where m.senderNo = :employeeNo AND "
                    + "m.senderTrash = 0 AND"
                    + "(lower(m.title) like lower('%' || :keyword || '%') OR "
                    + "lower(e.name) like lower('%' || :keyword || '%')) "
                    + "order by m.messageNo desc"
    )
    Page<MessageSearchDto> findBySenderNoAll(@Param(value = "employeeNo") String employeeNo,
                                             @Param(value = "keyword") String keyword,
                                             Pageable pageable);
    /**
     * 보낸쪽지함 제목으로 검색
     */
    @Query(
            value = "select new com.sohwakmo.hr.dto.MessageSearchDto(m.messageNo, m.messageType, m.receiveReadCheck, m.title, m.sendTime, m.senderNo, m.senderEmployee, m.receiveEmployee) "
                    + "from MESSAGE m "
                    + "inner join Employee e on m.receiveNo = e.employeeNo "
                    + "where m.senderNo = :employeeNo AND "
                    + "m.senderTrash = 0 AND "
                    + "lower(m.title) like lower('%' || :keyword || '%') "
                    + "order by m.messageNo desc"
    )
    Page<MessageSearchDto> findBySenderNoAndTitle(@Param(value = "employeeNo") String employeeNo,
                                                   @Param(value = "keyword") String keyword,
                                                   Pageable pageable);
    /**
     * 보낸쪽지함 받은사람으로 검색
     */
    @Query(
            value = "select new com.sohwakmo.hr.dto.MessageSearchDto(m.messageNo, m.messageType, m.receiveReadCheck, m.title, m.sendTime, m.senderNo, m.senderEmployee, m.receiveEmployee) "
                    + "from MESSAGE m "
                    + "inner join Employee e on m.receiveNo = e.employeeNo "
                    + "where m.senderNo = :employeeNo AND "
                    + "m.senderTrash = 0 AND "
                    + "lower(e.name) like lower('%' || :keyword || '%') "
                    + "order by m.messageNo desc"
    )
    Page<MessageSearchDto> findBySenderNoAndReceiveName(@Param(value = "employeeNo") String employeeNo,
                                                        @Param(value = "keyword") String keyword,
                                                        Pageable pageable);
    /**
     * 보낸쪽지함 메세지 타입이 있는 전체 검색
     */
    @Query(
            value = "select new com.sohwakmo.hr.dto.MessageSearchDto(m.messageNo, m.messageType, m.receiveReadCheck, m.title, m.sendTime, m.senderNo, m.senderEmployee, m.receiveEmployee) "
                    + "from MESSAGE m "
                    + "inner join Employee e on m.receiveNo = e.employeeNo "
                    + "where m.senderNo = :employeeNo AND "
                    + "m.messageType = :messageType AND "
                    + "m.senderTrash = 0 AND "
                    + "(lower(m.title) like lower('%' || :keyword || '%') OR "
                    + "lower(e.name) like lower('%' || :keyword || '%')) "
                    + "order by m.messageNo desc"
    )
    Page<MessageSearchDto> findByMessageTypeAndSenderNoAll(@Param(value = "employeeNo") String employeeNo,
                                                            @Param(value = "keyword") String keyword,
                                                            @Param(value = "messageType") String messageType,
                                                            Pageable pageable);
    /**
     * 보낸쪽지함 메세지 타입이 있는 제목 검색
     */
    @Query(
            value = "select new com.sohwakmo.hr.dto.MessageSearchDto(m.messageNo, m.messageType, m.receiveReadCheck, m.title, m.sendTime, m.senderNo, m.senderEmployee, m.receiveEmployee) "
                    + "from MESSAGE m "
                    + "inner join Employee e on m.receiveNo = e.employeeNo "
                    + "where m.senderNo = :employeeNo AND "
                    + "m.messageType = :messageType AND "
                    + "m.senderTrash = 0 AND "
                    + "lower(m.title) like lower('%' || :keyword || '%') "
                    + "order by m.messageNo desc"
    )
    Page<MessageSearchDto> findByMessageTypeAndSenderNoAndTitle(@Param(value = "employeeNo") String employeeNo,
                                                                 @Param(value = "keyword") String keyword,
                                                                 @Param(value = "messageType") String messageType,
                                                                 Pageable pageable);
    /**
     * 보낸쪽지함 메세지 타입이 있는 빋는사람 검색
     */
    @Query(
            value = "select new com.sohwakmo.hr.dto.MessageSearchDto(m.messageNo, m.messageType, m.receiveReadCheck, m.title, m.sendTime, m.senderNo, m.senderEmployee, m.receiveEmployee) "
                    + "from MESSAGE m "
                    + "inner join Employee e on m.receiveNo = e.employeeNo "
                    + "where m.senderNo = :employeeNo AND "
                    + "m.messageType = :messageType AND "
                    + "m.senderTrash = 0 AND "
                    + "lower(e.name) like lower('%' || :keyword || '%') "
                    + "order by m.messageNo desc"
    )
    Page<MessageSearchDto> findByMessageTypeAndSenderNoAndName(@Param(value = "employeeNo") String employeeNo,
                                                                @Param(value = "keyword") String keyword,
                                                                @Param(value = "messageType") String messageType,
                                                                Pageable pageable);

//---------------------------------------------------------------------------휴지통

    /**
     * 휴지통
     * select * from message
     * where (receive_no = 2 and receive_trash = 1 and receive_delete = 0) or (sender_no = 2 and sender_trash = 1 and sender_delete = 0)  order by message_No desc;
     * @param employeeNo 로그인한 사용자 번호
     * @return
     */
    @Query(
            value = "select new com.sohwakmo.hr.dto.MessageSearchDto(m.messageNo, m.messageType, m.receiveReadCheck, m.title, m.sendTime, m.senderNo, m.senderEmployee, m.receiveEmployee) "
                    + "from MESSAGE m "
                    + "where  (m.receiveNo = :employeeNo AND m.receiveTrash = 1 AND m.receiveDelete = 0) OR "
                    + "(m.senderNo = :employeeNo AND m.senderTrash = 1 AND m.senderDelete = 0) "
                    + "order by m.messageNo desc"
    )
    Page<MessageSearchDto> findByEmployeeNoOrderByMessageNoDesc(@Param(value = "employeeNo") String employeeNo, Pageable pageable);
    /**
     * 휴지통 전체 검색
     */
    @Query(
            value = "select new com.sohwakmo.hr.dto.MessageSearchDto(m.messageNo, m.messageType, m.receiveReadCheck, m.title, m.sendTime, m.senderNo, m.senderEmployee, m.receiveEmployee) "
                    + "from MESSAGE m "
                    + "inner join Employee e on m.senderNo = e.employeeNo "
                    + "inner join Employee e2 on m.receiveNo = e2.employeeNo "
                    + "where "
                    + "((m.receiveNo = :employeeNo AND m.receiveTrash = 1 AND m.receiveDelete = 0) OR "
                    + "(m.senderNo = :employeeNo AND m.senderTrash = 1 AND m.senderDelete = 0)) AND "
                    + "( ( lower(e.name) like lower('%' || :keyword || '%') ) or "
                    + "( lower(e2.name) like lower('%' || :keyword || '%') ) or "
                    + "( lower(m.title) like lower('%' || :keyword || '%') ) ) "
                    + "order by m.messageNo desc"
    )
    Page<MessageSearchDto> findByEmployeeNoAll(@Param(value = "employeeNo") String employeeNo,
                                               @Param(value = "keyword") String keyword,
                                               Pageable pageable);
    /**
     * 휴지통 제목으로 검색
     */
    @Query(
            value = "select new com.sohwakmo.hr.dto.MessageSearchDto(m.messageNo, m.messageType, m.receiveReadCheck, m.title, m.sendTime, m.senderNo, m.senderEmployee, m.receiveEmployee) "
                    + "from MESSAGE m "
                    + "inner join Employee e on m.senderNo = e.employeeNo "
                    + "inner join Employee e2 on m.receiveNo = e2.employeeNo "
                    + "where "
                    + "((m.receiveNo = :employeeNo AND m.receiveTrash = 1 AND m.receiveDelete = 0) OR "
                    + "(m.senderNo = :employeeNo AND m.senderTrash = 1 AND m.senderDelete = 0)) AND "
                    + "lower(m.title) like lower('%' || :keyword || '%') "
                    + "order by m.messageNo desc"
    )
    Page<MessageSearchDto> findByEmployeeNoAndTitle(@Param(value = "employeeNo") String employeeNo,
                                                    @Param(value = "keyword") String keyword,
                                                    Pageable pageable);
    /**
     * 휴지통 사람으로 검색
     */
    @Query(
            value = "select new com.sohwakmo.hr.dto.MessageSearchDto(m.messageNo, m.messageType, m.receiveReadCheck, m.title, m.sendTime, m.senderNo, m.senderEmployee, m.receiveEmployee) "
                    + "from MESSAGE m "
                    + "inner join Employee e on m.senderNo = e.employeeNo "
                    + "inner join Employee e2 on m.receiveNo = e2.employeeNo "
                    + "where "
                    + "((m.receiveNo = :employeeNo AND m.receiveTrash = 1 AND m.receiveDelete = 0) OR "
                    + "(m.senderNo = :employeeNo AND m.senderTrash = 1 AND m.senderDelete = 0)) AND "
                    + "( ( lower(e.name) like lower('%' || :keyword || '%') ) or "
                    + "( lower(e2.name) like lower('%' || :keyword || '%') ) )"
                    + "order by m.messageNo desc"
    )
    Page<MessageSearchDto> findByEmployeeNoAndReceiveName(@Param(value = "employeeNo") String employeeNo,
                                                          @Param(value = "keyword") String keyword,
                                                          Pageable pageable);
    /**
     * 휴지통 메세지 타입이 있는 전체 검색
     */
    @Query(
            value = "select new com.sohwakmo.hr.dto.MessageSearchDto(m.messageNo, m.messageType, m.receiveReadCheck, m.title, m.sendTime, m.senderNo, m.senderEmployee, m.receiveEmployee) "
                    + "from MESSAGE m "
                    + "inner join Employee e on m.senderNo = e.employeeNo "
                    + "inner join Employee e2 on m.receiveNo = e2.employeeNo "
                    + "where "
                    + "((m.receiveNo = :employeeNo AND m.receiveTrash = 1 AND m.receiveDelete = 0) OR "
                    + "(m.senderNo = :employeeNo AND m.senderTrash = 1 AND m.senderDelete = 0)) AND "
                    + "m.messageType = :messageType AND "
                    + "( ( lower(e.name) like lower('%' || :keyword || '%') ) or "
                    + "( lower(e2.name) like lower('%' || :keyword || '%') ) or "
                    + "( lower(m.title) like lower('%' || :keyword || '%') ) ) "
                    + "order by m.messageNo desc"
    )
    Page<MessageSearchDto> findByMessageTypeAndEmployeeNoAll(@Param(value = "employeeNo") String employeeNo,
                                                             @Param(value = "keyword") String keyword,
                                                             @Param(value = "messageType") String messageType,
                                                             Pageable pageable);
    /**
     * 휴지통 메세지 타입이 있는 제목 검색
     */
    @Query(
            value = "select new com.sohwakmo.hr.dto.MessageSearchDto(m.messageNo, m.messageType, m.receiveReadCheck, m.title, m.sendTime, m.senderNo, m.senderEmployee, m.receiveEmployee) "
                    + "from MESSAGE m "
                    + "inner join Employee e on m.senderNo = e.employeeNo "
                    + "inner join Employee e2 on m.receiveNo = e2.employeeNo "
                    + "where "
                    + "((m.receiveNo = :employeeNo AND m.receiveTrash = 1 AND m.receiveDelete = 0) OR "
                    + "(m.senderNo = :employeeNo AND m.senderTrash = 1 AND m.senderDelete = 0)) AND "
                    + "m.messageType = :messageType AND "
                    + "lower(m.title) like lower('%' || :keyword || '%') "
                    + "order by m.messageNo desc"
    )
    Page<MessageSearchDto> findByMessageTypeAndEmployeeNoAndTitle(@Param(value = "employeeNo") String employeeNo,
                                                                @Param(value = "keyword") String keyword,
                                                                @Param(value = "messageType") String messageType,
                                                                Pageable pageable);
    /**
     * 휴지통 메세지 타입이 있는 사람 검색
     */
    @Query(
            value = "select new com.sohwakmo.hr.dto.MessageSearchDto(m.messageNo, m.messageType, m.receiveReadCheck, m.title, m.sendTime, m.senderNo, m.senderEmployee, m.receiveEmployee) "
                    + "from MESSAGE m "
                    + "inner join Employee e on m.senderNo = e.employeeNo "
                    + "inner join Employee e2 on m.receiveNo = e2.employeeNo "
                    + "where "
                    + "((m.receiveNo = :employeeNo AND m.receiveTrash = 1 AND m.receiveDelete = 0) OR "
                    + "(m.senderNo = :employeeNo AND m.senderTrash = 1 AND m.senderDelete = 0)) AND "
                    + "m.messageType = :messageType AND "
                    + "( ( lower(e.name) like lower('%' || :keyword || '%') ) or "
                    + "( lower(e2.name) like lower('%' || :keyword || '%') ) )"
                    + "order by m.messageNo desc"
    )
    Page<MessageSearchDto> findByMessageTypeAndEmployeeNoAndName(@Param(value = "employeeNo") String employeeNo,
                                                                 @Param(value = "keyword") String keyword,
                                                                 @Param(value = "messageType") String messageType,
                                                                 Pageable pageable);


}